package com.example.va.data.producttemplate;

import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import com.example.va.core.service.producttemplate._common.protocol.ProductTemplateDsGateway;
import com.example.va.mappers.producttemplate.ProductTemplateMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProductTemplate implements ProductTemplateDsGateway {

    private final JpaProductTemplateRepository repository;
    private final ProductTemplateMapper productTemplateMapper;

    public JpaProductTemplate(JpaProductTemplateRepository repository, ProductTemplateMapper productTemplateMapper) {
        this.repository = repository;
        this.productTemplateMapper = productTemplateMapper;
    }

    @Override
    public ProductTemplateDto create(ProductTemplateDto productTemplateDto) {
        ProductTemplateDataMapper entity = productTemplateMapper.toEntity(productTemplateDto);
        ProductTemplateDataMapper savedEntity = repository.save(entity);
        return productTemplateMapper.fromEntity(savedEntity);
    }

    @Override
    public Optional<ProductTemplateDto> findById(Integer id) {
        return repository.findById(id)
                .map(productTemplateMapper::fromEntity);
    }

    @Override
    public List<ProductTemplateDto> findAll() {
        return repository.findAll().stream()
                .map(productTemplateMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductTemplateDto> update(Integer id, ProductTemplateDto productTemplateDto) {
        return repository.findById(id)
                .map(existingEntity -> {
                    ProductTemplateDataMapper updatedEntity = productTemplateMapper.toEntity(productTemplateDto);
                    updatedEntity.setId(id); // Preserve the ID
                    return repository.save(updatedEntity);
                })
                .map(productTemplateMapper::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
