package com.example.va.data.productattribute;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "product_attributes")
@SQLDelete(sql = "UPDATE product_attributes SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeDataMapper {
    String name;
    Double length;
    Double height;
    Double width;
    String color;
    Integer productId;
    Integer productTemplateAttributeId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    public ProductAttributeDataMapper(Integer id, String name, Double length, Double width, Double height, String color, Integer productId, Integer productTemplateAttributeId) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.height = height;
        this.width = width;
        this.color = color;
        this.productId = productId;
        this.productTemplateAttributeId = productTemplateAttributeId;
    }
}
