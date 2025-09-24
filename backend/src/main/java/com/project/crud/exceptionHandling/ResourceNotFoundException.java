package com.project.crud.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String table, String id) { super(table + " of id: " + id + ", is not found"); }
}