package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Criminoso;

@Repository
public interface CriminosoRepository extends JpaRepository<Criminoso, Long>{

}
