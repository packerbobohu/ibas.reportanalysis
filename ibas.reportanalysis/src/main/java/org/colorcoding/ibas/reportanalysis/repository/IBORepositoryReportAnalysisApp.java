package org.colorcoding.ibas.reportanalysis.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.reportanalysis.bo.report.*;

/**
* ReportAnalysis仓库应用
*/
public interface IBORepositoryReportAnalysisApp extends IBORepositoryApplication {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-报表
     * @param criteria 查询
     * @return 操作结果
     */
    IOperationResult<IReport> fetchReport(ICriteria criteria);

    /**
     * 保存-报表
     * @param bo 对象实例
     * @return 操作结果
     */
    IOperationResult<IReport> saveReport(IReport bo);

    //--------------------------------------------------------------------------------------------//

}
