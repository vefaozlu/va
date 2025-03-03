package com.example.va.infrastructure;

import org.springframework.http.ResponseEntity;

public interface Command<I ,O> {
    ResponseEntity<O> execute(I request);
}
