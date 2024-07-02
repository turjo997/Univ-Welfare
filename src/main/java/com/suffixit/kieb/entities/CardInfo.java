package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "card_info")
public class CardInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "consumer_id", nullable = false)
    private Integer consumerId;

    @Column(name = "card_name", nullable = false, length = 45)
    private String cardName;

    @Column(name = "card_no", nullable = false, length = 45)
    private String cardNo;

    @Column(name = "card_code", nullable = false, length = 45)
    private String cardCode;

    @Column(name = "expire_month", nullable = false, length = 45)
    private String expireMonth;

    @Column(name = "expire_year", nullable = false, length = 45)
    private String expireYear;

    @Column(name = "save_info", nullable = false)
    private Integer saveInfo;

    @Column(name = "add_date", nullable = false)
    private LocalDateTime addDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "ed")
    private LocalDate ed;

    @Column(name = "td")
    private LocalDate td;

}