package com.github.fa2bio.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fa2bio.domain.filter.DailySaleFilter;
import com.github.fa2bio.domain.service.SaleQueryService;
import com.github.fa2bio.domain.service.SaleReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfSaleReportService implements SaleReportService{
	
	@Autowired
	private SaleQueryService vendaQueryService;

	@Override
	public byte[] issueDailySales(DailySaleFilter filter, String timeOffset) {
		
		try {
			var inputStream = this.getClass().getResourceAsStream(
					"/reports/daily-sales.jasper");
			
			var parameters = new HashMap<String, Object>();
			parameters.put("REPORT_LOCALE", new Locale("pt","BR"));
			
			var dailySales = vendaQueryService.consultDailySales(filter, timeOffset);
					
			var dataSource = new JRBeanCollectionDataSource(dailySales);
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);
			
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException(
					"Unable to issue daily sales report", e);
		}
	}

}
