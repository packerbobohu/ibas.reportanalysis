package org.colorcoding.ibas.reportanalysis.repository;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.repository.IBORepositoryApplication;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.users.UserReport;

/**
 * ReportAnalysis仓库应用
 */
public interface IBORepositoryReportAnalysisApp extends IBORepositoryApplication {
	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-用户报表
	 * 
	 * @param user
	 *            用户
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	IOperationResult<UserReport> fetchUserReports(String user);

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-报表
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	IOperationResult<IReport> fetchReport(ICriteria criteria);

	/**
	 * 保存-报表
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	IOperationResult<IReport> saveReport(IReport bo);

	// --------------------------------------------------------------------------------------------//

}
