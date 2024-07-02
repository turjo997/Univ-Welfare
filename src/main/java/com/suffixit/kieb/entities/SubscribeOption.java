package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscribe_option")
public class SubscribeOption {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private Integer name;

    @Column(name = "STATUS", nullable = false)
    private Byte status;

    @Column(name = "ADD_DATE", nullable = false, length = 45)
    private String addDate;

}