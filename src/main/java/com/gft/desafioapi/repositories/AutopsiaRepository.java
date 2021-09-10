package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Autopsia;

@Repository
public interface AutopsiaRepository extends JpaRepository<Autopsia, Long>{

}
