package com.github.fa2bio.domain.service;

import java.util.List;

import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.model.dto.DailySale;

public interface SaleQueryService {
	
	List<DailySale> consultDailySales(DailySaleFilter filter, String timeOffset);
}
