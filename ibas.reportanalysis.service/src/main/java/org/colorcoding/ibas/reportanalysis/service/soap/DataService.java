package org.colorcoding.ibas.reportanalysis.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.cxf.WebServicePath;
import org.colorcoding.ibas.reportanalysis.bo.report.Report;
import org.colorcoding.ibas.reportanalysis.bo.reportbook.ReportBook;
import org.colorcoding.ibas.reportanalysis.repository.BORepositoryReportAnalysis;

/**
 * ReportAnalysis 数据服务JSON
 */
@WebService
@WebServicePath("data")
public class DataService extends BORepositoryReportAnalysis {

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-报表
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@WebMethod
	public OperationResult<Report> fetchReport(@WebParam(name = "criteria") Criteria criteria,
			@WebParam(name = "token") String token) {
		return super.fetchReport(criteria, token);
	}

	/**
	 * 保存-报表
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@WebMethod
	public OperationResult<Report> saveReport(@WebParam(name = "bo") Report bo,
			@WebParam(name = "token") String token) {
		return super.saveReport(bo, token);
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-报表簿
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@WebMethod
	public OperationResult<ReportBook> fetchReportBook(@WebParam(name = "criteria") Criteria criteria,
			@WebParam(name = "token") String token) {
		return super.fetchReportBook(criteria, token);
	}

	/**
	 * 保存-报表簿
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@WebMethod
	public OperationResult<ReportBook> saveReportBook(@WebParam(name = "bo") ReportBook bo,
			@WebParam(name = "token") String token) {
		return super.saveReportBook(bo, token);
	}

	// --------------------------------------------------------------------------------------------//

}
