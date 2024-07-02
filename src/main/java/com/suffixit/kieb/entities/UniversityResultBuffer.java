package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "university_result_buffer")
public class UniversityResultBuffer {
    @Id
    @Column(name = "result_id", nullable = false)
    private Integer id;

    @Column(name = "student_id", nullable = false, length = 100)
    private String studentId;

    @Column(name = "student_name", nullable = false, length = 300)
    private String studentName;

    @Column(name = "student_birth_date")
    private LocalDate studentBirthDate;

    @Column(name = "passing_year")
    private Integer passingYear;

    @Column(name = "student_result", nullable = false, length = 10)
    private String studentResult;

    @Column(name = "admission_session", length = 100)
    private String admissionSession;

    @Column(name = "engineering_field", length = 500)
    private String engineeringField;

    @Column(name = "required_credit_hrs", length = 50)
    private String requiredCreditHrs;

    @Column(name = "university_id", nullable = false)
    private Integer universityId;

    @Column(name = "file_id", nullable = false)
    private Integer fileId;

    @Column(name = "status")
    private Byte status;

    @Column(name = "add_user", length = 45)
    private String addUser;

    @Column(name = "add_date", length = 45)
    private String addDate;

    @Column(name = "add_term", length = 45)
    private String addTerm;

    @Column(name = "add_ip", length = 45)
    private String addIp;

    @Column(name = "mod_user", length = 45)
    private String modUser;

    @Column(name = "mod_date", length = 45)
    private String modDate;

    @Column(name = "mod_term", length = 45)
    private String modTerm;

    @Column(name = "mod_ip", length = 45)
    private String modIp;

}