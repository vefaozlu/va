package com.example.va.core.domain.product;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonProductFactory implements ProductFactory {
    @Override
    public Product createProduct(
            Integer id,
            Map<String, Object> attributes,
            Integer productTemplateId
    ) {
        return new CommonProduct(
                id,
                attributes,
                productTemplateId
        );
    }
} 