package com.svenruppert.junit5.basics.c06.example.services;

public record UpdateEntityResponse<T>(boolean updated, String message, T value) {
}
