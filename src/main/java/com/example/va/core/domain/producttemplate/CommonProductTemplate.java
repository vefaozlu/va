package com.example.va.core.domain.producttemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;

@Getter
@AllArgsConstructor
public class CommonProductTemplate implements ProductTemplate {
    private Integer id;
    private String name;
    private List<ProductTemplateAttribute> productTemplateAttributes;
} 