package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafioapi.entities.Prisao;

@Repository
public interface PrisaoRepository extends JpaRepository<Prisao, Long>{

}
