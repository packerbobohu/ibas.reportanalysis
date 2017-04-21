package org.colorcoding.ibas.reportanalysis.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.cxf.WebServicePath;
import org.colorcoding.ibas.reportanalysis.repository.*;
import org.colorcoding.ibas.reportanalysis.bo.report.*;

/**
* ReportAnalysis 数据服务JSON
*/
@WebService
@WebServicePath("data")
public class DataService extends BORepositoryReportAnalysis {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-报表
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Report> fetchReport(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchReport(criteria, token);
    }

    /**
     * 保存-报表
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Report> saveReport(@WebParam(name = "bo") Report bo, @WebParam(name = "token") String token) {
        return super.saveReport(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
