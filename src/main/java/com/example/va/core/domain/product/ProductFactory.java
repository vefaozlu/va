package com.example.va.core.domain.product;

import java.util.Map;


public interface ProductFactory {
    Product createProduct(
            Integer id,
            Map<String, Object> attributes,
            Integer productTemplateId
    );
} 