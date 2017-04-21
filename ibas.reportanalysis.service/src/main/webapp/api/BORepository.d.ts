/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import {
    FetchCaller,
    SaveCaller
} from "ibas/index";
import * as bo from "./bo/index"

/** ReportAnalysis 业务仓库 */
export interface IBORepositoryReportAnalysis {

    /**
     * 查询 报表
     * @param fetcher 查询者
     */
    fetchReport(fetcher: FetchCaller<bo.IReport>);
    /**
     * 保存 报表
     * @param saver 保存者
     */
    saveReport(saver: SaveCaller<bo.IReport>);


}
