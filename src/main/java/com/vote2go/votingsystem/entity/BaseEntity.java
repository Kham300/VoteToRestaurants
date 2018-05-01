package com.vote2go.votingsystem.entity;

import com.vote2go.votingsystem.HasId;
import org.hibernate.Hibernate;

import javax.persistence.*;

/**
 * Mamytov Khamidullo
 * 14.03.2018
 */

@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity implements HasId {

    public static final int START_SEQ = 100_000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private int id;

    public BaseEntity(){
    }

    protected BaseEntity(Integer id){
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))){
            return false;
        }
        BaseEntity that = (BaseEntity) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode(){
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString(){
        return String.format("Entity of type %s with id: %s", getClass().getName(), getId());
    }
}
