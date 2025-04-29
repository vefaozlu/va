package com.example.va.core.service.product.getallproducts;

import com.example.va.Command;
import com.example.va.core.service.product._common.dto.ProductDto;
import com.example.va.core.service.product._common.protocol.ProductDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductsService implements Command<Void, GetAllProductsResponse> {

    private final ProductDsGateway productDsGateway;

    public GetAllProductsService(ProductDsGateway productDsGateway) {
        this.productDsGateway = productDsGateway;
    }

    @Override
    public ResponseEntity<GetAllProductsResponse> execute(Void request) {
        return ResponseEntity.ok(new GetAllProductsResponse(productDsGateway.findAll()));
    }
} 