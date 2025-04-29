package com.example.va.core.service.product.getproductbyid;

import com.example.va.Command;
import com.example.va.core.service.product._common.dto.ProductDto;
import com.example.va.core.service.product._common.protocol.ProductDsGateway;
import com.example.va.mappers.product.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductByIdService implements Command<GetProductByIdRequest, GetProductByIdResponse> {

    private final ProductDsGateway productDsGateway;

    public GetProductByIdService(ProductDsGateway productDsGateway) {
        this.productDsGateway = productDsGateway;
    }

    @Override
    public ResponseEntity<GetProductByIdResponse> execute(GetProductByIdRequest request) {
        Optional<ProductDto> productDto = productDsGateway.findById(request.getId());

        return productDto
                .map(dto -> ResponseEntity.ok(new GetProductByIdResponse(dto)))
                .orElse(ResponseEntity.notFound().build());
    }
} 