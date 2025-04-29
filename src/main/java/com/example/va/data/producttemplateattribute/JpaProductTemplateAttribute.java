package com.example.va.data.producttemplateattribute;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import com.example.va.core.service.producttemplateattribute._common.protocol.ProductTemplateAttributeDsGateway;
import com.example.va.mappers.producttemplateattribute.ProductTemplateAttributeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProductTemplateAttribute implements ProductTemplateAttributeDsGateway {

    private final JpaProductTemplateAttributeRepository repository;
    private final ProductTemplateAttributeMapper productTemplateAttributeMapper;

    public JpaProductTemplateAttribute(JpaProductTemplateAttributeRepository repository, ProductTemplateAttributeMapper productTemplateAttributeMapper) {
        this.repository = repository;
        this.productTemplateAttributeMapper = productTemplateAttributeMapper;
    }

    @Override
    public ProductTemplateAttributeDto create(ProductTemplateAttributeDto productTemplateAttributeDto) {
        ProductTemplateAttributeDataMapper entity = productTemplateAttributeMapper.toEntity(productTemplateAttributeDto);
        ProductTemplateAttributeDataMapper savedEntity = repository.save(entity);
        return productTemplateAttributeMapper.fromEntity(savedEntity);
    }

    @Override
    public Optional<ProductTemplateAttributeDto> findById(Integer id) {
        return repository.findById(id)
                .map(productTemplateAttributeMapper::fromEntity);
    }

    @Override
    public List<ProductTemplateAttributeDto> findAll() {
        return repository.findAll().stream()
                .map(productTemplateAttributeMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductTemplateAttributeDto> update(Integer id, ProductTemplateAttributeDto productTemplateAttributeDto) {
        return repository.findById(id)
                .map(existingEntity -> {
                    ProductTemplateAttributeDataMapper updatedEntity = productTemplateAttributeMapper.toEntity(productTemplateAttributeDto);
                    updatedEntity.setId(id); // Preserve the ID
                    return repository.save(updatedEntity);
                })
                .map(productTemplateAttributeMapper::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
