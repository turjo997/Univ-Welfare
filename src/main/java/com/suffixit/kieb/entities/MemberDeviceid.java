package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_deviceid")
public class MemberDeviceid {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "device_id", nullable = false, length = 500)
    private String deviceId;

    @Column(name = "status")
    private Byte status;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

}