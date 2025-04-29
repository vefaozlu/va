package com.example.va.core.service.product.updateproduct;

import com.example.va.Command;
import com.example.va.core.domain.product.Product;
import com.example.va.core.domain.product.ProductFactory;
import com.example.va.core.service.product._common.dto.ProductDto;
import com.example.va.core.service.product._common.protocol.ProductDsGateway;
import com.example.va.mappers.product.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductRequest, UpdateProductResponse> {

    private final ProductDsGateway productDsGateway;
    private final ProductFactory productFactory;
    private final ProductMapper productMapper;

    public UpdateProductService(
            ProductDsGateway productDsGateway,
            ProductFactory productFactory,
            ProductMapper productMapper) {
        this.productDsGateway = productDsGateway;
        this.productFactory = productFactory;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<UpdateProductResponse> execute(UpdateProductRequest request) {
        Optional<ProductDto> existingProduct = productDsGateway.findById(request.getId());
        
        return existingProduct
                .map(product -> {
                    Date now = new Date();
                    Product updatedProduct = productFactory.createProduct(
                            request.getId(),
                            request.getAttributes(),
                            request.getProductTemplateId()
                    );

                    ProductDto productDto = new ProductDto(null, product.getAttributes(), product.getProductTemplateId());
                    ProductDto savedProduct = productDsGateway.update(request.getId(), productDto)
                            .orElseThrow(() -> new RuntimeException("Failed to update product"));

                    return ResponseEntity.ok(new UpdateProductResponse(savedProduct.getId()));
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 