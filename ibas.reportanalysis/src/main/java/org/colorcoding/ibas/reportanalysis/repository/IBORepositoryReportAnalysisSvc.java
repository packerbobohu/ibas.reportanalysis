package org.colorcoding.ibas.reportanalysis.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.reportanalysis.bo.report.*;

/**
* ReportAnalysis仓库服务
*/
public interface IBORepositoryReportAnalysisSvc extends IBORepositorySmartService {


    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-报表
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Report> fetchReport(ICriteria criteria, String token);

    /**
     * 保存-报表
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Report> saveReport(Report bo, String token);

    //--------------------------------------------------------------------------------------------//

}
