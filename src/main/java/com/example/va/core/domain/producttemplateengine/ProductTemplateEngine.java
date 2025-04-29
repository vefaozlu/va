package com.example.va.core.domain.producttemplateengine;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;

import java.util.HashMap;
import java.util.Map;

public interface ProductTemplateEngine {
    HashMap<String, ProductTemplateAttribute> getAttributes();

    Object getValue(String attributeName);

    Map<String, Object> calculate();
}
