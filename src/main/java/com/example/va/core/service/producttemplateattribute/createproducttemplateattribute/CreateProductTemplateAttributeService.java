package com.example.va.core.service.producttemplateattribute.createproducttemplateattribute;

import com.example.va.Command;
import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeFactory;
import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import com.example.va.core.service.producttemplateattribute._common.protocol.ProductTemplateAttributeDsGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductTemplateAttributeService implements Command<CreateProductTemplateAttributeRequest, CreateProductTemplateAttributeResponse> {

    private final ProductTemplateAttributeFactory productTemplateAttributeFactory;
    private final ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway;
    private final ModelMapper modelMapper;

    public CreateProductTemplateAttributeService(ProductTemplateAttributeFactory productTemplateAttributeFactory,
                                                 ProductTemplateAttributeDsGateway productTemplateAttributeDsGateway) {
        this.productTemplateAttributeFactory = productTemplateAttributeFactory;
        this.productTemplateAttributeDsGateway = productTemplateAttributeDsGateway;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ResponseEntity<CreateProductTemplateAttributeResponse> execute(
            CreateProductTemplateAttributeRequest request) {
//        ProductTemplateAttribute productTemplateAttribute = productTemplateAttributeFactory.createProductTemplateAttribute(
//                null, request.getName(), request.getType(), request.isAllowNull(), request.isCalculated(),
//                request.getFormula(), request.getVariables(), request.getConditions(), request.isConstant(),
//                request.getDefaultValue(), request.getProductTemplateId());

        ProductTemplateAttribute productTemplateAttribute = modelMapper.map(request, ProductTemplateAttribute.class);

//                ProductTemplateAttributeDto response = productTemplateAttributeDsGateway.create(
//                new ProductTemplateAttributeDto
//                        (null, productTemplateAttribute.getName(),
//                                productTemplateAttribute.getType(), productTemplateAttribute.isAllowNull(),
//                                productTemplateAttribute.isCalculated(), productTemplateAttribute.getFormula(),
//                                productTemplateAttribute.getVariables(), productTemplateAttribute.getConditions(),
//                                productTemplateAttribute.isConstant(), productTemplateAttribute.getDefaultValue(),
//                                productTemplateAttribute.getProductTemplateId()));

        ProductTemplateAttributeDto response = productTemplateAttributeDsGateway.create(
                modelMapper.map(productTemplateAttribute, ProductTemplateAttributeDto.class));

        return ResponseEntity.ok(new CreateProductTemplateAttributeResponse(response));
    }
} 