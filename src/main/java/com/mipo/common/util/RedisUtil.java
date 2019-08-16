package com.mipo.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

    private static RedisClient client = new RedisClient();


    public static void put(final String key, final Object value) {
        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                String json = JSON.toJSONString(value);
                jedis.set(key, json);
                return null;
            }
        });
    }

    public static void put(final String key, final Object value, final Integer seconds) {

        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                String json = JSON.toJSONString(value);
                jedis.set(key, json);
                jedis.expire(key, seconds);
                return null;
            }
        });

    }

    public static <T> T get(final String key, final Class<T> clazz) {
        return client.callRedis(new RedisHandler<T>() {
            @Override
            public T execute(Jedis jedis) {
                String value = jedis.get(key);
                return StringUtils.isBlank(value) ? null : JSONObject.parseObject(value, clazz);
            }
        });
    }

    public static Long del(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    /**
     * get key exist time
     *
     * @param key
     * @return
     */
    public static Long ttl(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    /**
     * 设置此key的生存时间，单位秒(s)
     */
    public static void setExpire(final String key, final int seconds) {
        client.callRedis(new RedisHandler<Object>() {
            @Override
            public Object execute(Jedis jedis) {
                jedis.expire(key, seconds);
                return null;
            }
        });
    }

    public static Boolean exists(final String key) {
        return client.callRedis(new RedisHandler<Boolean>() {
            @Override
            public Boolean execute(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    public static Long incr(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    public static Long incrBy(final String key, final long num) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incrBy(key, num);
            }
        });
    }

    public static Double incrByFloat(final String key, final double num) {
        return client.callRedis(new RedisHandler<Double>() {
            @Override
            public Double execute(Jedis jedis) {
                return jedis.incrByFloat(key, num);
            }
        });
    }

    /**
     * set if not exists object to redis
     * @param key 键
     * @param value 值
     * @param seconds 有效期秒数
     * @return Integer reply, specifically: 1 if the key was set；0 if the key was not set
     */
    public static Long setnx(final String key, final Object value, final Integer seconds){
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                String json = JSON.toJSONString(value);
                Long ret = jedis.setnx(key, json);
                jedis.expire(key, seconds);
                return ret;
            }
        });
    }

    public static Long decr(final String key) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    public static Long decrBy(final String key, final long num) {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.decrBy(key, num);
            }
        });
    }

    /**
     * 查询当前KEY数量
     * @return
     */
    public static Long dbSize() {
        return client.callRedis(new RedisHandler<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.dbSize();
            }
        });
    }

    static class RedisClient {

        private static JedisPool jedisPool = SpringContextUtil.getContext().getBean(JedisPool.class);

        public <T> T callRedis(RedisHandler<T> handler) {
            Jedis jedis = jedisPool.getResource();
            try {
                return (T) handler.execute(jedis);
            } finally {
                jedis.close();
            }
        }

    }

    interface RedisHandler<T> {

        /**
         * Redis执行方法
         * @param jedis
         * @return
         */
        T execute(Jedis jedis);

    }

}


