package com.example.va.core.service.product.getproductbyid;

import com.example.va.core.service.product._common.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByIdResponse {
    private ProductDto product;
} 