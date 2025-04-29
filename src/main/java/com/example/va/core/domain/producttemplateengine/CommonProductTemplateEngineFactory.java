package com.example.va.core.domain.producttemplateengine;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CommonProductTemplateEngineFactory implements ProductTemplateEngineFactory {
    @Override
    public ProductTemplateEngine create(HashMap<String, ProductTemplateAttribute> attributes, HashMap<String, Object> nonCalculatedAttributes) {
        return new CommonProductTemplateEngine(attributes, nonCalculatedAttributes);
    }
}
