package org.colorcoding.ibas.reportanalysis.reporter;

import org.colorcoding.ibas.bobas.data.IDataTable;
import org.colorcoding.ibas.bobas.data.KeyText;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;

/**
 * 水晶报表者
 * 
 * @author Niuren.Zhu
 *
 */
public class CrystalReporter extends Reporter {
	@Override
	public IDataTable run(IReport report) {
		// TODO:没有完全实现
		KeyText value = new KeyText();
		value.key = "${Url}";
		value.text = report.getAddress();
		return this.create(new KeyText[] { value });
	}
}
