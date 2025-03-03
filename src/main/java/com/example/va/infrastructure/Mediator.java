package com.example.va.infrastructure;

import org.springframework.http.ResponseEntity;

public interface Mediator {

    <I, O> ResponseEntity<O> executeCommand(I input);
}
