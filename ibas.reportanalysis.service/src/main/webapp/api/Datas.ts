/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * 报表类型
 */
export enum emReportType {
    /** 系统报表 */
    REPORT,
    /** 报表服务 */
    BOE,
    /** 绩效指标 */
    KPI
}
/**
 * 报表参数类型
 */
export enum emReportParameterType {
    /** 自由文本 */
    TEXT,
    /** 日期 */
    DATETIME,
    /** 系统变量 */
    SYSTEM,
    /** 范围值 */
    RANGE,
    /** 查询结果 */
    SQL,
    /** 预置值 */
    PRESET
}
/**
 * 分配类型
 */
export enum emAssignedType {
    /** 用户 */
    USER,
    /** 角色 */
    ROLE,
}