package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Crime;

@Repository
public interface CrimeRepository extends JpaRepository<Crime, Long>{

}
