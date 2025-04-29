package com.example.va.mappers.step;

import com.example.va.core.service.step._common.StepDto;
import com.example.va.data.step.StepDataMapper;

public interface StepMapper {
    StepDto fromEntity(StepDataMapper entity);

    StepDataMapper toEntity(StepDto dto);
}
