package com.example.va.data.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductDataMapper, Integer> {
}
