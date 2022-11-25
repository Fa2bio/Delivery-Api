package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.model.dto.DailySale;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Statistics")
public interface StatisticsControllerSwagger {

	@ApiOperation("Query daily sales statistics")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restauranteId", value = "Restaurant Id", 
				example = "1", dataType = "int"),
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Order creation start date/time",
			example = "2019-12-01T00:00:00Z", dataType = "date-time"),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Order creation end date/time",
			example = "2019-12-02T23:59:59Z", dataType = "date-time")
	})
	List<DailySale> queryDailySalesJson(
			@ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", defaultValue = "+00:00")
			DailySaleFilter filter, String timeOffset);
	
	ResponseEntity<byte[]> queryDailySalesJsonPdf(DailySaleFilter filter, String timeOffset);
}
