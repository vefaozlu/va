package com.example.va.core.service.product.createproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private Integer id;
    private Map<String, Object> calculationResult;
} 