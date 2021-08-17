package cn.edu.bistu.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapService extends HashMap<String, Object> {

    public static MapService map() {
        return new MapService();
    }

    public MapService putMap(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public <T> T getVal(String key, Class<T> clazz) {
        T o = (T) super.get(key);
        return o;
    }

    //public void removeList(String[] list) {
    //    Set<String> keySet = this.keySet();
    //    for (String key : keySet) {
    //        int index = Arrays.binarySearch(list, key);
    //        if(index != -1) {
    //            this.remove(key);
    //        }
    //    }
    //}

}
