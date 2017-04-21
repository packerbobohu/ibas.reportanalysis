/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

// 共享的数据
import {
} from "ibas/index";

/**
 * 报表类型
 */
export enum emReportType {
    /** 系统报表 */
    REPORTS,
    /** 水晶报表服务 */
    CRYSTAL_SERVICES
}
/**
 * 报表参数类型
 */
export enum emReportParameterType {
    /** 自由文本 */
    FREE_TEXT,
    /** 日期 */
    DATETIME,
    /** 系统变量 */
    SYSTEM_VARIABLES,
    /** 范围值 */
    RANGE_VALUES,
    /** 查询结果 */
    SQL_RESULT
}