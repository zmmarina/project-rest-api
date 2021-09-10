package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Legista;

@Repository
public interface LegistaRepository extends JpaRepository<Legista, Long>{

}
