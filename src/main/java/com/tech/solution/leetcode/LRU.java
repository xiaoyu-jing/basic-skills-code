package com.tech.solution.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/3/27
 * 洋钱罐-二面算法
 * 【LRU Cache 实现】
 *
 * 题目描述：给定空间大小，实现一个基于LRU的缓存方案，基本方法与签名如下。
 * 标准解法就是 LinkedHashMap,不过对于好多同学来讲，没有看过这个类的实现
 *
 * public class LRU <K, V>{
 *     int size = 0;
 *     public LRU(int size){
 *         this.size = size;
 *     }
 *     interface Loader<K, V> {
 *         V load(K key);
 *     }
 *     public V getData(K key, Loader<K, V> loader){
 *         // 实现这个地方的代码即可， 数据结构自选
 *     }
 *
 * }
 */
public class LRU<K, V>{
    private final int cacheSize;
    private final LinkedHashMap<K, V> cacheMap;

    public LRU(int cacheSize){
        this.cacheSize = cacheSize;
        // 第三个参数accessOrder 为true 表示LinkedHashMap按照访问顺序排序，最近访问的在头部，最久访问的在尾部
        this.cacheMap = new LinkedHashMap<K, V>(16,0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                // 当map中的元素数量超过指定阈值时，自动删除最久未使用的元素
                return size() > LRU.this.cacheSize;
            }
        };
    }

    // 加载器接口
    interface Loader<K, V> {
        V load(K key);
    }

    public V getData(K key, Loader<K, V> loader){
        // 实现这个地方的代码即可， 数据结构自选

        V value = cacheMap.get(key);
        if(value == null){
            // 如果缓存中不存在，则从loader加载数据并放入缓存
            // 通过同步器，防止并发问题
            synchronized (this){
                value = loader.load(key);
                cacheMap.put(key,value);
            }
        }
        // 将访问的元素移到map的头部，表示最近访问过
        cacheMap.remove(key);
        cacheMap.put(key,value);
        return value;
    }

    public static void main(String[] args){
        LRU<Integer, String> lru = new LRU<>(2);
        Loader<Integer, String> loader = key -> "Value for " + key;

        System.out.println(lru.getData(1,loader));
        System.out.println(lru.getData(2,loader));
        System.out.println(lru.getData(1,loader));
        System.out.println(lru.getData(3,loader));
        System.out.println(lru.getData(2,loader));

    }

    /**
     * 【代码解读】
     *
     * 在上面的代码中，getData方法首先尝试从缓存cacheMap中获取数据。如果数据不存在，则通过loader加载数据，并将其放入缓存中。
     * 无论数据是否已存在于缓存中，都会通过cacheMap.remove(key)和cacheMap.put(key, value)将访问的元素移到LinkedHashMap的头部，以表示最近访问过。
     *
     * 注意，我们在LinkedHashMap的构造函数中设置了accessOrder参数为true，这样LinkedHashMap就会按照访问顺序来排序元素，
     * 最近访问的元素会被放在头部，最久访问的元素会被放在尾部。当LinkedHashMap的大小超过cacheSize时，removeEldestEntry方法会被调用，
     * 并删除最久未使用的元素。
     */

}
