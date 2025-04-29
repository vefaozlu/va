package com.example.va.data.producttemplate;

import com.example.va.data.producttemplateattribute.ProductTemplateAttributeDataMapper;
import com.example.va.data.step.StepDataMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product_templates")
@SQLDelete(sql = "UPDATE product_templates SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTemplateDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_template_id")
    private List<ProductTemplateAttributeDataMapper> productTemplateAttributes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_template_id")
    private List<StepDataMapper> steps;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    public ProductTemplateDataMapper(String name, List<ProductTemplateAttributeDataMapper> productTemplateAttributes,
                                     List<StepDataMapper> steps) {
        this.name = name;
        this.productTemplateAttributes = productTemplateAttributes;
        this.steps = steps;
    }
}
