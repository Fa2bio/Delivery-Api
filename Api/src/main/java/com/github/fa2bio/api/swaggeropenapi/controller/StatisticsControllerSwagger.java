package com.github.fa2bio.api.swaggeropenapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.fa2bio.api.controller.StatisticsController.StatisticsModel;
import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.model.dto.DailySale;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Statistics")
public interface StatisticsControllerSwagger {
	
	@ApiOperation(value = "Statistics", hidden = true)
	StatisticsModel statistics();

	@ApiOperation("Query daily sales statistics")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restaurantId", value = "Restaurant Id", 
				example = "1", dataType = "int"),
		@ApiImplicitParam(name = "creationDateStart", value = "Order creation start date/time",
			example = "2019-12-01T00:00:00Z", dataType = "date-time"),
		@ApiImplicitParam(name = "creationDateFinal", value = "Order creation end date/time",
			example = "2019-12-02T23:59:59Z", dataType = "date-time")
	})
	List<DailySale> queryDailySalesJson(
			@ApiParam(value = "Time offset to be considered in the query from UTC", defaultValue = "+00:00")
			DailySaleFilter filter, String timeOffset);
	
	ResponseEntity<byte[]> queryDailySalesJsonPdf(DailySaleFilter filter, String timeOffset);
}
