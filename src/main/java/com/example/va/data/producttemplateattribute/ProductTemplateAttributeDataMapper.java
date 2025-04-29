package com.example.va.data.producttemplateattribute;

import com.example.va.core.domain.producttemplateattribute.ProductTemplateAttributeType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "product_template_attributes")
@SQLDelete(sql = "UPDATE product_template_attributes SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTemplateAttributeDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductTemplateAttributeType type;

    private boolean allowNull;

    private boolean isCalculated;

    private String formula;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "variables", columnDefinition = "jsonb")
    private Map<String, String> variables;

    private String conditions;

    @Column(name = "is_constant")
    private boolean isConstant = false;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "product_template_id")
    private Integer productTemplateId;

    @Column(name = "step_id")
    private Integer stepId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    public ProductTemplateAttributeDataMapper(String name, ProductTemplateAttributeType type, boolean allowNull,
                                              boolean isCalculated, String formula, Map<String, String> variables,
                                              String conditions, boolean isConstant, String defaultValue,
                                              Integer productTemplateId, Integer stepId) {
        this.name = name;
        this.type = type;
        this.allowNull = allowNull;
        this.isCalculated = isCalculated;
        this.formula = formula;
        this.variables = variables;
        this.conditions = conditions;
        this.isConstant = isConstant;
        this.defaultValue = defaultValue;
        this.productTemplateId = productTemplateId;
        this.stepId = stepId;
    }
}
