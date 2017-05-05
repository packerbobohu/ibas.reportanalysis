package org.colorcoding.ibas.reportanalysis.repository;

import org.colorcoding.ibas.bobas.common.ConditionRelationship;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.ISqlQuery;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.common.SqlQuery;
import org.colorcoding.ibas.bobas.data.DataTable;
import org.colorcoding.ibas.bobas.data.IDataTable;
import org.colorcoding.ibas.bobas.data.IDataTableColumn;
import org.colorcoding.ibas.bobas.data.IDataTableRow;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.i18n.i18n;
import org.colorcoding.ibas.bobas.organization.IOrganizationManager;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.organization.fantasy.OrganizationManager;
import org.colorcoding.ibas.bobas.ownership.PermissionGroup;
import org.colorcoding.ibas.bobas.repository.BORepository4DbReadonly;
import org.colorcoding.ibas.bobas.repository.BORepositoryServiceApplication;
import org.colorcoding.ibas.bobas.repository.IBORepository4DbReadonly;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.report.Report;
import org.colorcoding.ibas.reportanalysis.bo.users.UserReport;
import org.colorcoding.ibas.reportanalysis.data.emReportType;

/**
 * ReportAnalysis仓库
 */
@PermissionGroup("ReportAnalysis")
public class BORepositoryReportAnalysis extends BORepositoryServiceApplication
		implements IBORepositoryReportAnalysisSvc, IBORepositoryReportAnalysisApp {

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
	public OperationResult<UserReport> fetchUserReports(String user, String token) {
		OperationResult<UserReport> opRslt = new OperationResult<UserReport>();
		try {
			this.setUserToken(token);
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setAlias(Report.PROPERTY_ACTIVATED.getName());
			condition.setValue(emYesNo.YES);
			// 自己的查询
			condition = criteria.getConditions().create();
			condition.setBracketOpen(1);
			condition.setAlias(Report.PROPERTY_DATAOWNER.getName());
			condition.setValue(this.getCurrentUser().getId());
			// 所属角色的查询
			IOrganizationManager manager = OrganizationFactory.create().createManager();
			if (manager instanceof OrganizationManager) {
				OrganizationManager ifManager = (OrganizationManager) manager;
				for (String role : ifManager.getUserRoles(this.getCurrentUser())) {
					condition = criteria.getConditions().create();
					condition.setRelationship(ConditionRelationship.OR);
					condition.setAlias(Report.PROPERTY_GROUP.getName());
					condition.setValue(role);
				}
			}
			condition.setBracketClose(1);
			IOperationResult<Report> opRsltFetch = this.fetchReport(criteria, token);
			if (opRsltFetch.getError() != null) {
				throw opRsltFetch.getError();
			}
			if (opRsltFetch.getResultCode() != 0) {
				throw new Exception(opRsltFetch.getMessage());
			}
			for (Report boItem : opRsltFetch.getResultObjects()) {
				UserReport uReport = UserReport.create(boItem);
				boolean has = false;
				for (UserReport uItem : opRslt.getResultObjects()) {
					if (uItem.getId().equals(uReport.getId())) {
						has = true;
						break;
					}
				}
				if (!has) {
					// 已存在不再添加
					opRslt.addResultObjects(uReport);
				}
			}
		} catch (Exception e) {
			opRslt.setError(e);
		}
		return opRslt;
	}

	/**
	 * 查询-用户报表
	 * 
	 * @param user
	 *            用户
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public IOperationResult<UserReport> fetchUserReports(String user) {
		return this.fetchUserReports(user);
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
	public OperationResult<DataTable> runUserReport(UserReport report, String token) {
		OperationResult<DataTable> opRslt = new OperationResult<DataTable>();
		try {
			this.setUserToken(token);
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setAlias(Report.PROPERTY_ACTIVATED.getName());
			condition.setValue(emYesNo.YES);
			condition = criteria.getConditions().create();
			condition.setAlias(Report.PROPERTY_OBJECTKEY.getName());
			condition.setValue(report.getId());
			IOperationResult<Report> opRsltFetch = this.fetchReport(criteria, token);
			if (opRsltFetch.getError() != null) {
				throw opRsltFetch.getError();
			}
			if (opRsltFetch.getResultCode() != 0) {
				throw new Exception(opRsltFetch.getMessage());
			}
			Report rReport = opRsltFetch.getResultObjects().firstOrDefault();
			if (rReport == null) {
				throw new Exception(i18n.prop("msg_ra_not_found_report",
						report.getName() != null ? report.getName() : report.getId()));
			}
			if (rReport.getCategory() == emReportType.REPORT) {
				opRslt.addResultObjects(this.queryReport(rReport));
				return opRslt;
			} else if (rReport.getCategory() == emReportType.CRYSTAL) {
				// TODO:没有完全实现
				IDataTable table = new DataTable();
				IDataTableColumn columnKey = table.getColumns().create();
				columnKey.setName("Key");
				columnKey.setDataType(String.class);
				IDataTableColumn columnValue = table.getColumns().create();
				columnValue.setName("Value");
				columnValue.setDataType(String.class);
				IDataTableRow row = table.getRows().create();
				row.setValue(columnKey, "${Url}");
				row.setValue(columnValue, rReport.getAddress());
				opRslt.addResultObjects(table);
				return opRslt;
			}
			throw new Exception(i18n.prop("msg_ra_not_allowed_run_report",
					report.getName() != null ? report.getName() : report.getId()));
		} catch (Exception e) {
			opRslt.setError(e);
		}
		return opRslt;
	}

	protected IDataTable queryReport(Report report) throws Exception {
		if (report == null || report.getSqlString() == null || report.getSqlString().isEmpty()) {
			throw new Exception(i18n.prop("msg_ra_invaild_report_query",
					report.getName() != null ? report.getName() : report.getObjectKey()));
		}
		IBORepository4DbReadonly boRepository = new BORepository4DbReadonly("Master");
		ISqlQuery sqlQuery = new SqlQuery();
		sqlQuery.setQueryString(report.getSqlString());
		IOperationResult<IDataTable> opRslt = boRepository.query(sqlQuery);
		if (opRslt.getError() != null) {
			throw opRslt.getError();
		}
		if (opRslt.getResultCode() != 0) {
			throw new Exception(opRslt.getMessage());
		}
		return opRslt.getResultObjects().firstOrDefault();
	}

	/**
	 * 运行-用户报表
	 * 
	 * @param report
	 *            用户报表
	 * @return 操作结果
	 */
	public IOperationResult<DataTable> runUserReport(UserReport report) {
		return this.runUserReport(report, this.getUserToken());
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
	public OperationResult<Report> fetchReport(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Report.class);
	}

	/**
	 * 查询-报表（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IReport> fetchReport(ICriteria criteria) {
		return new OperationResult<IReport>(this.fetchReport(criteria, this.getUserToken()));
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
	public OperationResult<Report> saveReport(Report bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-报表（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IReport> saveReport(IReport bo) {
		return new OperationResult<IReport>(this.saveReport((Report) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

}
