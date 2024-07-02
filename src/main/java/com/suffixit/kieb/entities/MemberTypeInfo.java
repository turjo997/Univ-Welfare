package com.suffixit.kieb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "member_type_info")
public class MemberTypeInfo {
    @Id
    @Column(name = "member_type_id", nullable = false)
    private Integer id;

    @Column(name = "member_type_name", length = 30)
    private String memberTypeName;

    @Column(name = "member_type_category")
    private Integer memberTypeCategory;

    
    @Column(name = "Details")
    private String details;

    @Column(name = "entrance_fee", precision = 7, scale = 2)
    private BigDecimal entranceFee;

    @Column(name = "re_enrollment_fee", precision = 7, scale = 2)
    private BigDecimal reEnrollmentFee;

    @Column(name = "annaral_subscription_fee", precision = 7, scale = 2)
    private BigDecimal annaralSubscriptionFee;

    @Column(name = "diploma_fee", precision = 7, scale = 2)
    private BigDecimal diplomaFee;

    @Column(name = "age")
    private Integer age;

    @Column(name = "experience", length = 100)
    private String experience;

    @Column(name = "new_fees")
    private Integer newFees;

}