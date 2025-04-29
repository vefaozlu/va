package com.example.va.core.service.product.createproduct;

import com.example.va.Command;
import com.example.va.core.domain.product.ProductFactory;
import com.example.va.core.service.product._common.protocol.ProductDsGateway;
import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import com.example.va.core.service.producttemplate.getproducttemplatebyid.GetProductTemplateByIdRequest;
import com.example.va.core.service.producttemplate.getproducttemplatebyid.GetProductTemplateByIdResponse;
import com.example.va.core.service.producttemplateengine.calculate.CalculateRequest;
import com.example.va.core.service.producttemplateengine.calculate.CalculateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class CreateProductService implements Command<CreateProductRequest, CreateProductResponse> {

    private final ProductDsGateway productDsGateway;
    private final ProductFactory productFactory;
    private final Command<GetProductTemplateByIdRequest, GetProductTemplateByIdResponse> getProductTemplateByIdService;
    private final Command<CalculateRequest, CalculateResponse> calculateService;

    public CreateProductService(ProductDsGateway productDsGateway, ProductFactory productFactory,
                                Command<GetProductTemplateByIdRequest, GetProductTemplateByIdResponse> getProductTemplateByIdService,
                                Command<CalculateRequest, CalculateResponse> calculateService) {
        this.productDsGateway = productDsGateway;
        this.productFactory = productFactory;
        this.getProductTemplateByIdService = getProductTemplateByIdService;
        this.calculateService = calculateService;
    }

    @Override
    public ResponseEntity<CreateProductResponse> execute(CreateProductRequest request) {
        ProductTemplateDto productTemplateDto = Objects.requireNonNull(getProductTemplateByIdService.execute(
                new GetProductTemplateByIdRequest(request.getProductTemplateId())).getBody()).getProductTemplate();

        Map<String, Object> calculationResult = Objects.requireNonNull(calculateService.execute(
                new CalculateRequest(productTemplateDto.getProductTemplateAttributes(), request.getAttributes())).getBody()).getResults();

        return ResponseEntity.ok(new CreateProductResponse(null, calculationResult));
    }
}