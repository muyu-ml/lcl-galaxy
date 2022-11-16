# 一、单机搭建

1、安装与启动

```sh
# 1.下载社区版 MongoDB 4.1.3
#   下载地址：https://www.mongodb.com/download-center#community
wget https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-rhel70-4.1.3.tgz
# 2.将压缩包解压即可
tar -zxvf mongodb-linux-x86_64-rhel70-4.1.3.tgz

# 3.创建数据目录和日志目录
cd mongodb-linux-x86_64-rhel70-4.1.3
mkdir datas
mkdir logs
mkdir conf
touch logs/mongodb.log
# 4.创建mongodb.conf文件
vim conf/mongo.conf
# 5.指定配置文件方式的启动服务端
bin/mongod -f conf/mongo.conf
```

2、配置文件

```properties
#监听的端口，默认27017
port=27017
#数据库目录，默认/data/db
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/datas
#日志路径
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/logs/mongodb.log
#是否追加日志
logappend=true
#是否已后台启动的方式登陆
fork=true
#监听IP地址，默认全部可以访问
bind_ip=0.0.0.0
# 是开启用户密码登陆
auth=false
```

3、启动脚本start-mongo.sh

```bash
vim start-mongo.sh
chmod 755 start-mongo.sh
```

````
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/conf/mongo.conf
echo "start mongo..."
ps -ef | grep mongodb
````

4、关闭脚本stop-mongo.sh

```sh
vim  stop-mongo.sh
chmod 755 stop-mongo.sh
```

```bash
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/conf/mongo.conf
echo "stop mongo..."
ps -ef | grep mongodb
```



# 二、复制集搭建

1、配置脚本

（1）创建相关目录

```sh
# 初始化集群数据文件存储目录和日志文件
cd mongodb-linux-x86_64-rhel70-4.1.3/datas
mkdir server1 server2 server3
touch logs/server1.log
touch logs/server2.log
touch logs/server3.log

# 创建集群配置文件目录
mkdir cluster
```

（2）主节点配置 mongo_37017.conf  

```properties
tee cluster/mongo_37017.conf <<-'EOF'
# 主节点配置
dbpath=datas/server1
bind_ip=0.0.0.0
port=37017
fork=true
logpath=logs/server1.log
# 集群名称
replSet=heroMongoCluster
EOF
```

（3）从节点1配置 mongo_37018.conf  

```properties
tee cluster/mongo_37018.conf <<-'EOF'
dbpath=datas/server2
bind_ip=0.0.0.0
port=37018
fork=true
logpath=logs/server2.log
replSet=heroMongoCluster
EOF
```

（4）从节点2配置 mongo_37019.conf  

```properties
tee cluster/mongo_37019.conf <<-'EOF'
dbpath=datas/server3
bind_ip=0.0.0.0
port=37019
fork=true
logpath=logs/server3.log
replSet=heroMongoCluster
EOF
```

2、初始化节点配置

（1）启动集群脚本

```bash
tee cluster/start-mongo-cluster.sh <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37019.conf 
echo "start mongo cluster..."
ps -ef | grep mongodb
EOF
```

（2）授权

````shell
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/start-mongo-cluster.sh
````

（3）关闭集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/stop-mongo-cluster.sh <<-'EOF'
#! /bin/bash
clear
/usr/local/hero/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37017.conf
/usr/local/hero/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37018.conf
/usr/local/hero/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37019.conf
echo "stop mongo cluster..."
ps -ef | grep mongodb
EOF
```

（4）授权

````shell
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/stop-mongo-cluster.sh
````

3、初始化集群命令

（1）启动三个节点

````shell
bin/cluster/start-mongo-cluster.sh
````

（2）进入Primary 节点 运行如下命令：  

```sh
mongo --host=192.168.249.130 --port=37017
```

```sql
var cfg ={"_id":"heroMongoCluster",
            "protocolVersion" : 1,
            "members":[
                {"_id":1,"host":"192.168.249.130:37017","priority":10},
                {"_id":2,"host":"192.168.249.130:37018"}
            ]
        }
