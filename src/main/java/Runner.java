package main.java;


import main.java.dao.Command;
import main.java.dao.Pair;
import main.java.interfaces.KVStore;
import main.java.services.DataTypeIdentifierService;
import main.java.services.HashMapKVStoreService;
import main.java.services.StorageService;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class Runner {

    public static void main(String[] args) throws Exception {
        System.out.println("-----HELLO-----");
        HashMapKVStoreService kvStore = new HashMapKVStoreService();
        DataTypeIdentifierService dataTypeIdentifierService = new DataTypeIdentifierService();
        StorageService storageService = new StorageService(kvStore, dataTypeIdentifierService);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean runner = true;
        while (runner) {
            String input = br.readLine();
            Command command = null;
            try {
                command = new Command(input);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (command.getCommandName()) {
                case SET -> {
                    ArrayList<Pair<String, String>> kvPairs = new ArrayList<>();
                    for (int i = 1; i < command.getParams().size(); i = i + 2) {
                        kvPairs.add(new Pair<String, String>(command.getParams().get(i), command.getParams().get(i + 1)));
                    }
                    boolean res = storageService.setKey(command.getParams().get(0), kvPairs);
                    System.out.println(res);
                }
                case GET -> {
                    Map<String, String> mapValue = storageService.getValue(command.getParams().get(0));
                    if (mapValue == null) {
                        System.out.println("NOT FOUND!!");
                    }
                    System.out.println(mapValue);
                }
                case DELETE -> {
                    boolean res = storageService.deleteKey(command.getParams().get(0));
                    System.out.println(res);
                }
                case EXIT -> {
                    runner = false;
                    System.out.println("Exiting!!");
                }
            }
        }
    }
}
