package com.svenruppert.junit5.basics.c06.example.services;

public record CreateEntityResponse<T>(boolean created, String message, T entity) {
}
