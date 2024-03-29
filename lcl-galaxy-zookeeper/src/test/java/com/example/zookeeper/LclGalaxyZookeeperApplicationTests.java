package com.example.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LclGalaxyZookeeperApplicationTests {
    // 指定 zk 集群
    private static final String CLUSTER = "192.168.124.11:2181";
    // 指定节点名称
    private static final String PATH = "/mylog";

    @Test
    void contextLoads() {
        // 创建 zkClient
        ZkClient zkClient = new ZkClient(CLUSTER);
        // 为 zkClient 指定序列化器
        zkClient.setZkSerializer(new SerializableSerializer());
        // ---------------- 创建节点 -----------
        // 指定创建持久节点
        CreateMode mode = CreateMode.PERSISTENT;
        // 指定节点数据内容
        String data = "first log";
        // 创建节点
        String nodeName = zkClient.create(PATH, data, mode);
        System.out.println("新创建的节点名称为：" + nodeName);
        // ---------------- 获取数据内容 -----------
        Object readData = zkClient.readData(PATH);
        System.out.println("节点的数据内容为：" + readData);
        // ---------------- 注册 watcher -----------
        zkClient.subscribeDataChanges(PATH, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.print("节点" + dataPath);
                System.out.println("的数据已经更新为了" + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "的数据内容被删除");
            }
        });
        // ---------------- 更新数据内容 -----------
        zkClient.writeData(PATH, "second log");
        String updatedData = zkClient.readData(PATH);
        System.out.println("更新过的数据内容为：" + updatedData);
        // ---------------- 删除节点 -----------
        zkClient.delete(PATH);
        // ---------------- 判断节点存在性 -----------
        boolean isExists = zkClient.exists(PATH);
        System.out.println(PATH + "节点仍存在吗？" + isExists);
    }


    @Test
    public void curatorTest() throws Exception{
        String nodePath = "/lclcurator";
        // ---------------- 创建会话 -----------
        // 创建重试策略对象:第 1 秒重试 1 次，最多重试 3 次
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3); // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory
                .builder() .connectString("192.168.124.11:2181") .sessionTimeoutMs(15000) .connectionTimeoutMs(13000) .retryPolicy(retryPolicy) .namespace("logs") .build();
        // 开启客户端
        client.start();
        // 指定要创建和操作的节点，注意，其是相对于/logs 节点的 String nodePath = "/host";
        // ---------------- 创建节点 -----------
        String nodeName = client.create().forPath(nodePath, "myhost".getBytes()); System.out.println("新创建的节点名称为:" + nodeName);
        // ---------------- 获取数据内容并注册 watcher -----------
        byte[] data = client.getData().usingWatcher((CuratorWatcher) event -> {
            System.out.println(event.getPath() + "数据内容发生变化"); }).forPath(nodePath);
        System.out.println("节点的数据内容为:" + new String(data));
        // ---------------- 更新数据内容 ----------- client.setData().forPath(nodePath, "newhost".getBytes());
        // 获取更新过的数据内容
        byte[] newData = client.getData().forPath(nodePath); System.out.println("更新过的数据内容为:" + new String(newData));
        // ---------------- 删除节点 ----------- client.delete().forPath(nodePath);
        // ---------------- 判断节点存在性 -----------
        Stat stat = client.checkExists().forPath(nodePath);
        boolean isExists = true; if(stat == null) {
            isExists = false; }
        System.out.println(nodePath + "节点仍存在吗?" + isExists);
    }

}
