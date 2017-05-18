/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "./bo/index";
import { IBORepositoryBusinessObjectsEnterprise } from "../api/index";
import { DataConverter4boe } from "./DataConverters";

/** BusinessObjectsEnterprise 业务仓库 */
export class BORepositoryBusinessObjectsEnterprise extends ibas.BORepositoryApplication implements IBORepositoryBusinessObjectsEnterprise {

    /** 创建此模块的后端与前端数据的转换者 */
    protected createConverter(): ibas.IDataConverter {
        return new DataConverter4boe;
    }

    /**
     * 查询 BOE文件夹
     * @param fetcher 查询者
     */
    fetchFolder(fetcher: ibas.FetchCaller<bo.BOEFolder>): void {
        super.fetch(bo.BOEFolder.name, fetcher);
    }

    /**
     * 查询 BOE报表
     * @param fetcher 查询者
     */
    fetchReport(fetcher: ibas.FetchCaller<bo.BOEReport>): void {
        super.fetch(bo.BOEReport.name, fetcher);
    }
}
