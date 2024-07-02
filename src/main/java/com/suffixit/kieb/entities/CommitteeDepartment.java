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
@Table(name = "committee_department")
public class CommitteeDepartment {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "department_name", length = 200)
    private String departmentName;

    @Column(name = "short_name", length = 100)
    private String shortName;

    @Column(name = "orderby", nullable = false)
    private Integer orderby;

    @Column(name = "committee_id", nullable = false)
    private Integer committeeId;

}