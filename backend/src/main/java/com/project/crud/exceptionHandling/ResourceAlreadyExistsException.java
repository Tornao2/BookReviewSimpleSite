package com.project.crud.exceptionHandling;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String table, String id) {
        super("Resource of id: " + id + ", in the table " + table + " already exists");
    }
}
