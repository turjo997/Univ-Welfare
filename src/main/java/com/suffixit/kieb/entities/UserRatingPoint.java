package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_rating_point")
public class UserRatingPoint {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "rating_point")
    private Integer ratingPoint;

    @Column(name = "share_content_point")
    private Integer shareContentPoint;

    @Column(name = "content_upload_point")
    private Integer contentUploadPoint;

    @Column(name = "blood_donation_point")
    private Integer bloodDonationPoint;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

}