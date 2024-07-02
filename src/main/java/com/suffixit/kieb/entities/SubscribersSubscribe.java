package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscribers_subscribe")
public class SubscribersSubscribe {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIBERS_ID")
    private Subscriber subscribers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBSCRIBE_OPTION_ID")
    private SubscribeOption subscribeOption;

    @Column(name = "ADD_DATE", length = 45)
    private String addDate;

    @Column(name = "ADD_USER", length = 45)
    private String addUser;

    @Column(name = "ADD_TERM", length = 45)
    private String addTerm;

    @Column(name = "ADD_IP", length = 45)
    private String addIp;

}