rs.initiate(cfg)
rs.status()
```

也可以动态增删节点

```sql
# 增加节点
rs.add("192.168.249.130:37019")

# 删除slave 节点
rs.remove("192.168.249.130:37019")
```

4、测试数据复制

（1）进入主节点

````shell
bin/mongo --host=192.168.249.130 --port=37017
````

（2）插入并查询数据

````shell
heroMongoCluster:PRIMARY> db.copytest.insertMany([
...    { item: "journal", qty: 25, size: { h: 14, w: 21, uom: "cm" }, status: "A" },
...    { item: "notebook", qty: 50, size: { h: 8.5, w: 11, uom: "in" }, status: "A" },
...    { item: "paper", qty: 100, size: { h: 8.5, w: 11, uom: "in" }, status: "D" },
...    { item: "planner", qty: 75, size: { h: 22.85, w: 30, uom: "cm" }, status: "D" },
...    { item: "postcard", qty: 45, size: { h: 10, w: 15.25, uom: "cm" }, status: "A" },
...    { item: "postcard", qty: 55, size: { h: 10, w: 15.25, uom: "cm" }, status: "C" }
... ]);
{
	"acknowledged" : true,
	"insertedIds" : [
		ObjectId("6374a18f7e54952fce35d943"),
		ObjectId("6374a18f7e54952fce35d944"),
		ObjectId("6374a18f7e54952fce35d945"),
		ObjectId("6374a18f7e54952fce35d946"),
		ObjectId("6374a18f7e54952fce35d947"),
		ObjectId("6374a18f7e54952fce35d948")
	]
}
heroMongoCluster:PRIMARY> db.copytest.find()
{ "_id" : ObjectId("6374a18f7e54952fce35d943"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("6374a18f7e54952fce35d944"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "A" }
{ "_id" : ObjectId("6374a18f7e54952fce35d945"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "D" }
{ "_id" : ObjectId("6374a18f7e54952fce35d946"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("6374a18f7e54952fce35d947"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("6374a18f7e54952fce35d948"), "item" : "postcard", "qty" : 55, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "C" }
````

（3）登录从节点并查询数据

````shell
bin/mongo --host=192.168.249.130 --port=37018
````

````shell
# 默认节点下从节点不能读取数据。调用 rs.slaveOk() 解决  
heroMongoCluster:SECONDARY> rs.slaveOk()
heroMongoCluster:SECONDARY> db.copytest.find()
{ "_id" : ObjectId("6374a18f7e54952fce35d944"), "item" : "notebook", "qty" : 50, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "A" }
{ "_id" : ObjectId("6374a18f7e54952fce35d948"), "item" : "postcard", "qty" : 55, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "C" }
{ "_id" : ObjectId("6374a18f7e54952fce35d945"), "item" : "paper", "qty" : 100, "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "status" : "D" }
{ "_id" : ObjectId("6374a18f7e54952fce35d946"), "item" : "planner", "qty" : 75, "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "status" : "D" }
{ "_id" : ObjectId("6374a18f7e54952fce35d943"), "item" : "journal", "qty" : 25, "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "status" : "A" }
{ "_id" : ObjectId("6374a18f7e54952fce35d947"), "item" : "postcard", "qty" : 45, "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "status" : "A" }
````

5、测试集群选举

（1）kill 掉主节点

（2）进入其他节点查看集群状态

可以看到主节点已经改为37019

````shell
heroMongoCluster:PRIMARY> rs.status()
{
	"set" : "heroMongoCluster",
	......
	"members" : [
		{
			"_id" : 1,
			"name" : "192.168.249.130:37017",
			"health" : 0,
			"state" : 8,
			"stateStr" : "(not reachable/healthy)",
			......
		},
		{
			"_id" : 2,
			"name" : "192.168.249.130:37018",
			"health" : 1,
			"state" : 2,
			"stateStr" : "SECONDARY",
			......
		},
		{
			"_id" : 3,
			"name" : "192.168.249.130:37019",
			"health" : 1,
			"state" : 1,
			"stateStr" : "PRIMARY",
			......
}
````

（3）再次启动上面 kill 掉的节点

````shell
 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37017.conf
````

（4）重新查看集群状态

37017节点又重新变为了主节点

````shell
heroMongoCluster:SECONDARY> rs.status()
{
	"set" : "heroMongoCluster",
	......
	"members" : [
		{
			"_id" : 1,
			"name" : "192.168.249.130:37017",
			"health" : 1,
			"state" : 1,
			"stateStr" : "PRIMARY",
			......
		},
		{
			"_id" : 2,
			"name" : "192.168.249.130:37018",
			"health" : 1,
			"state" : 2,
			"stateStr" : "SECONDARY",
			......
		},
		{
			"_id" : 3,
			"name" : "192.168.249.130:37019",
			"health" : 1,
			"state" : 2,
			"stateStr" : "SECONDARY",
			......
}
````

6、复制集成员的配置参数

| 参数字段     | 类型   | 取值                          | 说明                                                         |
| ------------ | ------ | ----------------------------- | ------------------------------------------------------------ |
| _id          | 整数   | _id:0                         | 复制集中的标示                                               |
| host         | 字符串 | host:"主机:端口"              | 节点主机名                                                   |
| arbiterOnly  | 布尔值 | arbiterOnly:true              | 是否为仲裁(裁判)节点                                         |
| priority     | 整数   | priority=0\|1                 | 默认1，是否有资格变成主节点，范围0-10000，0永远不会变成主节点 |
| hidden       | 布尔值 | hidden=true\|false，0\|1      | 隐藏，权重必须为0，才可以设置                                |
| votes        | 整数   | votes= 0\|1                   | 投票，是否为投票节点，0 不投票，1投票                        |
| slaveDelay   | 整数   | slaveDelay=3600               | 从库的延迟多少秒                                             |
| buildIndexes | 布尔值 | buildIndexes=true\|false,0\|1 | 主库的索引，从库也创建，_id索引无效                          |

节点说明:

- Primary节点： 可以查询和新增数据
- Secondary节点：只能查询 不能新增 基于priority 权重可以被选为主节点
- Arbiter节点： 不能查询数据 和新增数据 ，不能变成主节点  

7、有仲裁节点复制集搭建

````shell
mkdir -p /data/mongo/data/server4
touch /data/mongo/logs/server4.log
vim /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/cluster/mongo_37020.conf
````

从节点3配置 mongo_37020.conf  

```properties
dbpath=/data/mongo/data/server4
bind_ip=0.0.0.0
port=37020
fork=true
logpath=/data/mongo/logs/server4.log
replSet=heroMongoCluster
```

举例:  

```json
var cfg ={"_id":"heroMongoCluster",
            "protocolVersion" : 1,
            "members":[
                {"_id":1,"host":"192.168.249.130:37017","priority":10},
                {"_id":2,"host":"192.168.249.130:37018","priority":0},
                {"_id":3,"host":"192.168.249.130:37019","priority":5},
                {"_id":4,"host":"192.168.249.130:37020","arbiterOnly":true}
            ]
        };
