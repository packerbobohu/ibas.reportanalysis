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

/** BusinessObjectsEnterprise 业务仓库 */
export interface IBORepositoryBusinessObjectsEnterprise {

    /**
     * 查询 BOE文件夹
     * @param fetcher 查询者
     */
    fetchFolder(fetcher: FetchCaller<bo.IBOEFolder>);

    /**
     * 查询 BOE报表
     * @param fetcher 查询者
     */
    fetchReport(fetcher: FetchCaller<bo.IBOEReport>);


}
