package com.gft.desafioapi.repositories.policial;

import java.util.List;

import com.gft.desafioapi.entities.Policial;
import com.gft.desafioapi.repositories.filter.PolicialFilter;

public interface PolicialRepositoryQuery {
	
	public List<Policial> policialFiltrarPorNome(PolicialFilter policialFilter);

}
