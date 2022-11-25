package com.github.fa2bio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.swaggeropenapi.controller.StatisticsControllerSwagger;
import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.model.dto.DailySale;
import com.github.fa2bio.domain.service.SaleQueryService;
import com.github.fa2bio.domain.service.SaleReportService;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController implements StatisticsControllerSwagger{

	@Autowired
	private SaleQueryService saleQueryService;
	
	@Autowired
	private SaleReportService saleReportService;
	
	@Override
	@GetMapping(path = "/daily-sales", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<DailySale> queryDailySalesJson(DailySaleFilter filter,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
		return saleQueryService.consultDailySales(filter, timeOffset);
	}
	
	@Override
	@GetMapping(path = "/daily-sales", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> queryDailySalesJsonPdf(DailySaleFilter filter,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
		byte[] bytesPdf = saleReportService.issueDailySales(filter, timeOffset);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
}