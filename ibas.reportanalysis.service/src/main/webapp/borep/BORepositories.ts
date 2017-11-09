/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "./bo/index";
import { IBORepositoryReportAnalysis, UserMethodsCaller, BO_REPOSITORY_REPORTANALYSIS } from "../api/index";
import { DataConverter4ra } from "./DataConverters";

/** ReportAnalysis 业务仓库 */
export class BORepositoryReportAnalysis extends ibas.BORepositoryApplication implements IBORepositoryReportAnalysis {

    /** 创建此模块的后端与前端数据的转换者 */
    protected createConverter(): ibas.IDataConverter {
        return new DataConverter4ra;
    }
    /**
     * 上传报表文件
     * @param caller 调用者
     */
    uploadReport(caller: ibas.UploadFileCaller): void {
        if (!this.address.endsWith("/")) { this.address += "/"; }
        let fileRepository: ibas.FileRepositoryUploadAjax = new ibas.FileRepositoryUploadAjax();
        fileRepository.address = this.address.replace("/services/rest/data/", "/services/rest/file/");
        fileRepository.token = this.token;
        fileRepository.converter = this.createConverter();
        fileRepository.upload("uploadReport", caller);
    }
    /**
     * 读取报表文件
     * @param caller 调用者
     */
    loadReport(caller: ibas.DownloadFileCaller): void {
        if (!this.address.endsWith("/")) { this.address += "/"; }
        let fileRepository: ibas.FileRepositoryDownloadAjax = new ibas.FileRepositoryDownloadAjax();
        fileRepository.address = this.address.replace("/services/rest/data/", "/services/rest/file/");
        fileRepository.token = this.token;
        fileRepository.converter = this.createConverter();
        fileRepository.download("loadReport", caller);
    }
	/**
	 * 查询用户报表
	 * @param listener 用户检索监听者
	 */
    fetchUserReports(caller: UserMethodsCaller<bo.UserReport>): void {
        let boRepository: ibas.BORepositoryAjax = new ibas.BORepositoryAjax();
        boRepository.address = this.address;
        boRepository.token = this.token;
        boRepository.converter = this.createConverter();
        if (ibas.objects.isNull(boRepository)) {
            throw new Error(ibas.i18n.prop("sys_invalid_parameter", "remoteRepository"));
        }
        let method: string =
            ibas.strings.format("fetchUserReports?user={0}&token={1}",
                caller.user, this.token);
        boRepository.callRemoteMethod(method, undefined, caller);
    }
    /**
     * 运行用户报表
     * @param listener 用户检索监听者
     */
    runUserReport(caller: IRunUserReportCaller): void {
        let boRepository: ibas.BORepositoryAjax = new ibas.BORepositoryAjax();
        boRepository.address = this.address;
        boRepository.token = this.token;
        boRepository.converter = this.createConverter();
        if (ibas.objects.isNull(boRepository)) {
            throw new Error(ibas.i18n.prop("sys_invalid_parameter", "remoteRepository"));
        }
        let method: string =
            ibas.strings.format("runUserReport?token={0}", this.token);
        let data: string = JSON.stringify(this.createConverter().convert(caller.report, method));
        boRepository.callRemoteMethod(method, data, caller);
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
    /**
     * 查询 报表簿
     * @param fetcher 查询者
     */
    fetchReportBook(fetcher: ibas.FetchCaller<bo.ReportBook>): void {
        super.fetch(bo.ReportBook.name, fetcher);
    }
    /**
     * 保存 报表簿
     * @param saver 保存者
     */
    saveReportBook(saver: ibas.SaveCaller<bo.ReportBook>): void {
        super.save(bo.ReportBook.name, saver);
    }

}
// 注册业务对象仓库到工厂
ibas.boFactory.register(BO_REPOSITORY_REPORTANALYSIS, BORepositoryReportAnalysis);
/**
 * 用户相关调用者
 */
export interface IRunUserReportCaller extends ibas.MethodCaller {
    /** 用户 */
    report: bo.UserReport;
    /**
     * 调用完成
     * @param opRslt 结果
     */
    onCompleted(opRslt: ibas.IOperationResult<ibas.DataTable>): void;
}