/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
import * as ibas from "ibas/index";
import {
    emReportType,
    emReportParameterType,
} from "../../api/index";

/** 用户报表 */
export class UserReport {
    constructor() {
        this.parameters = new ibas.ArrayList<UserReportParameter>();
    }
    /** 标识 */
    id: string;
    /** 名称 */
    name: string;
    /** 类型 */
    category: emReportType;
    /** 报表参数 */
    parameters: ibas.ArrayList<UserReportParameter>;
}
/** 用户报表参数 */
export class UserReportParameter {
    /** 参数名称 */
    name: string;
    /** 参数类型 */
    category: emReportParameterType;
    /** 参数说明 */
    description: string;
    /** 参数值 */
    value: string;
}