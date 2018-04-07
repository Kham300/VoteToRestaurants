package com.vote2go.votingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidates")
public class Candidate {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="candidate_name")
    private String name;

    @Column(name="number_of_votes")
    private Integer countOfVotes;

    public Candidate() {
    }

    public Candidate(Long id, String name, int countOfVotes) {
        this.id = id;
        this.name = name;
        this.countOfVotes = countOfVotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountOfVotes() {
        return countOfVotes;
    }

    public void setCountOfVotes(Integer countOfVotes) {
        this.countOfVotes = countOfVotes;
    }

}
