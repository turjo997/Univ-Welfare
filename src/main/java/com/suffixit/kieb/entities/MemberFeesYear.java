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
@Table(name = "member_fees_year")
public class MemberFeesYear {
    @Id
    @Column(name = "member_fees_year_id", nullable = false)
    private Integer id;

    @Column(name = "member_fees_year_name", nullable = false, length = 50)
    private String memberFeesYearName;

    @Column(name = "ordering", nullable = false)
    private Integer ordering;

}