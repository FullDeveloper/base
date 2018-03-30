
package com.cache.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.cache.cache.CacheService;

/**
 * 创建时间：2018年3月29日 下午11:57:57
 * 
 * 项目名称：cache-redis
 * 
 * @author zhourunbin
 * 
 * @version 1.0
 * 
 *          Description：
 */
@Service
public class RedisCacheServiceImpl implements CacheService {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public <V> V getCache(String key, String cacheName) {
		ValueWrapper valueWrapper = cacheManager.getCache(cacheName).get(key);
		return (V) (valueWrapper == null ? null : valueWrapper.get());
	}

	@Override
	public void cacheRemove(String key, String cacheName) {
		cacheManager.getCache(cacheName).evict(key);
	}

	@Override
	public <V> void cachePut(String key, V value, String cacheName) {
		cacheManager.getCache(cacheName).put(key, value);
	}

}
