package com.example.va.core.service.producttemplate.createproducttemplate;

import com.example.va.core.service.producttemplateattribute.createproducttemplateattribute.CreateProductTemplateAttributeRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateProductTemplateRequest {
    @NotNull(message = "Template name cannot be null.")
    @NotEmpty(message = "Template name cannot be empty.")
    private String name;
}