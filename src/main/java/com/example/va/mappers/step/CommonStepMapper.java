package com.example.va.mappers.step;

import com.example.va.core.service.step._common.StepDto;
import com.example.va.data.step.StepDataMapper;
import com.example.va.mappers.producttemplateattribute.ProductTemplateAttributeMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommonStepMapper implements StepMapper {

    ProductTemplateAttributeMapper productTemplateAttributeMapper;

    CommonStepMapper(ProductTemplateAttributeMapper productTemplateAttributeMapper) {
        this.productTemplateAttributeMapper = productTemplateAttributeMapper;
    }

    @Override
    public StepDto fromEntity(StepDataMapper entity) {
        return new StepDto(entity.getId(), entity.getName(), entity.getOrder(), entity.getProductTemplateId(),
                Optional.ofNullable(entity.getProductTemplateAttributes()).map(
                        productTemplateAttributes -> productTemplateAttributes.stream().map(
                                productTemplateAttributeMapper::fromEntity).collect(
                                Collectors.toList())).orElse(Collections.emptyList()));
    }

    @Override
    public StepDataMapper toEntity(StepDto dto) {
        return new StepDataMapper(dto.getName(), dto.getOrder(), dto.getProductTemplateId(),
                Optional.ofNullable(dto.getProductTemplateAttributes()).map(
                        productTemplateAttributes -> productTemplateAttributes.stream().map(
                                productTemplateAttributeMapper::toEntity).collect(
                                Collectors.toList())).orElse(Collections.emptyList()));
    }
}
