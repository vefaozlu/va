package com.example.va.core.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CommonProduct implements Product {
    private Integer id;
    private Map<String, Object> attributes;
    private Integer productTemplateId;

    @Override
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}