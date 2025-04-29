package com.example.va.core.service.producttemplateattribute._common.dto;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTemplateAttributeDto {
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