// 重新装载配置，并重新生成集群节点。
rs.reconfig(cfg)
// 重新查看集群状态
rs.status()
```

或者只是增加了 一个特殊的仲裁节点

注入节点：执行 rs.addArb("IP:端口");

```sql
rs.addArb("192.168.249.130:37020")  
```

# 三、集群搭建

## （一）配置并启动 config 节点

1、相关目录创建

```sh
# 初始化集群数据文件存储目录和日志文件
mkdir shardcluster
cd shardcluster/
mkdir config1 config2 config3 logs clusterconfig
# 初始化日志文件
touch logs/config1.log
touch logs/config2.log
touch logs/config3.log
```

2、配置 并启动config 节点集群 

（1）节点1 config-17017.conf  

```properties
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17017.conf <<-'EOF'
# 数据库文件位置
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/config1
#日志文件位置
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/config1.log
# 以追加方式写入日志
logappend=true
# 是否以守护进程方式运行
fork = true
bind_ip=0.0.0.0
port = 17017
# 表示是一个配置服务器
configsvr=true
#配置服务器副本集名称
replSet=configsvr
EOF
```

（2）节点2 config-17018.conf  

```properties
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17018.conf <<-'EOF'
# 数据库文件位置
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/config2
#日志文件位置
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/config2.log
# 以追加方式写入日志
logappend=true
# 是否以守护进程方式运行
fork = true
bind_ip=0.0.0.0
port = 17018
# 表示是一个配置服务器
configsvr=true
#配置服务器副本集名称
replSet=configsvr
EOF
```

（3）节点3 config-17019.conf  

```properties
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17019.conf <<-'EOF'
# 数据库文件位置
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/config3
#日志文件位置
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/config3.log
# 以追加方式写入日志
logappend=true
# 是否以守护进程方式运行
fork = true
bind_ip=0.0.0.0
port = 17019
# 表示是一个配置服务器
configsvr=true
#配置服务器副本集名称
replSet=configsvr
EOF
```

3、配置脚本

（1）启动集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/start-mongo-config.sh <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17019.conf
echo "start mongo config cluster..."
ps -ef | grep mongodb
EOF
```

