package com.example.va.mappers.producttemplateattribute;

import com.example.va.core.service.producttemplateattribute._common.dto.ProductTemplateAttributeDto;
import com.example.va.data.producttemplateattribute.ProductTemplateAttributeDataMapper;
import org.springframework.stereotype.Component;

@Component
public class CommonProductTemplateAttributeMapper implements ProductTemplateAttributeMapper {

    @Override
    public ProductTemplateAttributeDto fromEntity(ProductTemplateAttributeDataMapper entity) {
        return new ProductTemplateAttributeDto(entity.getId(), entity.getName(), entity.getType(), entity.isAllowNull(),
                entity.isCalculated(), entity.getFormula(), entity.getVariables(), entity.getConditions(),
                entity.isConstant(), entity.getDefaultValue(),
                entity.getProductTemplateId(), entity.getStepId());
    }

    @Override
    public ProductTemplateAttributeDataMapper toEntity(ProductTemplateAttributeDto dto) {
        return new ProductTemplateAttributeDataMapper(dto.getName(), dto.getType(), dto.isAllowNull(),
                dto.isCalculated(), dto.getFormula(), dto.getVariables(), dto.getConditions(), dto.isConstant(),
                dto.getDefaultValue(), dto.getProductTemplateId(), dto.getStepId());
    }
}
