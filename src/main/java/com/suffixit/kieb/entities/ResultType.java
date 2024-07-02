package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "result_type")
public class ResultType {
    @Id
    @Column(name = "result_type_id", nullable = false)
    private Integer id;

    
    @Column(name = "result_type_name")
    private String resultTypeName;

}