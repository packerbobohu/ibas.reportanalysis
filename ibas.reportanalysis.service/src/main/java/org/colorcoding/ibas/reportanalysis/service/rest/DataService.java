package org.colorcoding.ibas.reportanalysis.service.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.reportanalysis.repository.*;
import org.colorcoding.ibas.reportanalysis.bo.report.*;

/**
* ReportAnalysis 数据服务JSON
*/
@Path("data")
public class DataService extends BORepositoryReportAnalysis {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-报表
     * @param criteria 查询
     * @param token 口令
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
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveReport")
    public OperationResult<Report> saveReport(Report bo, @QueryParam("token") String token) {
        return super.saveReport(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
