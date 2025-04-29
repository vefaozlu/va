package com.example.va.core.domain.producttemplate;

import java.util.List;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;

public interface ProductTemplateFactory {
    ProductTemplate create(Integer id, String name, List<ProductTemplateAttribute> productTemplateAttributes);
} 