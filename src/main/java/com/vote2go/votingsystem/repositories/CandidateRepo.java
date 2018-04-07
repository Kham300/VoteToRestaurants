package com.vote2go.votingsystem.repositories;

import com.vote2go.votingsystem.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Integer> {

    public Candidate findById(Long id);
}
