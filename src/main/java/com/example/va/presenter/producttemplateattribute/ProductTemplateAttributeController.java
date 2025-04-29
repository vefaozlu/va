package com.example.va.presenter.producttemplateattribute;

import com.example.va.Command;
import com.example.va.core.service.producttemplateattribute.createproducttemplateattribute.CreateProductTemplateAttributeRequest;
import com.example.va.core.service.producttemplateattribute.createproducttemplateattribute.CreateProductTemplateAttributeResponse;
import com.example.va.core.service.producttemplateattribute.getallproducttemplateattributes.GetAllProductTemplateAttributesResponse;
import com.example.va.core.service.producttemplateattribute.getproducttemplateattributebyid.GetProductTemplateAttributeByIdRequest;
import com.example.va.core.service.producttemplateattribute.getproducttemplateattributebyid.GetProductTemplateAttributeByIdResponse;
import com.example.va.core.service.producttemplateattribute.updateproducttemplateattribute.UpdateProductTemplateAttributeRequest;
import com.example.va.core.service.producttemplateattribute.updateproducttemplateattribute.UpdateProductTemplateAttributeResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-template-attribute")
public class ProductTemplateAttributeController {

    private final Command<CreateProductTemplateAttributeRequest, CreateProductTemplateAttributeResponse> createProductTemplateAttributeService;
    private final Command<GetProductTemplateAttributeByIdRequest, GetProductTemplateAttributeByIdResponse> getProductTemplateAttributeByIdService;
    private final Command<Void, GetAllProductTemplateAttributesResponse> getAllProductTemplateAttributesService;
    private final Command<UpdateProductTemplateAttributeRequest, UpdateProductTemplateAttributeResponse> updateProductTemplateAttributeService;

    public ProductTemplateAttributeController(
            Command<CreateProductTemplateAttributeRequest, CreateProductTemplateAttributeResponse> createProductTemplateAttributeService,
            Command<GetProductTemplateAttributeByIdRequest, GetProductTemplateAttributeByIdResponse> getProductTemplateAttributeByIdService,
            Command<Void, GetAllProductTemplateAttributesResponse> getAllProductTemplateAttributesService,
            Command<UpdateProductTemplateAttributeRequest, UpdateProductTemplateAttributeResponse> updateProductTemplateAttributeService
    ) {
        this.createProductTemplateAttributeService = createProductTemplateAttributeService;
        this.getProductTemplateAttributeByIdService = getProductTemplateAttributeByIdService;
        this.getAllProductTemplateAttributesService = getAllProductTemplateAttributesService;
        this.updateProductTemplateAttributeService = updateProductTemplateAttributeService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateProductTemplateAttributeResponse> createProductTemplateAttribute(
            @RequestBody @Valid CreateProductTemplateAttributeRequest request
    ) {
        return createProductTemplateAttributeService.execute(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetProductTemplateAttributeByIdResponse> getProductTemplateAttributeById(
            @PathVariable Integer id
    ) {
        return getProductTemplateAttributeByIdService.execute(new GetProductTemplateAttributeByIdRequest(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetAllProductTemplateAttributesResponse> getAllProductTemplateAttributes() {
        return getAllProductTemplateAttributesService.execute(null);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateProductTemplateAttributeResponse> updateProductTemplateAttribute(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateProductTemplateAttributeRequest request
    ) {
        request.setId(id); // Ensure the ID from path matches the request
        return updateProductTemplateAttributeService.execute(request);
    }
} 