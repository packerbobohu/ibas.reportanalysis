package org.colorcoding.ibas.reportanalysis.reporter;

import org.colorcoding.ibas.bobas.data.IDataTable;
import org.colorcoding.ibas.bobas.data.KeyText;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;

/**
 * KPI报表者
 * 
 * @author Niuren.Zhu
 *
 */
public class KpiReporter extends Reporter {
	@Override
	public IDataTable run(IReport report) throws Exception {
		KeyText value = new KeyText();
		value.key = "${Kpi}";
		value.text = "100";
		return this.create(new KeyText[] { value });
	}
}
