package com.example.va.core.service.step._common;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepDto {
    private Integer id;
    private String name;
    private Integer order;
    private Integer productTemplateId;
    private List<ProductTemplateAttributeDto> productTemplateAttributes;
}
