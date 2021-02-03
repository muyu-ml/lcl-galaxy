--#如果key已经不存在，说明已经被解锁，直接发布（publish）redis消息
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('publish', KEYS[2], ARGV[1]);
    return 1;
end;
--# key和field不匹配，说明当前客户端线程没有持有锁，不能主动解锁。
if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then
    return nil;
end;
--# 将value减1
local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1);
--# 如果counter>0说明锁在重入，不能删除key
if (counter > 0) then
    redis.call('pexpire', KEYS[1], ARGV[2]);
    return 0;
    --# 删除key并且publish 解锁消息 "
else
    redis.call('del', KEYS[1]);
    redis.call('publish', KEYS[2], ARGV[1]);
    return 1;
end;
return nil;