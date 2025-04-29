package com.example.va.core.service.producttemplate.updateproducttemplate;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateProductTemplateRequest {
    private Integer id;
    private String name;
    private List<ProductTemplateAttribute> productTemplateAttributes;
} 