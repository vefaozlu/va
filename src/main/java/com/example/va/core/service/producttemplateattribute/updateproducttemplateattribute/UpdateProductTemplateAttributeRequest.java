package com.example.va.core.service.producttemplateattribute.updateproducttemplateattribute;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class UpdateProductTemplateAttributeRequest {
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