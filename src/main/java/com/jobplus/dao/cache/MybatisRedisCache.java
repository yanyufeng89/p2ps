package com.jobplus.dao.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MybatisRedisCache implements Cache {

	private static Log logger = LogFactory.getLog(MybatisRedisCache.class);
	
	private static GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

	private Jedis redisClient = createClient();
	
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public MybatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug("***********************MybatisRedisCache:id=" + id);
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public int getSize() {
		return Integer.valueOf(redisClient.dbSize().toString());
	}

	public void putObject(Object key, Object value) {
		logger.debug("***********************putObject:" + key + "=" + value);
		redisClient.set(serializer.serialize(key.toString()), serializer.serialize(value));
	}

	public Object getObject(Object key) {
		Object value = serializer.deserialize(redisClient.get(serializer.serialize(key.toString())));
		logger.debug("***********************getObject:" + key + "=" + value);
		return value;
	}

	public Object removeObject(Object key) {
		return redisClient.expire(serializer.serialize(key.toString()), 0);
	}

	public void clear() {
		redisClient.flushDB();
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	protected static Jedis createClient() {
		try {
			JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.0.39");
			return pool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("***********************初始化redis连接池错误");
	}

}
