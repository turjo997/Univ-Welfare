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
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "document_id", nullable = false)
    private Integer id;

    @Column(name = "document_name", nullable = false, length = 100)
    private String documentName;

}