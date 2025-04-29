package com.example.va.core.service.producttemplateattribute.getallproducttemplateattributes;

import com.example.va.Command;
import com.example.va.core.service.producttemplateattribute._common.protocol.ProductTemplateAttributeDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductTemplateAttributesService implements Command<Void, GetAllProductTemplateAttributesResponse> {

    private final ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway;

    public GetAllProductTemplateAttributesService(ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway) {
        this.productTemplateAttributeDsGateway = productTemplateAttributeDsGateway;
    }

    @Override
    public ResponseEntity<GetAllProductTemplateAttributesResponse> execute(Void request) {
        return ResponseEntity.ok(new GetAllProductTemplateAttributesResponse(productTemplateAttributeDsGateway.findAll()));
    }
} 