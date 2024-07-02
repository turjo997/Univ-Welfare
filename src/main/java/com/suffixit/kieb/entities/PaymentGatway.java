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
@Table(name = "payment_gatway")
public class PaymentGatway {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "store_id", nullable = false, length = 500)
    private String storeId;

    @Column(name = "store_pass", nullable = false, length = 500)
    private String storePass;

    @Column(name = "store_mode", nullable = false, length = 500)
    private String storeMode;

    @Column(name = "store_host", nullable = false, length = 500)
    private String storeHost;

    @Column(name = "store_key", nullable = false, length = 500)
    private String storeKey;

    @Column(name = "store_opt1", nullable = false, length = 500)
    private String storeOpt1;

    @Column(name = "store_opt2", nullable = false, length = 500)
    private String storeOpt2;

    @Column(name = "store_opt3", nullable = false, length = 500)
    private String storeOpt3;

    @Column(name = "store_opt4", nullable = false, length = 500)
    private String storeOpt4;

    @Column(name = "store_opt5", nullable = false, length = 500)
    private String storeOpt5;

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