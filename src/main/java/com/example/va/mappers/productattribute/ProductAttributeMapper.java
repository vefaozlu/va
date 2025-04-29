package com.example.va.mappers.productattribute;

import com.example.va.core.service.productattribute._common.dto.ProductAttributeDto;
import com.example.va.data.productattribute.ProductAttributeDataMapper;

import java.util.List;

public interface ProductAttributeMapper {
    ProductAttributeDto fromEntity(ProductAttributeDataMapper entity);

    ProductAttributeDataMapper toEntity(ProductAttributeDto dto);
}
