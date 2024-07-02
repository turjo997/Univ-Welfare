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
@Table(name = "member_fine_list")
public class MemberFineList {
    @Id
    @Column(name = "member_id", nullable = false)
    private Integer id;

    //TODO [JPA Buddy] generate columns from DB
}