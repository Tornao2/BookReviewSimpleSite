package com.project.crud.exceptionHandling;

public class ForeignKeyFoundException extends RuntimeException {
    public ForeignKeyFoundException(String table, String id, String otherTable) {
        super("Resource from table " + table + " of id: " + id + ", is being used in table " + otherTable);
    }
}
