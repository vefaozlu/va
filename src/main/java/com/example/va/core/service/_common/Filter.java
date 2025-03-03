package com.example.va.core.service._common;

import org.springframework.data.domain.Sort;

import java.util.Date;

public interface Filter {
    Integer getCount();

    Integer getOffset();

    Sort.Direction getOrderDirection();

    String getOrderProperty();

    String getSearch();

    Date getStartDate();

    Date getEndDate();
}
