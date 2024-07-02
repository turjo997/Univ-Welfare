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
@Table(name = "ieb_slug")
public class IebSlug {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "slug_name", nullable = false, length = 200)
    private String slugName;

    @Column(name = "slug", length = 200)
    private String slug;

    @Column(name = "parent_id", nullable = false)
    private Integer parentId;

}