package org.colorcoding.ibas.reportanalysis.repository;

import org.colorcoding.ibas.bobas.common.ConditionRelationship;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.organization.IOrganizationManager;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.organization.fantasy.OrganizationManager;
import org.colorcoding.ibas.bobas.ownership.PermissionGroup;
import org.colorcoding.ibas.bobas.repository.BORepositoryServiceApplication;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.report.Report;
import org.colorcoding.ibas.reportanalysis.bo.users.UserReport;

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
