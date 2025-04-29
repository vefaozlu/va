package com.example.va.data.productattribute;

import com.example.va.core.service.productattribute._common.dto.ProductAttributeDto;
import com.example.va.core.service.productattribute._common.protocol.ProductAttributeDsGateway;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductAttribute implements ProductAttributeDsGateway {

    private  final JpaProductAttributeRepository repository;

    public JpaProductAttribute(JpaProductAttributeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(ProductAttributeDto productAttributeDto) {
        return false;
    }
}
