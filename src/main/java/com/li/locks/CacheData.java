package com.li.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 设计缓存系统
 */
public class CacheData {

    private ReadWriteLock rwlock = new ReentrantReadWriteLock();

    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {

    }

    public Object getData(String key) {

        rwlock.readLock().lock();
        Object value = null;
        try {

            value = cache.get(key);

            if (value == null) {
                rwlock.readLock().unlock();
                rwlock.writeLock().lock();
                try {
                    if (value == null){

                        value = "querydb";
                        cache.put(key, value);
                    }
                } finally {

                    rwlock.writeLock().unlock();
                }
                rwlock.readLock().lock();
            }
        } finally {

            rwlock.readLock().unlock();
        }
        return value;

    }

    public void setData(String key, Object value) {

        try {
            rwlock.writeLock().lock();

            cache.put(key,value);
        } finally {

            rwlock.writeLock().unlock();
        }

    }

    public void clear(){

        try {

            rwlock.writeLock().lock();
            cache.clear();
        } finally {

            rwlock.writeLock().unlock();
        }
    }
}
