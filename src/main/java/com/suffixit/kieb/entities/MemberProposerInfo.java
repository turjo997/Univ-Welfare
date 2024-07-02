package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_proposer_info")
public class MemberProposerInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proposer_id", nullable = false)
    private Member proposer;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "app_rej_date")
    private LocalDateTime appRejDate;

}