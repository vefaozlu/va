package com.example.va.mappers.productattribute;

import com.example.va.core.service.productattribute._common.dto.ProductAttributeDto;
import com.example.va.data.productattribute.ProductAttributeDataMapper;
import org.springframework.stereotype.Component;

@Component
public class CommonProductAttributeDataMapper implements ProductAttributeMapper {
    @Override
    public ProductAttributeDto fromEntity(ProductAttributeDataMapper entity) {
        return new ProductAttributeDto(
                entity.getId(),
                entity.getName(),
                entity.getLength(),
                entity.getWidth(),
                entity.getHeight(),
                entity.getColor(),
                entity.getProductId(),
                entity.getProductTemplateAttributeId());
    }

    @Override
    public ProductAttributeDataMapper toEntity(ProductAttributeDto dto) {
        return new ProductAttributeDataMapper(
                dto.getId(),
                dto.getName(),
                dto.getLength(),
                dto.getWidth(),
                dto.getHeight(),
                dto.getColor(),
                dto.getProductId(),
                dto.getProductTemplateId());
    }
}
