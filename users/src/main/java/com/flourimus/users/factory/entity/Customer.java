package com.flourimus.users.factory.entity;

import java.time.LocalDateTime;

import com.flourimus.users.enums.GrantTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flourimus_common_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 64)
    private String firstName;

    @Column(name = "last_name", length = 64)
    private String lastName;

    @Column(length = 120, nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Phone phone;

    private String avatar;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "grand_type", nullable = false)
    private GrantTypes grandType;

    private LocalDateTime dob;

    @Column(nullable = false)
    private Integer roleId;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerToken tokens;

    @Column(name = "organization_id", nullable = false)
    private Integer organizationId;

    @Column(name = "location_id", nullable = false)
    private Integer locationId;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "channel_id", nullable = false)
    private Integer channelId;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

}
