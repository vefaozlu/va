package com.example.va.core.service.producttemplate.createproducttemplate;

import com.example.va.Command;
import com.example.va.core.domain.producttemplate.ProductTemplate;
import com.example.va.core.domain.producttemplate.ProductTemplateFactory;
import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import com.example.va.core.service.producttemplate._common.protocol.ProductTemplateDsGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductTemplateService implements
    Command<CreateProductTemplateRequest, CreateProductTemplateResponse> {

  private final ProductTemplateFactory productTemplateFactory;
  private final ProductTemplateDsGateway productTemplateDsGateway;
  private final ModelMapper modelMapper;

  public CreateProductTemplateService(ProductTemplateFactory productTemplateFactory,
      ProductTemplateDsGateway productTemplateDsGateway, ModelMapper modelMapper) {
    this.productTemplateFactory = productTemplateFactory;
    this.productTemplateDsGateway = productTemplateDsGateway;
    this.modelMapper = modelMapper;
  }

  @Override
  public ResponseEntity<CreateProductTemplateResponse> execute(
      CreateProductTemplateRequest request) {
    ProductTemplate productTemplate = productTemplateFactory.create(null, request.getName(), null);

    ProductTemplateDto response = productTemplateDsGateway.create(
        modelMapper.map(productTemplate, ProductTemplateDto.class));
    return ResponseEntity.ok(new CreateProductTemplateResponse(response));
  }
}