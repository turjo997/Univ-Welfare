package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "update_password_info")
public class UpdatePasswordInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "pass_key", nullable = false, length = 200)
    private String passKey;

    @Column(name = "request_time")
    private LocalDateTime requestTime;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    @Column(name = "status", nullable = false)
    private Byte status;

}