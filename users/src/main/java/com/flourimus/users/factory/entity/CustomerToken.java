package com.flourimus.users.factory.entity;

import com.flourimus.users.common.enums.TokensKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flourimus_common_customer_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token_keys", nullable = false)
    private TokensKeys key;

    @Column(nullable = false)
    private String value;

}
