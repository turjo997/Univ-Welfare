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
@Table(name = "outbox")
public class Outbox {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email_id", nullable = false, length = 100)
    private String emailId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;

    @Column(name = "response_msg", length = 500)
    private String responseMsg;

}