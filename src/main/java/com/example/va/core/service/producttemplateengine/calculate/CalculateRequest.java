package com.example.va.core.service.producttemplateengine.calculate;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class CalculateRequest {
    @NotNull(message = "Attributes cannot be null")
    private List<ProductTemplateAttributeDto> attributes;

    @NotNull(message = "Non-calculated attributes cannot be null")
    private HashMap<String, Object> nonCalculatedAttributes;
}
