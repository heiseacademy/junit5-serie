package com.svenruppert.junit5.basics.c06.example.services;

public record DeleteEntityResponse<T>(boolean deleted, String message, T value) {
}
