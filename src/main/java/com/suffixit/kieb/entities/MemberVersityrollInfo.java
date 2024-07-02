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
@Table(name = "member_versityroll_info")
public class MemberVersityrollInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "university_roll", nullable = false, length = 300)
    private String universityRoll;

}