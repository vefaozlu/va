package com.example.va.core.service.producttemplate.updateproducttemplate;

import com.example.va.Command;
import com.example.va.core.domain.producttemplate.ProductTemplate;
import com.example.va.core.domain.producttemplate.ProductTemplateFactory;
import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import com.example.va.core.service.producttemplate._common.protocol.ProductTemplateDsGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductTemplateService implements Command<UpdateProductTemplateRequest, UpdateProductTemplateResponse> {

    private final ProductTemplateFactory productTemplateFactory;
    private final ProductTemplateDsGateway productTemplateDsGateway;

    public UpdateProductTemplateService(ProductTemplateFactory productTemplateFactory,
                                        ProductTemplateDsGateway productTemplateDsGateway) {
        this.productTemplateFactory = productTemplateFactory;
        this.productTemplateDsGateway = productTemplateDsGateway;
    }

    @Override
    public ResponseEntity<UpdateProductTemplateResponse> execute(UpdateProductTemplateRequest request) {
        ProductTemplate productTemplate = productTemplateFactory.create(
                request.getId(),
                request.getName(),
                request.getProductTemplateAttributes()
        );

        return productTemplateDsGateway.update(request.getId(),
                        new ProductTemplateDto(request.getId(), productTemplate.getName(), null, null)
                )
                .map(updatedTemplate -> ResponseEntity.ok(new UpdateProductTemplateResponse(updatedTemplate)))
                .orElse(ResponseEntity.notFound().build());
    }
} 