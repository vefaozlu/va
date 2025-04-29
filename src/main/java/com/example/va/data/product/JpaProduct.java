package com.example.va.data.product;

import com.example.va.core.service.product._common.dto.ProductDto;
import com.example.va.core.service.product._common.protocol.ProductDsGateway;
import com.example.va.mappers.product.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProduct implements ProductDsGateway {

    private final JpaProductRepository repository;
    private final ProductMapper productMapper;

    public JpaProduct(JpaProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        ProductDataMapper entity = productMapper.toEntity(productDto);
        ProductDataMapper savedEntity = repository.save(entity);
        return productMapper.fromEntity(savedEntity);
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        return repository.findById(id)
                .map(productMapper::fromEntity);
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream()
                .map(productMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> update(Integer id, ProductDto productDto) {
        return repository.findById(id)
                .map(existingEntity -> {
                    ProductDataMapper updatedEntity = productMapper.toEntity(productDto);
                    updatedEntity.setId(id);
                    return repository.save(updatedEntity);
                })
                .map(productMapper::fromEntity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
