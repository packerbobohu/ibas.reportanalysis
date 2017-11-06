package org.colorcoding.ibas.reportanalysis;

/**
 * 我的配置项
 */
public class MyConfiguration extends org.colorcoding.ibas.bobas.MyConfiguration {

	/**
	 * 模块标识
	 */
	public static final String MODULE_ID = "0dda2ecb-af63-4a3d-b087-aa3dda8179b4";

	/**
	 * 命名空间
	 */
	public static final String NAMESPACE_ROOT = "http://colorcoding.org/ibas/reportanalysis/";

	/**
	 * 数据命名空间
	 */
	public static final String NAMESPACE_DATA = NAMESPACE_ROOT + "data";

	/**
	 * 业务对象命名空间
	 */
	public static final String NAMESPACE_BO = NAMESPACE_ROOT + "bo";

	/**
	 * 服务命名空间
	 */
	public static final String NAMESPACE_SERVICE = NAMESPACE_ROOT + "service";
	/**
	 * 配置项目-报表工厂，多个时“;”分隔
	 */
	public final static String CONFIG_ITEM_REPORTER_FACTORIES = "ReporterFactories";
	/**
	 * 配置项目-报表文件文件夹
	 */
	public final static String CONFIG_ITEM_REPORT_FILE_FOLDER = "ReportFileFolder";
}
