package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member_rating_history")
public class MemberRatingHistory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "rating_date")
    private LocalDateTime ratingDate;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_user", length = 45)
    private String modUser;

}