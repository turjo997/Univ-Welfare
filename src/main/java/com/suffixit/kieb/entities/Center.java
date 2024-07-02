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
@Table(name = "center")
public class Center {
    @Id
    @Column(name = "center_id", nullable = false)
    private Integer id;

    @Column(name = "center_name", nullable = false, length = 100)
    private String centerName;

    @Column(name = "center_type_id", nullable = false)
    private Integer centerTypeId;

    @Column(name = "center_parent_id")
    private Integer centerParentId;

    @Column(name = "website", length = 100)
    private String website;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "details", length = 500)
    private String details;

    @Column(name = "featured_picture", length = 100)
    private String featuredPicture;

}