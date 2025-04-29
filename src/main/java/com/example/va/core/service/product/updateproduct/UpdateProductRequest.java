package com.example.va.core.service.product.updateproduct;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private Integer id;
    
    @NotNull
    private Map<String, Object> attributes;
    
    @NotNull
    private Integer productTemplateId;
} 