（2）授权

````sh
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/start-mongo-config.sh
````

（3）关闭集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/stop-mongo-config.sh  <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/clusterconfig/config-17019.conf
echo "stop mongo config cluster..."
ps -ef | grep mongodb
EOF
```

（4）授权

````sh
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/stop-mongo-config.sh
````

4、配置集群

（1）启动集群

````sh
./start-mongo-config.sh
````

（2）进入任意节点的mongo shell 并添加 配置节点集群

注意use admin  

```bash
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --host=192.168.249.130 --port=17017
```

```sql
use admin
var cfg ={"_id":"configsvr",
        "members":[
            {"_id":1,"host":"192.168.249.130:17017"},
            {"_id":2,"host":"192.168.249.130:17018"},
            {"_id":3,"host":"192.168.249.130:17019"}]
        };
rs.initiate(cfg)
rs.status()
```

## （二）配置 shard1集群

1、shard1集群搭建37017到37019

```sh
# 1）初始化集群数据文件存储目录和日志文件
mkdir server1 server2 server3 shard
touch logs/server1.log logs/server2.log logs/server3.log

# 2）主节点配置 mongo_37017.conf
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37017.conf <<-'EOF'
# 主节点配置
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/server1
bind_ip=0.0.0.0
port=37017
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/server1.log
# 集群名称
replSet=shard1
shardsvr=true
EOF

# 3）从节点1配置 mongo_37018.conf  
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37018.conf <<-'EOF'
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/server2
bind_ip=0.0.0.0
port=37018
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/server2.log
replSet=shard1
shardsvr=true
EOF

# 4）从节点2配置 mongo_37019.conf
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37019.conf <<-'EOF'
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/server3
bind_ip=0.0.0.0
port=37019
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/server3.log
replSet=shard1
shardsvr=true
EOF
```

2、启动集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/start-mongo-shard1.sh <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37019.conf 
echo "start mongo shard1 cluster..."
ps -ef | grep mongodb
EOF
```

3、关闭集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/stop-mongo-shard1.sh <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_37019.conf
echo "stop mongo shard1 cluster..."
ps -ef | grep mongodb
EOF
```

4、授权

````shell
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/start-mongo-shard1.sh
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/stop-mongo-shard1.sh
````

5、启动

````shell
./start-mongo-shard1.sh
````

4、初始化集群命令

启动三个节点 然后进入 Primary 节点 运行如下命令：  

```sh
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongo --host=192.168.249.130 --port=37017
```

```sql
var cfg ={"_id":"shard1",
            "protocolVersion" : 1,
            "members":[
                {"_id":1,"host":"192.168.249.130:37017"},
                {"_id":2,"host":"192.168.249.130:37018"},
                {"_id":3,"host":"192.168.249.130:37019"}
            ]
        }
rs.initiate(cfg)
rs.status()
```

## （三）配置shard2集群

1、shard2集群搭建47017到47019

```sh
# 1）初始化集群数据文件存储目录和日志文件
mkdir server4 server5 server6
touch logs/server4.log logs/server5.log logs/server6.log

