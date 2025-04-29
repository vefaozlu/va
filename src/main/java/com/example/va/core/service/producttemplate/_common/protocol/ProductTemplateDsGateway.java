package com.example.va.core.service.producttemplate._common.protocol;

import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;

import java.util.List;
import java.util.Optional;

public interface ProductTemplateDsGateway {
    ProductTemplateDto create(ProductTemplateDto productTemplateDto);
    Optional<ProductTemplateDto> findById(Integer id);
    List<ProductTemplateDto> findAll();
    Optional<ProductTemplateDto> update(Integer id, ProductTemplateDto productTemplateDto);
    void delete(Integer id);
}
