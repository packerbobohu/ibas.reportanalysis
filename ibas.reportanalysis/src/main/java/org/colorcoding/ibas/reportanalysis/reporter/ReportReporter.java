package org.colorcoding.ibas.reportanalysis.reporter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.SqlQuery;
import org.colorcoding.ibas.bobas.data.IDataTable;
import org.colorcoding.ibas.bobas.i18n.i18n;
import org.colorcoding.ibas.bobas.repository.BORepository4DbReadonly;
import org.colorcoding.ibas.bobas.repository.IBORepository4DbReadonly;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.report.IReportParameter;

/**
 * 系统报表者
 * 
 * @author Niuren.Zhu
 *
 */
public class ReportReporter extends Reporter {

	@Override
	public IDataTable run(IReport report) throws Exception {
		if (report == null || report.getSqlString() == null || report.getSqlString().isEmpty()) {
			throw new Exception(i18n.prop("msg_ra_invaild_report_query",
					report.getName() != null ? report.getName() : report.getObjectKey()));
		}
		IBORepository4DbReadonly boRepository = new BORepository4DbReadonly("Master");
		String sqlString = report.getSqlString();
		// 替换变量
		String pattern = "\\$\\{([\\!a-zA-Z].*?)\\}";
		Matcher matcher = Pattern.compile(pattern).matcher(sqlString);
		while (matcher.find()) {
			String vName = matcher.group(0);
			IReportParameter parameter = report.getReportParameters().firstOrDefault(c -> c.getName().equals(vName));
			if (parameter != null) {
				String vValue = parameter.getValue();
				if (vValue != null) {
					sqlString = sqlString.replace(vName, vValue);
				}
			}
		}
		// 检查是否安全
		if (!this.check(sqlString)) {
			throw new Exception(i18n.prop("msg_ra_invaild_report_query", report.getName()));
		}
		// 执行语句
		IOperationResult<IDataTable> opRslt = boRepository.query(new SqlQuery(sqlString));
		if (opRslt.getError() != null) {
			throw opRslt.getError();
		}
		if (opRslt.getResultCode() != 0) {
			throw new Exception(opRslt.getMessage());
		}
		return opRslt.getResultObjects().firstOrDefault();
	}

	protected boolean check(String sql) {
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
				+ "(\\b(update|delete|insert|trancate|into|ascii|master|drop)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(sql).find()) {
			return false;
		}
		return true;
	}
}
