package main.java.enums;

public enum CommandType {
    GET("get"), SET("set"), DELETE("delete"), EXIT("exit");

    private String type;

    CommandType(String s) {
        this.type = s;
    }

    public String getType(){
        return this.type;
    }
}
