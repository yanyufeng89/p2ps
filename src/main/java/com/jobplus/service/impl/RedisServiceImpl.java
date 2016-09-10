package com.jobplus.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.jobplus.service.IRedisService;

/**
 * 封装redis 缓存服务器服务实现
 *
 * @author hk
 *         <p>
 *         2012-12-16 上午3:09:18
 */
@Service(value = "redisService")
public class RedisServiceImpl implements IRedisService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    private RedisTemplate redisTemplate;

    private static GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

    /**
     * set value to redis
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * set value to redis
     *
     * @param key
     * @param object
     */
    public void set(String key, Object object) {
        this.set(key.getBytes(), serializer.serialize(object), 0L);
    }

    /**
     * set value to redis
     *
     * @param key
     * @param value
     * @param liveTime 超时时间
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * set value to redis
     *
     * @param key
     * @param value
     * @param liveTime 超时时间
     */
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }


    /**
     * delete value from redis
     *
     * @param pattern
     * @return
     */
    public long delByPattern(String pattern) {
        if (!StringUtils.isEmpty(pattern)) {
            return del(this.keys(pattern));
        }
        return 0;
    }

    /**
     * delete value from redis
     *
     * @param keys
     */
    public long del(final Set<String> keys) {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (String key : keys) {
                    result = connection.del(key.getBytes());
                }
                return result;
            }
        });
    }

    /**
     * delete value from redis
     *
     * @param keys
     */
    public long del(final String... keys) {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }


    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public Object getObject(final byte[] key) {
        return redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return serializer.deserialize(connection.get(key));
            }
        });
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public Object getObject(final String key) {
        return getObject(key.getBytes());
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(key.getBytes()), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
    }

    /**
     * get key set from redis
     *
     * @param pattern
     * @return
     */
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);
    }


    /**
     * exists key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }


    /**
     * clear all
     *
     * @return
     */
    public String flushDB() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * redis key size
     *
     * @return
     */
    public long dbSize() {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * ping
     *
     * @return
     */
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }
    
    /**
     * 缓存list
     * @param k
     * @param v
     * @param time
     * @return
     */
    public boolean setList(String k, List v, long time) {
        String key =  k;
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            long l = listOps.rightPushAll(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     * @param k
     * @param v
     * @return
     */
    public boolean setList(String k, List v) {
       return setList(k, v, -1);
    }

    /**
     * 获取list缓存
     * @param k
     * @param start
     * @param end
     * @return
     */
    public List getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            return listOps.range( k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" +  k + ", error[" + t + "]");
        }
        return null;
    }
    
    

    /**
     * 获取总条数, 可用于分页
     * @param k
     * @return
     */
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps =  redisTemplate.opsForList();
            return listOps.size( k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" +  k + "], error[" + t + "]");
        }
        return 0;
    }


	@Override
	public List getList(String k) {
		  try {
	            ListOperations<String, String> listOps =  redisTemplate.opsForList();
	            return listOps.range( k, 0, getListSize(k));
	        } catch (Throwable t) {
	            logger.error("获取list缓存失败key[" +  k + ", error[" + t + "]");
	        }
	        return null;
	}

}
