package com.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 1000)
    private String accessToken;
    @Column(length = 1000)
    private String refreshToken;

    private LocalDateTime accessTokenExpiry;
    private LocalDateTime refreshTokenExpiry;
    private boolean revoked;
    private LocalDateTime createdAt;

}
