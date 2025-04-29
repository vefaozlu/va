package com.example.va.core.domain.producttemplateattribute;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonProductTemplateAttributeFactory implements ProductTemplateAttributeFactory {
    @Override
    public ProductTemplateAttribute createProductTemplateAttribute(Integer id, String name,
                                                                   ProductTemplateAttributeType type, boolean allowNull,
                                                                   boolean calculated, String formula,
                                                                   Map<String, String> variables, String conditions,
                                                                   boolean isConstant, String defaultValue,
                                                                   Integer productTemplateId, Integer stepId) {
        return new CommonProductTemplateAttribute(id, name, type, allowNull, calculated, formula, variables, conditions,
                isConstant, defaultValue, productTemplateId, stepId);
    }
} 