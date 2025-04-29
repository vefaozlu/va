package com.example.va.mappers.product;

import com.example.va.core.service.product._common.dto.ProductDto;
import com.example.va.data.product.ProductDataMapper;

public interface ProductMapper {
    ProductDataMapper toEntity(ProductDto dto);
    ProductDto fromEntity(ProductDataMapper entity);
} 