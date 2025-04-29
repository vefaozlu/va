package com.example.va.core.service.producttemplate.getproducttemplatebyid;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetProductTemplateByIdRequest {
    private Integer id;
}