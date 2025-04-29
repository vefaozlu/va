package com.example.va.mappers.producttemplate;

import com.example.va.core.service.producttemplate._common.dto.ProductTemplateDto;
import com.example.va.data.producttemplate.ProductTemplateDataMapper;
import com.example.va.mappers.producttemplateattribute.ProductTemplateAttributeMapper;
import com.example.va.mappers.step.StepMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommonProductTemplateMapper implements ProductTemplateMapper {

    private final ProductTemplateAttributeMapper productTemplateAttributeMapper;
    private final StepMapper stepMapper;

    public CommonProductTemplateMapper(ProductTemplateAttributeMapper productTemplateAttributeMapper,
                                       StepMapper stepMapper) {
        this.productTemplateAttributeMapper = productTemplateAttributeMapper;
        this.stepMapper = stepMapper;
    }

    @Override
    public ProductTemplateDataMapper toEntity(ProductTemplateDto dto) {
        return new ProductTemplateDataMapper(dto.getName(), Optional.ofNullable(dto.getProductTemplateAttributes()).map(
                attributes -> attributes.stream().map(productTemplateAttributeMapper::toEntity).collect(
                        Collectors.toList())).orElse(Collections.emptyList()), Optional.ofNullable(dto.getSteps()).map(
                steps -> steps.stream().map(stepMapper::toEntity).collect(
                        Collectors.toList())).orElse(Collections.emptyList()));
    }

    @Override
    public ProductTemplateDto fromEntity(ProductTemplateDataMapper entity) {
        return new ProductTemplateDto(entity.getId(), entity.getName(),
                entity.getProductTemplateAttributes().stream().map(productTemplateAttributeMapper::fromEntity).collect(
                        Collectors.toList()), entity.getSteps().stream().map(stepMapper::fromEntity).collect(
                Collectors.toList()));
    }
}
