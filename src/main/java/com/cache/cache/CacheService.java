
package com.cache.cache;

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
public interface CacheService {

	<V> V getCache(String key, String cacheName);

	void cacheRemove(String key, String cacheName);

	<V> void cachePut(String key, V value, String cacheName);
}
