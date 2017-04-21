package org.colorcoding.ibas.reportanalysis.data;

import org.colorcoding.ibas.bobas.mapping.Value;

/**
 * 报表参数类型
 * 
 * @author Niuren.Zhu
 *
 */
public enum emReportParameterType {
	/**
	 * 自由文本
	 */
	@Value("TXT")
	FREE_TEXT,
	/**
	 * 日期
	 */
	@Value("DATE")
	DATETIME,
	/**
	 * 系统变量
	 */
	@Value("SYS")
	SYSTEM_VARIABLES,

	/**
	 * 范围值
	 */
	@Value("RANG")
	RANGE_VALUES,

	/**
	 * 查询结果
	 */
	@Value("SQL")
	SQL_RESULT,
}
