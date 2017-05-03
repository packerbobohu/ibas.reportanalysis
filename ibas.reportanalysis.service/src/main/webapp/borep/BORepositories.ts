/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "./bo/index";
import { IBORepositoryReportAnalysis, UserMethodsCaller } from "../api/index";
import { DataConverter4ra } from "./DataConverters";

/** ReportAnalysis 业务仓库 */
export class BORepositoryReportAnalysis extends ibas.BORepositoryApplication implements IBORepositoryReportAnalysis {

    /** 创建此模块的后端与前端数据的转换者 */
    protected createConverter(): ibas.IDataConverter {
        return new DataConverter4ra;
    }
    /** 创建远程仓库 */
    protected createRemoteRepository(): ibas.IRemoteRepository {
        let boRepository: ibas.BORepositoryAjax = new ibas.BORepositoryAjax();
        boRepository.address = this.address;
        boRepository.token = this.token;
        boRepository.converter = this.createConverter();
        return boRepository;
    }
	/**
	 * 查询用户报表
	 * @param listener 用户检索监听者
	 */
    fetchUserReports(caller: UserMethodsCaller<bo.UserReport>): void {
        let remoteRepository: ibas.IRemoteRepository = this.createRemoteRepository();
        if (ibas.objects.isNull(remoteRepository)) {
            throw new Error(ibas.i18n.prop("msg_invalid_parameter", "remoteRepository"));
        }
        let method: string =
            ibas.strings.format("fetchUserReports?user={0}&token={1}",
                caller.user, this.token);
        remoteRepository.callRemoteMethod(method, undefined, caller);
    }
    /**
     * 查询 报表
     * @param fetcher 查询者
     */
    fetchReport(fetcher: ibas.FetchCaller<bo.Report>): void {
        super.fetch(bo.Report.name, fetcher);
    }
    /**
     * 保存 报表
     * @param saver 保存者
     */
    saveReport(saver: ibas.SaveCaller<bo.Report>): void {
        super.save(bo.Report.name, saver);
    }

}
