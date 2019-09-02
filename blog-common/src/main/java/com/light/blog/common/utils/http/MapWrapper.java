package com.light.blog.common.utils.http;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 对原生Map的 增强
 */
public class MapWrapper {

    protected Map target;

    public MapWrapper(Map map) {
        this.target = map;
    }

    public static MapWrapper wrap(Map map) {
        return new MapWrapper(map);
    }

    public static MapWrapper ofEmptyMap() {
        return new MapWrapper(new HashMap());
    }


    /**
     * 用keypath获取Map的值
     * 使用案例：
     * 以下数据结构 可以通过 prop1.child 来直接获取 "val"
     * {"prop1":{"child":"val"}}
     *
     * @param keyPath
     * @param convert 用于处理类型转换 (如果不提供，则直接强转)
     * @return
     */
    public <T> T get(String keyPath, Function<Object, T> convert) {
        String[] keys = null;
        if (!keyPath.contains(".")) {
            keys = new String[]{keyPath};
        } else {
            keys = keyPath.split("\\.");
        }
        Object obj = target.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            if (obj instanceof Map) {
                obj = ((Map) obj).get(keys[i]);
            }else if (obj instanceof List){
                try {
                    int index = Integer.valueOf(keys[i]);
                    obj = ((List)obj).get(index);
                }catch (NumberFormatException e){
                    return null;
                }

            }
            else {
                return null;
            }
        }
        return obj == null ? null : convert == null ? (T) obj : convert.apply(obj);
    }

    public String getString(String keyPath) {
        return get(keyPath, String::valueOf);
    }

    public Long getLong(String keyPath) {
        return get(keyPath, a -> Long.valueOf(a.toString()));
    }

    public Integer getInteger(String ketPath) {
        return get(ketPath, a -> Integer.valueOf(a.toString()));
    }

    public <K> List<K> getJsonAsList(String keyPath, Class<K> arrayType) {
        String jsonStr = get(keyPath, String::valueOf);
        return JSON .parseArray(jsonStr, arrayType);
    }

    public Map getJsonAsMap(String keyPath) {
        String jsonStr = get(keyPath, String::valueOf);
        return JSON.parseObject(jsonStr, Map.class);
    }

    public Map getMap(String keyPath) {
        return get(keyPath, m -> (Map) m);
    }

    public List getList(String keyPath) {
        return get(keyPath, l -> (List) l);
    }


    /**
     * 把map中已存在的 value 复制到指定的key
     * 计划编写
     * 1. 拿到fromKeyPath的值
     * 2. 分析toKeyPath, 执行put
     */
    public void copy(String fromKeyPath, String toKeyPath) {

    }

    /**
     * 1.待实现 移动key的位置
     *
     * @param fromKeyPath
     * @param toKeyPath
     */
    public void move(String fromKeyPath, String toKeyPath) {

    }

    public MapWrapper put(Object key, Object value) {
        target.put(key, value);
        return this;
    }

    public MapWrapper putIfNotNull(Object key, Object value) {
        if (value != null) {
            target.put(key, value);
        }
        return this;
    }


    public Map getTarget() {
        return target;
    }


}
