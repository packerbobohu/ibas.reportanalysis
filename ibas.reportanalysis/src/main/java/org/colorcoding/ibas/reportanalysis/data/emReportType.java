package org.colorcoding.ibas.reportanalysis.data;

import org.colorcoding.ibas.bobas.mapping.Value;

/**
 * 报表类型
 * 
 * @author Niuren.Zhu
 *
 */
public enum emReportType {
	/**
	 * 系统报表
	 */
	@Value("R")
	REPORT,
	/**
	 * 水晶报表服务
	 */
	@Value("C")
	CRYSTAL,
}
