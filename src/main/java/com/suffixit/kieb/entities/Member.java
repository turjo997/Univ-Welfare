package com.suffixit.kieb.entities;

import com.suffixit.kieb.enumerations.ApproveStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {

    @Id
    @SequenceGenerator(name = "member_id_gen", sequenceName = "member_gen", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_gen")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "member_name", nullable = false, length = 100)
    private String memberName;
    @Column(name = "batch_name", length = 100)
    private String batchName;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Column(name = "place_of_birth", length = 100)
    private String placeOfBirth;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "blood_group", length = 20)
    private String bloodGroup;

    @Column(name = "phone1", length = 11)
    private String phone1;

    @Column(name = "phone2", length = 11)
    private String phone2;

    @Column(name = "mobile", length = 50)
    private String mobile;

    @Column(name = "email_id", nullable = false , length = 200)
    private String emailId;

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private UniversitySubject universitySubject;

    @Column(name = "scroll_no")
    private Integer scrollNo;

    @Column(name = "receipt_date")
    private LocalDate receiptDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "picture_name", length = 145)
    private String pictureName;

    @Column(name = "add_user", length = 100)
    private String addUser;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "mod_user", length = 100)
    private String modUser;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Column(name = "roll_id")
    private String roll;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_division_id")
    private MemberDivision memberDivision;

    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus;

    private String approvedBy;

    private LocalDateTime approvedDate;

}