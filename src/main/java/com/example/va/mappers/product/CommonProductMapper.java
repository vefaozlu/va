package com.example.va.mappers.product;

import com.example.va.core.service.product._common.dto.ProductDto;
import com.example.va.data.product.ProductDataMapper;
import com.example.va.mappers.productattribute.ProductAttributeMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommonProductMapper implements ProductMapper {

    private final ProductAttributeMapper productAttributeMapper;

    public CommonProductMapper(ProductAttributeMapper productAttributeMapper) {
        this.productAttributeMapper = productAttributeMapper;
    }

    @Override
    public ProductDataMapper toEntity(ProductDto dto) {
        ProductDataMapper entity = new ProductDataMapper();
        entity.setId(dto.getId());
        entity.setAttributes(dto.getAttributes().stream().map(productAttributeMapper::toEntity).collect(Collectors.toList()));
        entity.setProductTemplateId(dto.getProductTemplateId());
        return entity;
    }

    @Override
    public ProductDto fromEntity(ProductDataMapper entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setAttributes(entity.getAttributes().stream().map(productAttributeMapper::fromEntity).collect(Collectors.toList()));
        dto.setProductTemplateId(entity.getProductTemplateId());
        return dto;
    }
} 