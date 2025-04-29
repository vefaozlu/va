package com.example.va.core.service.producttemplate.getallproducttemplates;

import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllProductTemplatesResponse {
    private List<ProductTemplateDto> productTemplates;
} 