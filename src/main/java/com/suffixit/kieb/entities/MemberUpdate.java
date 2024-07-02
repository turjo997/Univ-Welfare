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
@Table(name = "member_update")
public class MemberUpdate {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "member_id_old", nullable = false, length = 50)
    private String memberIdOld;

    @Column(name = "member_id_new", nullable = false, length = 50)
    private String memberIdNew;

}