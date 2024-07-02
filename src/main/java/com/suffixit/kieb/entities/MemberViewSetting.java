package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_view_setting")
public class MemberViewSetting {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "phone1_view", nullable = false)
    private Byte phone1View;

    @Column(name = "phone2_view", nullable = false)
    private Byte phone2View;

    @Column(name = "mobile_view", nullable = false)
    private Byte mobileView;

    @Column(name = "email_view", nullable = false)
    private Byte emailView;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

}