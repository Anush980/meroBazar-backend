package com.merobazar.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users") // use only when db table name is different from class name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false) // ensures database column type is UUID
    private UUID id;

    private String name;

    @Column(unique = true,nullable = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.BUYER;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

     @PrePersist
    protected void onCreate() {
        if (isActive == null) isActive = true;
        if (role == null) role = UserRole.BUYER;
    }

    public enum UserRole {
        BUYER,
        SELLER,
        ADMIN
    }
}