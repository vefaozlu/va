package com.example.va.core.service.producttemplate.getproducttemplatebyid;

import com.example.va.Command;
import com.example.va.core.service.producttemplate._common.protocol.ProductTemplateDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductTemplateByIdService implements Command<GetProductTemplateByIdRequest, GetProductTemplateByIdResponse> {

    private final ProductTemplateDsGateway productTemplateDsGateway;

    public GetProductTemplateByIdService(ProductTemplateDsGateway productTemplateDsGateway) {
        this.productTemplateDsGateway = productTemplateDsGateway;
    }

    @Override
    public ResponseEntity<GetProductTemplateByIdResponse> execute(GetProductTemplateByIdRequest request) {
        return productTemplateDsGateway.findById(request.getId())
                .map(productTemplate -> ResponseEntity.ok(new GetProductTemplateByIdResponse(productTemplate)))
                .orElse(ResponseEntity.notFound().build());
    }
} 