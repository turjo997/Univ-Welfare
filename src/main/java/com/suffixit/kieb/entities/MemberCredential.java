package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_credential")
public class MemberCredential {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "member_key", nullable = false, length = 512)
    private String memberKey;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

}