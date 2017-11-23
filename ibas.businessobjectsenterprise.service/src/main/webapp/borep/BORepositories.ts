/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "./bo/index";
import { IBORepositoryBusinessObjectsEnterprise, ConnectCaller } from "../api/index";
import { DataConverter4boe } from "./DataConverters";

/** BusinessObjectsEnterprise 业务仓库 */
export class BORepositoryBusinessObjectsEnterprise extends ibas.BORepositoryApplication implements IBORepositoryBusinessObjectsEnterprise {

    /** 创建此模块的后端与前端数据的转换者 */
    protected createConverter(): ibas.IDataConverter {
        return new DataConverter4boe;
    }

	/**
	 * 连接BOE，连接成功记录token为成功口令
	 * @param listener 登录调用者
	 */
    connect(caller: ConnectCaller): void {
        let remoteRepository: ibas.BORepositoryAjax = new ibas.BORepositoryAjax();
        remoteRepository.address = this.address;
        remoteRepository.converter = this.createConverter();
        if (ibas.objects.isNull(remoteRepository)) {
            throw new Error(ibas.i18n.prop("sys_invalid_parameter", "remoteRepository"));
        }
        let that: this = this;
        require(["../3rdparty/crypto-js"], function (cryptoJS: CryptoJS.Hashes): void {
            // todo：应对密码加密
            let method: string = ibas.strings.format("connect?user={0}&password={1}", caller.user, caller.password);
            let methodCaller: ibas.MethodCaller = {
                onCompleted(opRslt: ibas.IOperationMessage): void {
                    if (opRslt.resultCode === 0) {
                        // 连接成功，记录口令
                        that.token = opRslt.userSign;
                    }
                    caller.onCompleted(opRslt);
                }
            };
            remoteRepository.callRemoteMethod(method, undefined, methodCaller);
        }, function (error: RequireError): void {
            // 加载js库失败
            let opRslt: ibas.IOperationMessage = new ibas.OperationMessage();
            opRslt.resultCode = -901;
            opRslt.message = error.message;
            caller.onCompleted(opRslt);
        });
    }
    /**
     * 上传文件
     * @param caller 调用者
     */
    upload(caller: ibas.UploadFileCaller<ibas.FileData>): void {
        if (!this.address.endsWith("/")) { this.address += "/"; }
        let fileRepository: ibas.FileRepositoryUploadAjax = new ibas.FileRepositoryUploadAjax();
        fileRepository.address = this.address.replace("/services/rest/data/", "/services/rest/file/");
        fileRepository.token = this.token;
        fileRepository.converter = this.createConverter();
        fileRepository.upload("upload", caller);
    }
    /**
     * 下载文件
     * @param caller 调用者
     */
    download(caller: ibas.DownloadFileCaller<Blob>): void {
        if (!this.address.endsWith("/")) { this.address += "/"; }
        let fileRepository: ibas.FileRepositoryDownloadAjax = new ibas.FileRepositoryDownloadAjax();
        fileRepository.address = this.address.replace("/services/rest/data/", "/services/rest/file/");
        fileRepository.token = this.token;
        fileRepository.converter = this.createConverter();
        fileRepository.download("download", caller);
    }
    /**
     * 查询 BOE文件夹
     * @param fetcher 查询者
     */
    fetchFolder(fetcher: ibas.FetchCaller<bo.BOEFolder>): void {
        super.fetch(bo.BOEFolder.name.replace("BOE", ""), fetcher);// 重新指定名称
    }

    /**
     * 查询 BOE报表
     * @param fetcher 查询者
     */
    fetchReport(fetcher: ibas.FetchCaller<bo.BOEReport>): void {
        super.fetch(bo.BOEReport.name.replace("BOE", ""), fetcher);// 重新指定名称
    }
}