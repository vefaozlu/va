package com.example.va.core.service.producttemplateattribute.updateproducttemplateattribute;

import com.example.va.Command;
import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeFactory;
import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import com.example.va.core.service.producttemplateattribute._common.protocol.ProductTemplateAttributeDsGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductTemplateAttributeService implements
    Command<UpdateProductTemplateAttributeRequest, UpdateProductTemplateAttributeResponse> {

  private final ProductTemplateAttributeFactory productTemplateAttributeFactory;
  private final ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway;
  private final ModelMapper modelMapper;

  public UpdateProductTemplateAttributeService(
      ProductTemplateAttributeFactory productTemplateAttributeFactory,
      ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway) {
    this.productTemplateAttributeFactory = productTemplateAttributeFactory;
    this.productTemplateAttributeDsGateway = productTemplateAttributeDsGateway;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public ResponseEntity<UpdateProductTemplateAttributeResponse> execute(
      UpdateProductTemplateAttributeRequest request) {
    ProductTemplateAttribute productTemplateAttribute = productTemplateAttributeFactory.createProductTemplateAttribute(
        request.getId(), request.getName(), request.getType(), request.isAllowNull(),
        request.isCalculated(), request.getFormula(), request.getVariables(),
        request.getConditions(), request.isConstant(), request.getDefaultValue(),
        request.getProductTemplateId(), request.getStepId());

    return productTemplateAttributeDsGateway.update(request.getId(),
            modelMapper.map(productTemplateAttribute, ProductTemplateAttributeDto.class)).map(
            updatedAttribute -> ResponseEntity.ok(
                new UpdateProductTemplateAttributeResponse(updatedAttribute)))
        .orElse(ResponseEntity.notFound().build());
  }
} 