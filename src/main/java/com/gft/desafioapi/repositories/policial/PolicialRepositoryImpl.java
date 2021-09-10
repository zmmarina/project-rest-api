package com.gft.desafioapi.repositories.policial;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gft.desafioapi.entities.Policial;
import com.gft.desafioapi.repositories.filter.PolicialFilter;

public class PolicialRepositoryImpl implements PolicialRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Policial> policialFiltrarPorNome(PolicialFilter policialFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Policial> criteria = builder.createQuery(Policial.class);
		Root<Policial> root = criteria.from(Policial.class);
		Predicate[] predicates = criarRestricoes(policialFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Policial> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(PolicialFilter policialFilter, CriteriaBuilder builder, Root<Policial> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(policialFilter.getNome() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + policialFilter.getNome().toLowerCase() + "%"));			
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
