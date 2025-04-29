package com.example.va.mappers.producttemplateattribute;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import com.example.va.data.producttemplateattribute.ProductTemplateAttributeDataMapper;

public interface ProductTemplateAttributeMapper {
    ProductTemplateAttributeDto fromEntity(ProductTemplateAttributeDataMapper entity);

    ProductTemplateAttributeDataMapper toEntity(ProductTemplateAttributeDto dto);
}
