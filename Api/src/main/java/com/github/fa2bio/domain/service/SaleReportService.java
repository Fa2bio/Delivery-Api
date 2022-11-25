package com.github.fa2bio.domain.service;

import com.github.fa2bio.domain.filter.DailySaleFilter;

public interface SaleReportService {

	byte[] issueDailySales(DailySaleFilter filter, String timeOffset);
}
