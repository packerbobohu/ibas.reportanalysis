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
	TEXT,
	/**
	 * 日期
	 */
	@Value("DATE")
	DATETIME,
	/**
	 * 系统变量
	 */
	@Value("SYS")
	SYSTEM,

	/**
	 * 范围值
	 */
	@Value("RANG")
	RANGE,

	/**
	 * 查询结果
	 */
	@Value("SQL")
	SQL,
	/**
	 * 预置值
	 */
	@Value("SET")
	PRESET,
}
