package com.example.va.core.service.producttemplateattribute.getproducttemplateattributebyid;

import com.example.va.Command;
import com.example.va.core.service.producttemplateattribute._common.protocol.ProductTemplateAttributeDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductTemplateAttributeByIdService implements Command<GetProductTemplateAttributeByIdRequest, GetProductTemplateAttributeByIdResponse> {

    private final ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway;

    public GetProductTemplateAttributeByIdService(ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway) {
        this.productTemplateAttributeDsGateway = productTemplateAttributeDsGateway;
    }

    @Override
    public ResponseEntity<GetProductTemplateAttributeByIdResponse> execute(GetProductTemplateAttributeByIdRequest request) {
        return productTemplateAttributeDsGateway.findById(request.getId())
                .map(productTemplateAttribute -> ResponseEntity.ok(new GetProductTemplateAttributeByIdResponse(productTemplateAttribute)))
                .orElse(ResponseEntity.notFound().build());
    }
} 