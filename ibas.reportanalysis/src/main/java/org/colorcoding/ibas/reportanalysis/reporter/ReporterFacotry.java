package org.colorcoding.ibas.reportanalysis.reporter;

import org.colorcoding.ibas.bobas.i18n.i18n;
import org.colorcoding.ibas.reportanalysis.bo.users.UserReport;
import org.colorcoding.ibas.reportanalysis.data.emReportType;

public class ReporterFacotry {

	private static ReporterFacotry instance;

	private ReporterFacotry() {

	}

	public static ReporterFacotry create() {
		if (instance == null) {
			synchronized (ReporterFacotry.class) {
				if (instance == null) {
					instance = new ReporterFacotry();
				}
			}
		}
		return instance;
	}

	public IReporter create(UserReport report) throws Exception {
		if (report.getCategory() == emReportType.REPORT) {
			return new ReportReporter();
		} else if (report.getCategory() == emReportType.CRYSTAL) {
			return new CrystalReporter();
		} else if (report.getCategory() == emReportType.KPI) {
			return new KpiReporter();
		}
		throw new Exception(i18n.prop("msg_ra_not_allowed_run_report",
				report.getName() != null ? report.getName() : report.getId()));
	}
}
