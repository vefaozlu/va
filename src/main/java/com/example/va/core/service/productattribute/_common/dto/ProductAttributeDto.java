package com.example.va.core.service.productattribute._common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductAttributeDto {
    Integer id;
    String name;
    Double length;
    Double width;
    Double height;
    String color;
    Integer productId;
    Integer productTemplateId;
}
