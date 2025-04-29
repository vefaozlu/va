package com.example.va.core.service.product._common.protocol;

import com.example.va.core.service.product._common.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductDsGateway {
    ProductDto create(ProductDto productDto);
    Optional<ProductDto> findById(Integer id);
    List<ProductDto> findAll();
    Optional<ProductDto> update(Integer id, ProductDto productDto);
    void delete(Integer id);
}
