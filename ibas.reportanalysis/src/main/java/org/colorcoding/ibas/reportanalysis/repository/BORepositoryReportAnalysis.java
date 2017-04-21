package org.colorcoding.ibas.reportanalysis.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.ownership.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.reportanalysis.bo.report.*;

/**
* ReportAnalysis仓库
*/
@PermissionGroup("ReportAnalysis")
public class BORepositoryReportAnalysis extends BORepositoryServiceApplication implements IBORepositoryReportAnalysisSvc, IBORepositoryReportAnalysisApp {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-报表
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    public OperationResult<Report> fetchReport(ICriteria criteria, String token) {
        return super.fetch(criteria, token, Report.class);
    }

    /**
     * 查询-报表（提前设置用户口令）
     * @param criteria 查询
     * @return 操作结果
     */
    public IOperationResult<IReport> fetchReport(ICriteria criteria) {
        return new OperationResult<IReport>(this.fetchReport(criteria, this.getUserToken()));
    }

    /**
     * 保存-报表
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    public OperationResult<Report> saveReport(Report bo, String token) {
        return super.save(bo, token);
    }

    /**
     * 保存-报表（提前设置用户口令）
     * @param bo 对象实例
     * @return 操作结果
     */
    public IOperationResult<IReport> saveReport(IReport bo) {
        return new OperationResult<IReport>(this.saveReport((Report) bo, this.getUserToken()));
    }

    //--------------------------------------------------------------------------------------------//

}
