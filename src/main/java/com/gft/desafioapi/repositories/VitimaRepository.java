package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Vitima;

@Repository
public interface VitimaRepository extends JpaRepository<Vitima, Long>{

}
