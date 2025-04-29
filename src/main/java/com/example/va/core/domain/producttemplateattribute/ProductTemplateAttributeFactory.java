package com.example.va.core.domain.producttemplateattribute;

import java.util.Map;

public interface ProductTemplateAttributeFactory {
    ProductTemplateAttribute createProductTemplateAttribute(
        Integer id,
        String name,
        ProductTemplateAttributeType type,
        boolean allowNull,
        boolean calculated,
        String formula,
        Map<String, String> variables,
        String conditions,
        boolean isConstant,
        String defaultValue,
        Integer productTemplateId,
        Integer stepId
    );
} 