# 2）主节点配置 mongo_47017.conf
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47017.conf <<-'EOF'
# 主节点配置
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/server4
bind_ip=0.0.0.0
port=47017
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/server4.log
# 集群名称
replSet=shard2
shardsvr=true
EOF

# 3）从节点1配置 mongo_47018.conf  
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47018.conf <<-'EOF'
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/server5
bind_ip=0.0.0.0
port=47018
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/server5.log
replSet=shard2
shardsvr=true
EOF

# 4）从节点2配置 mongo_47019.conf
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47019.conf <<-'EOF'
dbpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/server6
bind_ip=0.0.0.0
port=47019
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/server6.log
replSet=shard2
shardsvr=true
EOF
```

2、启动集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/start-mongo-shard2.sh <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47019.conf 
echo "start mongo shard1 cluster..."
ps -ef | grep mongodb
EOF
```

3、关闭集群脚本

```bash
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/stop-mongo-shard2.sh <<-'EOF'
#! /bin/bash
clear
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47017.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47018.conf
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongod --shutdown -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/shard/mongo_47019.conf
echo "stop mongo shard1 cluster..."
ps -ef | grep mongodb
EOF
```

4、授权

````shell
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/start-mongo-shard2.sh
chmod 755 /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/stop-mongo-shard2.sh
````

5、启动

````shell
./start-mongo-shard2.sh
````

4、初始化集群命令

启动三个节点 然后进入 Primary 节点 运行如下命令：  

```sh
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongo --host=192.168.249.130 --port=47017
```

```sql
var cfg ={"_id":"shard2",
            "protocolVersion" : 1,
            "members":[
                {"_id":1,"host":"192.168.249.130:47017"},
                {"_id":2,"host":"192.168.249.130:47018"},
                {"_id":3,"host":"192.168.249.130:47019"}
            ]
        }
rs.initiate(cfg)
rs.status()
```

## （四）配置和启动路由节点

1、配置数据

```sh
touch /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/route.log
mkdir route
```

2、route-27017.conf

```properties
tee /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/route/route-27017.conf <<-'EOF'
port=27017
bind_ip=0.0.0.0
fork=true
logpath=/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/logs/route.log
configdb=configsvr/192.168.249.130:17017,1192.168.249.130:17018,192.168.249.130:17019
EOF
```

3、启动路由节点使用 mongos （注意不是mongod）  

```sh
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongos -f /root/rj/mongodb-linux-x86_64-rhel70-4.1.3/shardcluster/route/route-27017.conf
```

4、mongos（路由）中添加分片节点

进入路由mongos  节点

```sh
/root/rj/mongodb-linux-x86_64-rhel70-4.1.3/bin/mongo --port 27017
```

```sql
sh.status()
sh.addShard("shard1/192.168.249.130:37017,192.168.249.130:37018,192.168.249.130:37019");
sh.addShard("shard2/192.168.249.130:47017,192.168.249.130:47018,192.168.249.130:47019");
sh.status()
```

## （五）开启数据库和集合分片

1、继续使用mongos完成分片开启和分片大小设置

```sql
# 为数据库开启分片功能
use admin
db.runCommand( { enablesharding :"myRangeDB"});
# 为指定集合开启分片功能
db.runCommand( { shardcollection : "myRangeDB.coll_shard",key : {_id: 1} } )
```

2、向集合中插入数据测试

通过路由循环向集合中添加数  

```sql
use myRangeDB;
for(var i=1;i<= 1000;i++){
    db.coll_shard.insert({"name":"test"+i,salary:(Math.random()*20000).toFixed(2)});
}
```

3、查看分片情况

```sql
db.coll_shard.stats();
sharded  true
# 可以观察到当前数据全部分配到了一个shard集群上。这是因为MongoDB并不是按照文档的级别将数据散落在各个分片上的，而是按照范围分散的。也就是说collection的数据会拆分成块chunk，然后分布在不同的shard
# 这个chunk很大，现在这种服务器配置，只有数据插入到一定量级才能看到分片的结果
# 默认的chunk大小是64M，可以存储很多文档

# 查看chunk大小：
use config
db.settings.find()
# 修改chunk大小
db.settings.save( { _id:"chunksize", value: NumberLong(128)} )
```

