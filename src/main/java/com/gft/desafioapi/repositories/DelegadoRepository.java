package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Delegado;

@Repository
public interface DelegadoRepository extends JpaRepository<Delegado, Long>{

}
