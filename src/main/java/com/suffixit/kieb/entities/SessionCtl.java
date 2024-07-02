package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "session_ctl")
public class SessionCtl {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "consumer_id", nullable = false)
    private Integer consumerId;

    @Column(name = "key_str", nullable = false, length = 450)
    private String keyStr;

    @Column(name = "req_time", nullable = false)
    private LocalDateTime reqTime;

    @Column(name = "exp_time", nullable = false)
    private LocalDateTime expTime;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

}