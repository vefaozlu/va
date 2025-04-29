package com.example.va.core.domain.producttemplateengine;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;

import java.util.HashMap;

public interface ProductTemplateEngineFactory {
    ProductTemplateEngine create(HashMap<String, ProductTemplateAttribute> attributes,
                                 HashMap<String, Object> nonCalculatedAttributes);
}
