package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "group_sms_rate")
public class GroupSmsRate {
    @Id
    @Column(name = "RATE_ID", nullable = false)
    private Integer id;

    @Column(name = "RATE_NAME", length = 100)
    private String rateName;

    @Column(name = "WITH_MASK_COST", nullable = false, precision = 10, scale = 2)
    private BigDecimal withMaskCost;

    @Column(name = "WITHOUT_MASK_COST", nullable = false, precision = 10, scale = 2)
    private BigDecimal withoutMaskCost;

    @Column(name = "ed", nullable = false)
    private LocalDate ed;

    @Column(name = "td", nullable = false)
    private LocalDate td;

    @Column(name = "STATUS")
    private Byte status;

    @Column(name = "ADD_DATE")
    private LocalDateTime addDate;

    @Column(name = "ADD_USER", length = 50)
    private String addUser;

    @Column(name = "ADD_TERM", length = 50)
    private String addTerm;

    @Column(name = "ADD_IP", length = 50)
    private String addIp;

    @Column(name = "MOD_DATE")
    private LocalDateTime modDate;

    @Column(name = "MOD_USER", length = 50)
    private String modUser;

    @Column(name = "MOD_TERM", length = 50)
    private String modTerm;

    @Column(name = "MOD_IP", length = 50)
    private String modIp;

}