package com.example.va.data.step;

import com.example.va.data.producttemplateattribute.ProductTemplateAttributeDataMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "refresh_tokens")
@SQLDelete(sql = "UPDATE refresh_tokens SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@NoArgsConstructor
public class StepDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer order;

    @Column(name = "product_template_id")
    private Integer productTemplateId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "step_id")
    private List<ProductTemplateAttributeDataMapper> productTemplateAttributes;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    public StepDataMapper(String name, Integer order, Integer productTemplateId,
                          List<ProductTemplateAttributeDataMapper> productTemplateAttributes) {
        this.name = name;
        this.order = order;
        this.productTemplateId = productTemplateId;
        this.productTemplateAttributes = productTemplateAttributes;
    }
}
