package com.example.va.core.service.producttemplate.getproducttemplatebyid;

import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductTemplateByIdResponse {
    private ProductTemplateDto productTemplate;
} 