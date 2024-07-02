package com.suffixit.kieb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TempIncomeStatementPtdId implements Serializable {
    private static final long serialVersionUID = 413585689949545036L;
    @Column(name = "GL_TYPE", nullable = false, length = 200)
    private String glType;

    @Column(name = "GL_NARRATION", nullable = false, length = 200)
    private String glNarration;

    @Column(name = "USER_ID", nullable = false, length = 100)
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TempIncomeStatementPtdId entity = (TempIncomeStatementPtdId) o;
        return Objects.equals(this.glType, entity.glType) &&
                Objects.equals(this.glNarration, entity.glNarration) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(glType, glNarration, userId);
    }

}