package com.example.va.core.service.product.createproduct;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotNull
    private HashMap<String, Object> attributes;
    
    @NotNull
    private Integer productTemplateId;
} 