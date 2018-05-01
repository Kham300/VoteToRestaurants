package com.vote2go.votingsystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
@MappedSuperclass
public class NamedEntity extends BaseEntity{

    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 255)
    @SafeHtml
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
