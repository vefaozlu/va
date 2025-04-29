package com.example.va.core.service.producttemplateengine.calculate;

import com.example.va.Command;
import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeFactory;
import com.example.va.core.domain.producttemplateengine.ProductTemplateEngine;
import com.example.va.core.domain.producttemplateengine.ProductTemplateEngineFactory;
import java.util.HashMap;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CalculateService implements Command<CalculateRequest, CalculateResponse> {

  private final ProductTemplateEngineFactory productTemplateEngineFactory;
  private final ProductTemplateAttributeFactory productTemplateAttributeFactory;
  private final ModelMapper modelMapper;

  public CalculateService(ProductTemplateEngineFactory productTemplateEngineFactory,
      ProductTemplateAttributeFactory productTemplateAttributeFactory, ModelMapper modelMapper) {
    this.productTemplateEngineFactory = productTemplateEngineFactory;
    this.productTemplateAttributeFactory = productTemplateAttributeFactory;
    this.modelMapper = modelMapper;
  }

  @Override
  public ResponseEntity<CalculateResponse> execute(CalculateRequest request) {
    HashMap<String, ProductTemplateAttribute> attributes = new HashMap<>();
    request.getAttributes().forEach(attribute -> attributes.put(attribute.getName(),
        productTemplateAttributeFactory.createProductTemplateAttribute(attribute.getId(),
            attribute.getName(),
            attribute.getType(), attribute.isAllowNull(), attribute.isCalculated(),
            attribute.getFormula(),
            attribute.getVariables(), attribute.getConditions(), attribute.isConstant(),
            attribute.getDefaultValue(), attribute.getProductTemplateId(), attribute.getStepId())));

    ProductTemplateEngine productTemplateEngine = productTemplateEngineFactory.create(attributes,
        request.getNonCalculatedAttributes());

    return ResponseEntity.ok(new CalculateResponse(productTemplateEngine.calculate()));
  }
}
