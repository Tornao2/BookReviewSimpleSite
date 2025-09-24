package com.project.crud.exceptionHandling;

public class ForeignKeyNotFoundException extends RuntimeException {
    public ForeignKeyNotFoundException(String table, String id, String otherTable) {
        super("No foreign key in the table " + otherTable + " was found that would allow for creation" +
                "in table " + table + ", of a resource with foreign id: " + id);
    }
}
