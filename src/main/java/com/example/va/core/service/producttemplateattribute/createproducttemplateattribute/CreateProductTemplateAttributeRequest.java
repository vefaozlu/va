package com.example.va.core.service.producttemplateattribute.createproducttemplateattribute;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CreateProductTemplateAttributeRequest {
    @NotNull(message = "Attribute name cannot be null.")
    @NotEmpty(message = "Attribute name cannot be empty.")
    private String name;

    @NotNull(message = "Type cannot be null.")
    private ProductTemplateAttributeType type;

    @NotNull(message = "Allow null cannot be null.")
    private boolean allowNull;

    @NotNull(message = "Calculated cannot be null.")
    private boolean calculated;

    private String formula;

    private Map<String, String> variables;

    private String conditions;

    private boolean isConstant;

    private String defaultValue;

    @NotNull(message = "Product template id cannot be null.")
    private Integer productTemplateId;
}