package com.vote2go.votingsystem.repositories;

import com.vote2go.votingsystem.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizensRepo extends JpaRepository<Citizen, Integer> {

    public Citizen findByName(String name);
}
