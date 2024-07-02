package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "outbox_event")
public class OutboxEvent {
    @Id
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @Column(name = "event_name", nullable = false, length = 305)
    private String eventName;

    @Column(name = "outbox_email_subject", length = 300)
    private String outboxEmailSubject;

    
    @Column(name = "outbox_email_body", nullable = false)
    private String outboxEmailBody;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

}