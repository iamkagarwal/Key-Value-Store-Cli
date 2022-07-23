package main.java.services;

public class DataTypeIdentifierService {

    public String getDataType(String value) {
        String datatype = null;

        if (value.matches("true|false")) {
            datatype = String.class.getSimpleName();
        } else if (value.matches("\\d+")){
            datatype = Integer.class.getSimpleName();
        } else if (value.matches("\\d*[.]\\d+")) {
            datatype = double.class.getSimpleName();
        } else {
            datatype = String.class.getSimpleName();
        }
        return datatype;
    }
}
