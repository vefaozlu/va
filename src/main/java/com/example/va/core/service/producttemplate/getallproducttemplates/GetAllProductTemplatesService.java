package com.example.va.core.service.producttemplate.getallproducttemplates;

import com.example.va.Command;
import com.example.va.core.service.producttemplate._common.protocol.ProductTemplateDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductTemplatesService implements Command<Void, GetAllProductTemplatesResponse> {

    private final ProductTemplateDsGateway productTemplateDsGateway;

    public GetAllProductTemplatesService(ProductTemplateDsGateway productTemplateDsGateway) {
        this.productTemplateDsGateway = productTemplateDsGateway;
    }

    @Override
    public ResponseEntity<GetAllProductTemplatesResponse> execute(Void request) {
        return ResponseEntity.ok(new GetAllProductTemplatesResponse(productTemplateDsGateway.findAll()));
    }
} 