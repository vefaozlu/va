package com.example.va.core.service.producttemplate._common.dto;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import com.example.va.core.service.step._common.StepDto;
import com.example.va.data.step.StepDataMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTemplateDto {
    private Integer id;
    private String name;
    private List<ProductTemplateAttributeDto> productTemplateAttributes;
    private List<StepDto> steps;
}
