package com.example.va.core.service.producttemplateattribute._common.protocol;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;

import java.util.List;
import java.util.Optional;

public interface ProductTemplateAttributeDsGateway {
    ProductTemplateAttributeDto create(ProductTemplateAttributeDto productTemplateAttributeDto);
    Optional<ProductTemplateAttributeDto> findById(Integer id);
    List<ProductTemplateAttributeDto> findAll();
    Optional<ProductTemplateAttributeDto> update(Integer id, ProductTemplateAttributeDto productTemplateAttributeDto);
    void delete(Integer id);
}
