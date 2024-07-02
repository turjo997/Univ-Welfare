package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "gl_code")
public class GlCode {
    @Id
    @Column(name = "GL_CODE", nullable = false)
    private Integer id;

    @Column(name = "GL_NARRATION", nullable = false, length = 200)
    private String glNarration;

    @Column(name = "PARENT_GL_CODE", nullable = false)
    private Integer parentGlCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GL_TYPE_ID", nullable = false)
    private GlType glType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STORE_ID", nullable = false)
    private Store store;

    @Column(name = "ISLEAF", nullable = false)
    private Integer isleaf;

    @Column(name = "ADD_DATE")
    private LocalDate addDate;

    @Column(name = "ADD_USER", length = 100)
    private String addUser;

    @Column(name = "MOD_DATE")
    private LocalDate modDate;

    @Column(name = "MOD_USER", length = 100)
    private String modUser;

}