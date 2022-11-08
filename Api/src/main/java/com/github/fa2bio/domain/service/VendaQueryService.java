package com.github.fa2bio.domain.service;

import java.util.List;

import com.github.fa2bio.domain.filter.VendaDiariaFilter;
import com.github.fa2bio.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
