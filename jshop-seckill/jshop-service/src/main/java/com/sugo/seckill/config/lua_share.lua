-- 引入 redis.lua
local redis = require "resty.redis"
-- new 一个 redis 对象
local red = redis:new()

-- 基于内存字典实现缓存
-- 添加缓存方法
function set_to_cache(key,value,expritime)
    if not expritime then
        expritime = 0
    end
    -- 获取本地内存字典对象
    local ngx_cache = ngx.shared.ngx_cache
    -- 添加本地缓存数据
    local succ,err,forcible = ngx_cache:set(key,value,expritime)
    return succ
end

-- 获取缓存方法
function get_from_cache(key)
    -- 获取本地内存字典对象
    local ngx_cache = ngx.shared.ngx_cache
    -- 从本地内存字典中获取数据
    local value = ngx_cache:get(key)
    if not value then
        -- 从 redis 获取缓存数据
        local rev,err = get_from_redis(key)
        if not rev then
            ngx.say("redis cache not exsists",err)
            return
        end
        -- 添加本地缓存数据
        set_to_cache(key,rev,60)
    end
    return value
end



-- 向 redis 添加缓存
function set_to_redis(key,value)
    -- 设置 redis 超时时间
    red:set_timeout(100000)
    -- 连接 redis 服务器
    local ok,err = red:connect("172.20.10.14",6379)
    -- 判断连接是否 OK
    if not ok then
        ngx.say("failed to connect:",err)
        return
    end
    -- 向 redis 添加缓存数据
    local ok,err = red:set(key,value)
    if not ok then
        ngx.say("failed set to redis:",err)
        return
    end
    return ok;
end

-- 从 redis 获取缓存数据
function get_from_redis(key)
    -- 设置 redis 超时时间
    red:set_timeout(100000)
    -- 连接 redis 服务器
    local ok,err = red:connect("172.20.10.14",6379)
    -- 判断连接是否 OK
    if not ok then
        ngx.say("failed to connect:",err)
        return
    end
    -- 从 redis 获取缓存数据
    local res,err = red:get(key)
    if not res then
        ngx.say("failed get to redis:",err)
        return
    end
    -- 打印
    ngx.say("get cache data from redis...............")
    return res
end

-- 实现缓存业务
-- 判断本地内存字典是否具有缓存数据，如果没有访问后端服务
-- 先获取请求参数
local params = ngx.req.get_uri_args()
local id = params.id
-- 先从本地内存字典获取缓存数据
local goods = get_from_cache("seckill_goods_"..id)
-- 如果内存字典缓存数据不存在
if goods == nil then
    -- 从后端服务器查询
    local res = ngx.location.capture("/seckill/goods/detail/"..id)
    -- 获取请求数据body
    goods = res.body
    -- 添加本地缓存数据
    set_to_cache("seckill_goods_"..id,goods,60)
end
-- 返回缓存结构
ngx.say(goods)