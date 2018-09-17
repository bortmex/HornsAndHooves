package ru.javaproject.hornsandhooves.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Status {
    STATUS_PENDING( "STATUS_PENDING"),
    STATUS_INTHEWORK_PERSON("STATUS_INTHEWORK_PERSON"),
    STATUS_INTHEWORK_DEPARTMENT("STATUS_INTHEWORK_DEPARTMENT"),
    STATUS_FINISHED("STATUS_FINISHED");

    private String status;

    private static Map<String, Status> ratings = new HashMap<String, Status>();
    static {
        for (Status r : EnumSet.allOf(Status.class)) {
            ratings.put(r.toString(), r);
        }
    }

    private static Status getRating(String rating) {
        return ratings.get(rating);
    }

    private Status(String rating) {
        this.status = rating;
    }

    @Override
    public String toString() {
        return status;
    }
}
