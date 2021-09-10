package com.gft.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafioapi.entities.Policial;
import com.gft.desafioapi.repositories.policial.PolicialRepositoryQuery;

public interface PolicialRepository extends JpaRepository<Policial, Long>, PolicialRepositoryQuery{

}
