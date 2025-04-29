package com.example.va.core.service.producttemplateengine.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CalculateResponse {
    private Map<String, Object> results;
}
