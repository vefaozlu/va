package com.example.va.core.service.product._common.dto;

import com.example.va.core.service.productattribute._common.dto.ProductAttributeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private List<ProductAttributeDto> attributes;
    private Integer productTemplateId;
}
