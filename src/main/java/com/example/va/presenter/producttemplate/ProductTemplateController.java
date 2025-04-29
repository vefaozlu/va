package com.example.va.presenter.producttemplate;

import com.example.va.Command;
import com.example.va.core.service.producttemplate.createproducttemplate.CreateProductTemplateRequest;
import com.example.va.core.service.producttemplate.createproducttemplate.CreateProductTemplateResponse;
import com.example.va.core.service.producttemplate.getallproducttemplates.GetAllProductTemplatesResponse;
import com.example.va.core.service.producttemplate.getproducttemplatebyid.GetProductTemplateByIdRequest;
import com.example.va.core.service.producttemplate.getproducttemplatebyid.GetProductTemplateByIdResponse;
import com.example.va.core.service.producttemplate.updateproducttemplate.UpdateProductTemplateRequest;
import com.example.va.core.service.producttemplate.updateproducttemplate.UpdateProductTemplateResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-template")
public class ProductTemplateController {

    private final Command<CreateProductTemplateRequest, CreateProductTemplateResponse> createProductTemplateService;
    private final Command<GetProductTemplateByIdRequest, GetProductTemplateByIdResponse> getProductTemplateByIdService;
    private final Command<Void, GetAllProductTemplatesResponse> getAllProductTemplatesService;
    private final Command<UpdateProductTemplateRequest, UpdateProductTemplateResponse> updateProductTemplateService;

    public ProductTemplateController(
            Command<CreateProductTemplateRequest, CreateProductTemplateResponse> createProductTemplateService,
            Command<GetProductTemplateByIdRequest, GetProductTemplateByIdResponse> getProductTemplateByIdService,
            Command<Void, GetAllProductTemplatesResponse> getAllProductTemplatesService,
            Command<UpdateProductTemplateRequest, UpdateProductTemplateResponse> updateProductTemplateService) {
        this.createProductTemplateService = createProductTemplateService;
        this.getProductTemplateByIdService = getProductTemplateByIdService;
        this.getAllProductTemplatesService = getAllProductTemplatesService;
        this.updateProductTemplateService = updateProductTemplateService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateProductTemplateResponse> createProductTemplate(@RequestBody @Valid CreateProductTemplateRequest request) {
        return createProductTemplateService.execute(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetProductTemplateByIdResponse> getProductTemplateById(@PathVariable Integer id) {
        return getProductTemplateByIdService.execute(new GetProductTemplateByIdRequest(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetAllProductTemplatesResponse> getAllProductTemplates() {
        return getAllProductTemplatesService.execute(null);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateProductTemplateResponse> updateProductTemplate(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateProductTemplateRequest request) {
        request.setId(id); // Ensure the ID from path matches the request
        return updateProductTemplateService.execute(request);
    }
} 