使用hash分片

```sql
use admin
db.runCommand({"enablesharding":"myHashDB"})
db.runCommand({"shardcollection":"myHashDB.coll_shard","key":{"_id":"hashed"}})
```

```sql
use myHashDB;
for(var i=1;i<= 1000;i++){
    db.coll_shard.insert({"name":"test"+i,salary:(Math.random()*20000).toFixed(2)});
}
```

#### 6）验证分片效果

分别进入 shard1 和 shard2 中的数据库 进行验证

# 四、Springboot集成

## （一）MongoTemplate 方式  

1、基于maven新建springboot工程

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

2、配置文件application.properties

```properties
# spring-data配置方式1
#spring.data.mongodb.uri=mongodb://192.168.249.130:27017/testcluster
#spring.data.mongodb.database=hero

# spring-data配置方式2
spring.data.mongodb.host=123.57.135.5
spring.data.mongodb.port=27017
spring.data.mongodb.database=hero
#logging.level.ROOT=DEBUG
```

3、实体

````java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("employee")
public class Employee implements Serializable {
    @Id
    private String id;
    private int empId;
    private String firstName;
    private String lastName;
    private float salary;
}
````

4、注入 MongoTemplate 完成增删改查

```java
@SpringBootTest
public class ClusterTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void add() {
        Employee employee = Employee.builder()
                .id("22").firstName("wang").lastName("benson").empId(2).salary(12200).build();
        mongoTemplate.save(employee);
    }

    @Test
    public void findAll() {
        List<Employee> employees = mongoTemplate.findAll(Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Employee employee = Employee.builder().id("11").build();
        Query query = new Query(Criteria.where("id").is(employee.getId()));
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void findByName() {
        Employee employee = Employee.builder().lastName("hero").build();
        Query query2 = new Query(Criteria.where("lastName").regex("^.*" + employee.getLastName() + ".*$"));
        List<Employee> empList = mongoTemplate.find(query2, Employee.class);
        empList.forEach(System.out::println);
    }

    @Test
    public void update() {
        Employee employee = Employee.builder().id("11").build();
        //使用更新的文档更新所有与查询文档条件匹配的对象
        Query query = new Query(Criteria.where("id").is(employee.getId()));
        UpdateDefinition updateDefinition = new Update().set("lastName", "hero110");
        UpdateResult updateResult = mongoTemplate
                .updateMulti(query, updateDefinition, Employee.class);
        System.out.println("update id:{}" + updateResult.getUpsertedId());
    }

    @Test
    public void del() {
        Employee employee = Employee.builder().lastName("hero110").build();
        Query query = new Query(Criteria.where("lastName").is(employee.getLastName()));
        mongoTemplate.remove(query, Employee.class);
    }
}
```

## （二）MongoRepository 方式  

1、基于maven新建springboot工程

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

2、配置文件application.properties

```properties
# spring-data配置方式1
#spring.data.mongodb.uri=mongodb://123.57.135.5:27017/hero
#spring.data.mongodb.database=hero

# spring-data配置方式2
spring.data.mongodb.host=123.57.135.5
spring.data.mongodb.port=27017
spring.data.mongodb.database=hero
#logging.level.ROOT=DEBUG
```

3、编写实体类 并在实体类上打@Document(“集合名”)

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("employee")
public class Employee implements Serializable {
    @Id
    private String id;
    private int empId;
    private String firstName;
    private String lastName;
    private float salary;
}
```

4、编写 Repository 接口 继承 MongoRepository

```java
public interface EmployeeRepository extends MongoRepository<Employee, String> {}
```

- 方法具体参考:https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
- 如果内置方法不够用 就自己定义 如:定义find|read|get 等开头的方法进行查询

5、从Spring容器中获取Repository对象 进行测试  

```java
@SpringBootTest
public class ClusterTest2 {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void add() {
        Employee employee = Employee.builder()
                .id("11").firstName("liu").lastName("hero").empId(1).salary(10200).build();
        employeeRepository.save(employee);
    }

    @Test
    public void findAll() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);
    }
}
```

