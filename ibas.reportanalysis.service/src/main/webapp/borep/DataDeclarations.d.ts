/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

/** ibas的java端数据声明 */

/** 操作消息 */
export interface DataDeclaration {
    /** 数据类型 */
    type: string;
}
/** 用户 */
export interface UserReport extends DataDeclaration {
    /** 标识 */
    Id: string;
    /** 名称 */
    Name: string;
    /** 类型 */
    Category: string;
    /** 报表参数 */
    Parameters: UserReportParameter[];
}
/** 用户应用模块 */
export interface UserReportParameter extends DataDeclaration {
    /** 参数名称 */
    Name: string;
    /** 参数类型 */
    Category: string;
    /** 参数说明 */
    Description: string;
    /** 参数值 */
    Value: string;
}