package main.java.dao;

import main.java.enums.CommandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private CommandType commandName;

    private ArrayList<String> params;

    public Command(String command) throws Exception {
        final List<String> inputStream = Arrays.stream(command.trim().split(" "))
                .map(String::trim)
                .filter(a -> a.length() > 0).collect(Collectors.toList());

        try {
            commandName = CommandType.valueOf(inputStream.get(0).toUpperCase());
        } catch (Exception e){
            throw new Exception("Illegal command " +inputStream.get(0));
        }

        inputStream.remove(0);
        params = (ArrayList<String>) inputStream;
        if ((params.size()-1)%2!=0) {
            throw new Exception("Value missing for a key");
        }
    }

    public CommandType getCommandName() {
        return this.commandName;
    }

    public ArrayList<String> getParams() {
        return this.params;
    }
}
