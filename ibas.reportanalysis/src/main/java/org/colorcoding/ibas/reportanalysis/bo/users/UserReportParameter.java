package org.colorcoding.ibas.reportanalysis.bo.users;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.util.ArrayList;
import org.colorcoding.ibas.reportanalysis.bo.report.IReport;
import org.colorcoding.ibas.reportanalysis.bo.report.IReportParameter;
import org.colorcoding.ibas.reportanalysis.bo.report.Report;
import org.colorcoding.ibas.reportanalysis.data.emReportParameterType;
import org.colorcoding.ibas.reportanalysis.data.emReportType;

/**
 * 用户报表参数
 * 
 * @author Niuren.Zhu
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "UserReportParameter")
@XmlRootElement(name = "UserReportParameter")
public class UserReportParameter {

	public static UserReportParameter create(IReportParameter boItem) {
		UserReportParameter userReportParameter = new UserReportParameter();
		userReportParameter.setName(boItem.getName());
		userReportParameter.setCategory(boItem.getCategory());
		userReportParameter.setDescription(boItem.getDescription());
		userReportParameter.setValue(boItem.getValue());
		return userReportParameter;
	}

	private static final String PARAMETER_NAME_TEMPLATE = "${%s}";

	public static List<UserReportParameter> create(IReport boItem) {
		ArrayList<UserReportParameter> parameters = new ArrayList<>();
		if (boItem.getCategory() == emReportType.CRYSTAL) {
			// 水晶报表参数以参数形式返回
			UserReportParameter parameter = null;
			if (boItem.getServer() != null && boItem.getServer().length() > 0) {
				parameter = new UserReportParameter();
				parameter.setName(String.format(PARAMETER_NAME_TEMPLATE, Report.PROPERTY_SERVER.getName()));
				parameter.setCategory(emReportParameterType.PRESET);
				parameter.setValue(boItem.getServer());
				parameters.add(parameter);
			}
			if (boItem.getUserName() != null && boItem.getUserName().length() > 0) {
				parameter = new UserReportParameter();
				parameter.setName(String.format(PARAMETER_NAME_TEMPLATE, Report.PROPERTY_USERNAME.getName()));
				parameter.setCategory(emReportParameterType.PRESET);
				parameter.setValue(boItem.getUserName());
				parameters.add(parameter);
			}
			if (boItem.getPassword() != null && boItem.getPassword().length() > 0) {
				parameter = new UserReportParameter();
				parameter.setName(String.format(PARAMETER_NAME_TEMPLATE, Report.PROPERTY_PASSWORD.getName()));
				parameter.setCategory(emReportParameterType.PRESET);
				parameter.setValue(boItem.getPassword());
				parameters.add(parameter);
			}
			if (boItem.getAddress() != null && boItem.getAddress().length() > 0) {
				parameter = new UserReportParameter();
				parameter.setName(String.format(PARAMETER_NAME_TEMPLATE, Report.PROPERTY_ADDRESS.getName()));
				parameter.setCategory(emReportParameterType.PRESET);
				parameter.setValue(boItem.getAddress());
				parameters.add(parameter);
			}
		}
		return parameters;
	}

	/**
	 * 参数名称
	 */
	@XmlElement(name = "Name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 参数类型
	 */
	@XmlElement(name = "Category")
	private emReportParameterType category;

	public emReportParameterType getCategory() {
		return category;
	}

	public void setCategory(emReportParameterType category) {
		this.category = category;
	}

	/**
	 * 参数说明
	 */
	@XmlElement(name = "Description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 参数值
	 */
	@XmlElement(name = "Value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
