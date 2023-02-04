package cn.codedan.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisCache
 * @Description: TODO
 * @Author: codedan
 * @Date: 2022/12/13 16:36
 * @Version: 1.0.0
 **/
@SuppressWarnings(value = {"unchecked","rawtypes"})
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer，String、实体类
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 缓存基本的对象，Integer，String、实体类
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    /**
     * 设置有效时间
     * @param key
     * @param timeout
     * @return
     */
    public boolean expire(final  String key, final long timeout){
        return expire(key,timeout,TimeUnit.SECONDS);
    }

    public boolean expire(final  String key, final long timeout,final TimeUnit timeUnit){
        return redisTemplate.expire(key,timeout,timeUnit);
    }

    /**
     * 获取缓存的基本对象
     * @param key
     * @return
     * @param <T>
     */
    public <T> T getCahcheObject(final String key){
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     * @param key
     * @return
     */
    public boolean deleteObject(final String key){
        return redisTemplate.delete(key);
    }

    public long deleteObject(final Collection collection){
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存list
     * @param key
     * @param dataList
     * @return
     * @param <T>
     */
    public <T> long setCacheList(final  String key, final List<T> dataList){
        Long count = redisTemplate.opsForList().rightPushAll(key,dataList);
        return count == null ? 0 :count;
    }

    public <T> List<T> getCacheList(final  String key){
        return redisTemplate.opsForList().range(key,0,-1);
    }
















}
