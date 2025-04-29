package com.example.va.core.service.producttemplateattribute.getallproducttemplateattributes;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllProductTemplateAttributesResponse {
    private List<ProductTemplateAttributeDto> productTemplateAttributes;
} 