package com.ruiya.cache.redis;

import com.ruiya.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisUtils {

    // 启用pool。支持多线程

    private static JedisPool pool = null;
    static {
        initJedisPool();
    }

    // private static JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);

    // 启用线程池刷新，防止阻塞
    private static ExecutorService service = Executors.newFixedThreadPool(5);

    private static synchronized void initJedisPool() {
        if (pool == null || pool.isClosed()) {
            pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1",
                    6379, Protocol.DEFAULT_TIMEOUT, null, 3);
        }
    }

    /**
     * <p>Title: set</p>
     * <p>Description: redis缓存，默认缓存1小时</p>
     * @param k
     * @param v
     */
    public static void set(final String k, final String v) {
        set(k, v, 3600);
    }

    /**
     * <p>Title: set</p>
     * <p>Description: redis缓存</p>
     * @param k
     * @param v
     * @param seconds
     */
    public static void set(final String k, final String v, final int seconds) {

        service.execute(new Runnable() {

            @Override
            public void run() {
                Jedis jedis = null;
                try {
                    if (pool == null || pool.isClosed()) {
                        initJedisPool();
                    }
                    jedis = pool.getResource();

                    jedis.set(k, v);
                    if(seconds>0) {
                        jedis.expire(k, seconds);
                    }
                } catch(Exception e) {
                    // TODO: handle exception
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

            }
        });
    }

    public static void setObject(final String k, final Object v, final int seconds) {

        service.execute(new Runnable() {

            @Override
            public void run() {
                Jedis jedis = null;
                try {
                    if (pool == null || pool.isClosed()) {
                        pool = new JedisPool(new JedisPoolConfig(), "localhost");
                    }
                    jedis = pool.getResource();

                    jedis.set(k.getBytes("UTF-8"), SerializeUtil.serialize(v));
                    jedis.expire(k, seconds);
                } catch(Exception e) {
                    // TODO: handle exception
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

            }
        });
    }

    public static void del(final String k) {

        service.execute(new Runnable() {

            @Override
            public void run() {
                Jedis jedis = null;
                try {
                    if (pool == null || pool.isClosed()) {
                        pool = new JedisPool(new JedisPoolConfig(), "localhost");
                    }
                    jedis = pool.getResource();
                    jedis.del(k);
                } catch(Exception e) {
                    // TODO: handle exception
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

            }
        });
    }

    public static String get(String k) {

        Jedis jedis = null;
        try {
            if (pool == null || pool.isClosed()) {
                initJedisPool();
            }
            jedis = pool.getResource();
            return jedis.get(k);
        } catch(Exception e) {
            // TODO: handle exception
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }


    public static Object getObject(String k) {

        Jedis jedis = null;
        try {
            if (pool == null || pool.isClosed()) {
                initJedisPool();
            }
            jedis = pool.getResource();
            byte[] value = jedis.get(k.getBytes("UTF-8"));
            return SerializeUtil.serialize(value);
        } catch(Exception e) {
            // TODO: handle exception
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public static void destroy() {
        if (pool != null) {
            pool.destroy();
        }
    }

}
