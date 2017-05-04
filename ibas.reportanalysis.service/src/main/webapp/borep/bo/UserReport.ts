/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
import * as ibas from "ibas/index";
import {
    IReport,
    IReportParameter,
    emReportType,
    emReportParameterType,
} from "../../api/index";

/** 用户报表 */
export class UserReport {
    static create(report: IReport): UserReport {
        let uReport: UserReport = new UserReport();
        uReport.id = <any>report.objectKey;
        uReport.name = report.name;
        uReport.category = report.category;
        for (let item of report.reportParameters) {
            uReport.parameters.add(UserReportParameter.create(item));
        }
        return uReport;
    }

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
    static create(parameter: IReportParameter): UserReportParameter {
        let uParameter: UserReportParameter = new UserReportParameter();
        uParameter.name = parameter.name;
        uParameter.category = parameter.category;
        uParameter.description = parameter.description;
        uParameter.value = parameter.value;
        return uParameter;
    }
    /** 参数名称 */
    name: string;
    /** 参数类型 */
    category: emReportParameterType;
    /** 参数说明 */
    description: string;
    /** 参数值 */
    value: string;
}