package com.example.va.core.domain.producttemplateattribute;

import java.util.Map;

public interface ProductTemplateAttribute {
    Integer getId();
    String getName();
    ProductTemplateAttributeType getType();
    boolean isAllowNull();
    boolean isCalculated();
    String getFormula();
    Map<String, String> getVariables();
    String getConditions();
    String getDefaultValue();
    boolean isConstant();
    Integer getProductTemplateId();
    Integer getStepId();
} 