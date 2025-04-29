package com.example.va.data.product;

import com.example.va.data.productattribute.ProductAttributeDataMapper;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer productTemplateId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productTemplateAttributeId")
    private List<ProductAttributeDataMapper> attributes;
}
