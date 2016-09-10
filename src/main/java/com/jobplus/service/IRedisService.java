package com.jobplus.service;

import java.util.List;
import java.util.Set;

/**
 * redis 的操作开放接口
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 *
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @version 1.0 <br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @creatdate Aug 18, 2016 11:18:22 AM <br>
 */
public interface IRedisService {

    /**
     * set value to redis
     *
     * @param key
     * @param value
     */
    public void set(String key, String value);

    /**
     * set value to redis
     *
     * @param key
     * @param object
     */
    public void set(String key, Object object);

    /**
     * set value to redis
     *
     * @param key
     * @param value
     * @param liveTime 超时时间
     */
    public void set(final byte[] key, final byte[] value, final long liveTime);

    /**
     * set value to redis
     *
     * @param key
     * @param value
     * @param liveTime 超时时间
     */
    public void set(String key, String value, long liveTime);


    /**
     * delete value from redis
     *
     * @param pattern
     * @return
     */
    public long delByPattern(String pattern);

    /**
     * delete value from redis
     *
     * @param keys
     */
    public long del(final Set<String> keys);

    /**
     * delete value from redis
     *
     * @param keys
     */
    public long del(final String... keys);

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public Object getObject(final byte[] key);

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public Object getObject(final String key);

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public String get(final String key);

    /**
     * get key set from redis
     *
     * @param pattern
     * @return
     */
    public Set keys(String pattern);


    /**
     * exists key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key);


    /**
     * clear all
     *
     * @return
     */
    public String flushDB();

    /**
     * redis key size
     *
     * @return
     */
    public long dbSize();

    /**
     * ping
     *
     * @return
     */
    public String ping();
    
    public long getListSize(String k);
    
    public List<String> getList(String k);
    
    public List<String> getList(String k, long start, long end);
    
    public boolean setList(String k, List<String> v) ;
    
    public boolean setList(String k, List<String> v, long time);
    

}