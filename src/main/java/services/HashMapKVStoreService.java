package main.java.services;

import main.java.interfaces.KVStore;

import java.util.HashMap;
import java.util.Map;

public class HashMapKVStoreService implements KVStore {

    HashMap<String, Map<String, String>> dbStore;

    HashMap<String, String> dataTypeList;

    public HashMapKVStoreService() {
        dbStore = new HashMap<>();
        dataTypeList = new HashMap<>();

    }

    @Override
    public Map<String, String> getKey(String key) {
        if (dbStore.containsKey(key)) {
            return dbStore.get(key);
        }
        return null;
    }

    @Override
    public boolean setKey(String key, Map<String, String> value) {
        dbStore.put(key, value);
        return true;
    }

    @Override
    public boolean deleteKey(String key) {
        dbStore.remove(key);
        return true;
    }

    @Override
    public void addDatatype(String key, String value) {
        dataTypeList.putIfAbsent(key, value);
    }

    @Override
    public boolean checkDataType(String key, String dataType) {
        String expectedDatatype =  dataTypeList.getOrDefault(key, null);
        return dataType.equals(expectedDatatype);
    }
}
