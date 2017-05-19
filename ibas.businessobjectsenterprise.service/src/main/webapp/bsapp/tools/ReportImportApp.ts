/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "../../borep/bo/index";
import { BORepositoryBusinessObjectsEnterprise } from "../../borep/BORepositories";

/** 列表应用-BOE报表 */
export class ReportImportApp extends ibas.Application<IReportImportView> {

    /** 应用标识 */
    static APPLICATION_ID: string = "45a0761f-78af-47bd-871f-03f981353466";
    /** 应用名称 */
    static APPLICATION_NAME: string = "businessobjectsenterprise_app_report_import";
    /** 构造函数 */
    constructor() {
        super();
        this.id = ReportImportApp.APPLICATION_ID;
        this.name = ReportImportApp.APPLICATION_NAME;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 注册视图 */
    protected registerView(): void {
        super.registerView();
        // 其他事件
        this.view.connectEvent = this.connect;
        this.view.fetchFolderEvent = this.fetchFolder;
        this.view.fetchReportEvent = this.fetchReport;
        this.view.importReportEvent = this.importReport;
    }
    /** 视图显示后 */
    protected viewShowed(): void {
        // 视图加载完成
    }
    /** 连接boe */
    connect(): void { 

    }
    /** 获取目录 */
    fetchFolder(): void { 

    }
    /** 获取报表 */
    fetchReport(): void { 

    }
    /** 导入报表 */
    importReport(): void { 

    }
}
/** 视图-BOE报表 */
export interface IReportImportView extends ibas.IView {
    /** BOE服务地址 */
    readonly server: string;
    /** BOE用户 */
    readonly user: string;
    /** BOE密码 */
    readonly password: string;
    /** 连接BOE */
    connectEvent: Function;
    /** 获取目录 */
    fetchFolderEvent: Function;
    /** 显示目录 */
    showFolders(datas: bo.BOEFolder[]): void;
    /** 获取报表 */
    fetchReportEvent: Function;
    /** 显示报表 */
    showReports(datas: bo.BOEReport[]): void;
    /** 导入报表 */
    importReportEvent: Function;
}
