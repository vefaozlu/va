package com.example.va.core.domain.producttemplate;

import java.util.List;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;

public interface ProductTemplate {
    Integer getId();
    String getName();
    List<ProductTemplateAttribute> getProductTemplateAttributes();
} 