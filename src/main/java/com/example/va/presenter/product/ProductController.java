package com.example.va.presenter.product;

import com.example.va.Command;
import com.example.va.core.service.product.createproduct.CreateProductRequest;
import com.example.va.core.service.product.createproduct.CreateProductResponse;
import com.example.va.core.service.product.getallproducts.GetAllProductsResponse;
import com.example.va.core.service.product.getproductbyid.GetProductByIdRequest;
import com.example.va.core.service.product.getproductbyid.GetProductByIdResponse;
import com.example.va.core.service.product.updateproduct.UpdateProductRequest;
import com.example.va.core.service.product.updateproduct.UpdateProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Command<CreateProductRequest, CreateProductResponse> createProductService;
    private final Command<GetProductByIdRequest, GetProductByIdResponse> getProductByIdService;
    private final Command<Void, GetAllProductsResponse> getAllProductsService;
    private final Command<UpdateProductRequest, UpdateProductResponse> updateProductService;

    public ProductController(
            Command<CreateProductRequest, CreateProductResponse> createProductService,
            Command<GetProductByIdRequest, GetProductByIdResponse> getProductByIdService,
            Command<Void, GetAllProductsResponse> getAllProductsService,
            Command<UpdateProductRequest, UpdateProductResponse> updateProductService) {
        this.createProductService = createProductService;
        this.getProductByIdService = getProductByIdService;
        this.getAllProductsService = getAllProductsService;
        this.updateProductService = updateProductService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        return createProductService.execute(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetProductByIdResponse> getProductById(@PathVariable Integer id) {
        return getProductByIdService.execute(new GetProductByIdRequest(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        return getAllProductsService.execute(null);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateProductResponse> updateProduct(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateProductRequest request) {
        request.setId(id); // Ensure the ID from path matches the request
        return updateProductService.execute(request);
    }
} 