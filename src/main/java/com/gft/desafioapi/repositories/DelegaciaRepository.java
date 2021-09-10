package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Delegacia;

@Repository
public interface DelegaciaRepository extends JpaRepository<Delegacia, Long>{

}
