package main.java.services;

import main.java.dao.Pair;
import main.java.interfaces.KVStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageService {

    private KVStore dataStore;
    private DataTypeIdentifierService dataTypeIdentifierService;

    public StorageService(KVStore dataStore, DataTypeIdentifierService dataTypeIdentifierService) {
        this.dataTypeIdentifierService = dataTypeIdentifierService;
        this.dataStore = dataStore;
    }

    public boolean setKey(String key, ArrayList<Pair<String, String>> values) throws Exception {
        Map<String,String> map = new HashMap<>();
        for (Pair<String,String> pair: values) {
            checkAndAddDataType(pair.getKey(), pair.getValue());
            map.put(pair.getKey(), pair.getValue());
        }
        return dataStore.setKey(key, map);
    }

    public Map<String, String> getValue(String key) {
        Map<String,String> getValue = dataStore.getKey(key);
        return getValue;
    }

    public boolean deleteKey(String key) {
        return dataStore.deleteKey(key);
    }

    public void checkAndAddDataType(String key, String value) throws Exception {
        String dataType = dataTypeIdentifierService.getDataType(value);
        dataStore.addDatatype(key, dataType);
        if (!dataStore.checkDataType(key, dataType)) {
            throw new Exception("Datatype incorrect for key "+ key);
        }
    }
}
