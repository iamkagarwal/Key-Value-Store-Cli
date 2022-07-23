package main.java.interfaces;

import java.util.Map;

public interface KVStore {

    Map<String,String> getKey(String key);

    boolean setKey(String key, Map<String, String> value);

    boolean deleteKey(String key);

    void addDatatype(String key, String dataType);

    boolean checkDataType(String key, String dataType);
}
