package org.colorcoding.ibas.boe;

/**
 * 我的配置项
 */
public class MyConfiguration extends org.colorcoding.ibas.bobas.MyConfiguration {

	/**
	 * 模块标识
	 */
	public static final String MODULE_ID = "c4d3da42-0a53-480b-98fe-bf551a9581c7";

	/**
	 * 命名空间
	 */
	public static final String NAMESPACE_ROOT = "http://colorcoding.org/ibas/businessobjectsenterprise/";

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
	 * 配置项目-BOE地址
	 */
	public static final String CONFIG_ITEM_BOE_SERVER_ADDRESS = "BOEAddress";
	/**
	 * 配置项目-报表查看地址
	 */
	public static final String CONFIG_ITEM_BOE_VIEWER_ADDRESS = "BOEViewer";
}
