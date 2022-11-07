package com.github.fa2bio.domain.service;

import com.github.fa2bio.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
