package com.example.va.core.domain.producttemplate;

import java.util.List;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import org.springframework.stereotype.Component;

@Component
public class CommonProductTemplateFactory implements ProductTemplateFactory {
    @Override
    public ProductTemplate create(Integer id, String name, List<ProductTemplateAttribute> productTemplateAttributes) {
        return new CommonProductTemplate(id, name, productTemplateAttributes);
    }
} 