package org.colorcoding.ibas.businessobjectsenterprise.reporter;

import org.colorcoding.ibas.bobas.i18n.i18n;
import org.colorcoding.ibas.businessobjectsenterprise.BOEException;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.report.IReportParameter;

public class BOEReporter {

	public static final String BOE_SERVER_URL = "${Url}";
	public static final String BOE_SERVER_USER = "${User}";
	public static final String BOE_SERVER_PASSWORD = "${Password}";
	public static final String BOE_SERVER_VIEW_DOCUMENT = "${Viewer}";

	private IReport report;

	public final IReport getReport() {
		return report;
	}

	private final void setReport(IReport report) {
		this.report = report;
	}

	protected String getParameterValue(String name) throws BOEException {
		if (this.getReport() != null) {
			for (IReportParameter item : this.getReport().getReportParameters()) {
				if (name.equalsIgnoreCase(item.getName())) {
					return item.getValue();
				}
			}
		}
		throw new BOEException(i18n.prop("msg_boe_not_found_report_parameter", name));
	}

	public String getBOEServer() throws BOEException {
		return this.getParameterValue(BOE_SERVER_URL);
	}

	public String getUser() throws BOEException {
		return this.getParameterValue(BOE_SERVER_USER);
	}

	public String getPassword() throws BOEException {
		return this.getParameterValue(BOE_SERVER_PASSWORD);
	}

	public String getViewDocument() throws BOEException {
		return this.getParameterValue(BOE_SERVER_VIEW_DOCUMENT);
	}
}
