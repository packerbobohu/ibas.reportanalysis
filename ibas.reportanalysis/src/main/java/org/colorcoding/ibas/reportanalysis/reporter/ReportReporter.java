package org.colorcoding.ibas.reportanalysis.reporter;

import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.ISqlQuery;
import org.colorcoding.ibas.bobas.common.SqlQuery;
import org.colorcoding.ibas.bobas.data.IDataTable;
import org.colorcoding.ibas.bobas.i18n.i18n;
import org.colorcoding.ibas.bobas.repository.BORepository4DbReadonly;
import org.colorcoding.ibas.bobas.repository.IBORepository4DbReadonly;
import org.colorcoding.ibas.reportanalysis.bo.report.Report;

/**
 * 系统报表者
 * 
 * @author Niuren.Zhu
 *
 */
public class ReportReporter extends Reporter {

	@Override
	public IDataTable run(Report report) throws Exception {
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

}
