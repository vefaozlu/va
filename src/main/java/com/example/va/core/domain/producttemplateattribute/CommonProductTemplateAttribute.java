package com.example.va.core.domain.producttemplateattribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CommonProductTemplateAttribute implements ProductTemplateAttribute {
    private Integer id;
    private String name;
    private ProductTemplateAttributeType type;
    private boolean allowNull;
    private boolean calculated;
    private String formula;
    private Map<String, String> variables;
    private String conditions;
    private boolean isConstant;
    private String defaultValue;
    private Integer productTemplateId;
    private Integer stepId;
}