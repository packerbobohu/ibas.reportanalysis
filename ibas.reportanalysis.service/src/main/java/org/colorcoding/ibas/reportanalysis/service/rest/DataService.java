package org.colorcoding.ibas.reportanalysis.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.data.DataTable;
import org.colorcoding.ibas.reportanalysis.bo.report.Report;
import org.colorcoding.ibas.reportanalysis.bo.users.UserReport;
import org.colorcoding.ibas.reportanalysis.repository.BORepositoryReportAnalysis;

/**
 * ReportAnalysis 数据服务JSON
 */
@Path("data")
public class DataService extends BORepositoryReportAnalysis {

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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("fetchUserReports")
	public OperationResult<UserReport> fetchUserReports(@QueryParam("user") String user,
			@QueryParam("token") String token) {
		return super.fetchUserReports(user, token);
	}

	/**
	 * 运行-用户报表
	 * 
	 * @param report
	 *            用户报表
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("runUserReport")
	public OperationResult<DataTable> runUserReport(UserReport report, @QueryParam("token") String token) {
		return super.runUserReport(report, token);
	}

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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("fetchReport")
	public OperationResult<Report> fetchReport(Criteria criteria, @QueryParam("token") String token) {
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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("saveReport")
	public OperationResult<Report> saveReport(Report bo, @QueryParam("token") String token) {
		return super.saveReport(bo, token);
	}

	// --------------------------------------------------------------------------------------------//

}
