package com.example.va.mappers.producttemplate;

import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import com.example.va.data.producttemplate.ProductTemplateDataMapper;

public interface ProductTemplateMapper {
    ProductTemplateDto fromEntity(ProductTemplateDataMapper entity);

    ProductTemplateDataMapper toEntity(ProductTemplateDto dto);
}
