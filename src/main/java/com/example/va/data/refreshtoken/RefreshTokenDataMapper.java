package com.example.va.data.refreshtoken;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "refresh_tokens")
@SQLDelete(sql = "UPDATE refresh_tokens SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
@Data
@NoArgsConstructor
public class RefreshTokenDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Date expiresAt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    private boolean deleted = Boolean.FALSE;

    RefreshTokenDataMapper(String token, Integer userId, Date expiresAt) {
        this.token = token;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }
}
