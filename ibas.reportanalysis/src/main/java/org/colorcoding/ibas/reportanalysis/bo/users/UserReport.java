package org.colorcoding.ibas.reportanalysis.bo.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.util.ArrayList;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.report.IReportParameter;
import org.colorcoding.ibas.reportanalysis.data.emReportType;

/**
 * 用户报表
 * 
 * @author Niuren.Zhu
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "UserReport")
@XmlRootElement(name = "UserReport")
public class UserReport {
	public static UserReport create(IReport boItem) {
		UserReport userReport = new UserReport();
		userReport.setId(String.valueOf(boItem.getObjectKey()));
		userReport.setName(boItem.getName());
		userReport.setCategory(boItem.getCategory());
		ArrayList<UserReportParameter> parameters = new ArrayList<>();
		// 报表中的参数
		parameters.addAll(UserReportParameter.create(boItem));
		// 参数
		for (IReportParameter item : boItem.getReportParameters()) {
			parameters.add(UserReportParameter.create(item));
		}
		userReport.setParameters(parameters.toArray(new UserReportParameter[] {}));
		return userReport;
	}

	@XmlElement(name = "Id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "Name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Category")
	private emReportType category;

	public emReportType getCategory() {
		return category;
	}

	public void setCategory(emReportType category) {
		this.category = category;
	}

	@XmlElement(name = "Parameters")
	private UserReportParameter[] parameters;

	public UserReportParameter[] getParameters() {
		return parameters;
	}

	public void setParameters(UserReportParameter[] parameters) {
		this.parameters = parameters;
	}

}
