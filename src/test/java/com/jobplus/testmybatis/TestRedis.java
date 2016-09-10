package com.jobplus.testmybatis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.User;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类

@ContextConfiguration(locations = { "classpath:spring-application.xml" })
public class TestRedis {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestRedis.class);
	@Resource
	private RedisTemplate redisTemplate;

	@Test
	public void test1() {
		User user = new User();
		user.setUserid(10012);
		user.setUsername("ananWang");
		Account a = new Account();
		a.setUserid(100212);
		ValueOperations valueops = redisTemplate.opsForValue();
		valueops.set("",user);
		valueops.set(String.valueOf(a.getUserid()), a);
		//redisTemplate.s
		logger.info(JSON.toJSONString(valueops.get(String.valueOf(user.getUserid()))));
		redisTemplate.delete(String.valueOf(a.getUserid()));
	}
	
	public void saveUser(final User user) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getUserid()),
                               redisTemplate.getStringSerializer().serialize(user));
                return null;
            }
        });
    }

    public User getUser(final long id) {
        return (User) redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    User user= (User) redisTemplate.getStringSerializer().deserialize(value);
                    return user;
                }
                return null;
            }
        });
    }

}
