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
@Table(name = "attribute")
public class Attribute {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ATTRIBUTE_NAME", nullable = false, length = 100)
    private String attributeName;

    @Column(name = "TYPE1", length = 10)
    private String type1;

    @Column(name = "TYPE2", length = 10)
    private String type2;

    @Column(name = "TYPE3", length = 10)
    private String type3;

    @Column(name = "SHOWING_ORDER")
    private Integer showingOrder;

    @Column(name = "PUBLISHED")
    private Byte published;

}