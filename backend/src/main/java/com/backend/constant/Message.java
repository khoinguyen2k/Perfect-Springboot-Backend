package com.backend.constant;

public class Message {
    public static String notExist(String entityType, String identifier) {
        return "There's no " + entityType + " with username/id " + identifier;
    }

    public static final String SUCCESSFUL = "Successful!";

}
