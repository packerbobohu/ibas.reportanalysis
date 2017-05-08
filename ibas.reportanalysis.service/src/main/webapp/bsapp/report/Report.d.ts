/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "../../borep/bo/index";

/** 报表查看者 */
export interface IReportViewer extends ibas.IApplication<ibas.IView> {
    /** 报表 */
    report: bo.UserReport;
    /** 运行报表 */
    runReport(): void;
}