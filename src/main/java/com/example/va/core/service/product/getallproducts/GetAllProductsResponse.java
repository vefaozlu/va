package com.example.va.core.service.product.getallproducts;

import com.example.va.core.service.product._common.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponse {
    private List<ProductDto> products;
} 