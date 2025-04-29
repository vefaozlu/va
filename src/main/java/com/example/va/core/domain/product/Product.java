package com.example.va.core.domain.product;

import java.util.Map;

public interface Product {
    Integer getId();

    Map<String, Object> getAttributes();

    void setAttributes(Map<String, Object> attributes);

    Integer getProductTemplateId();
}