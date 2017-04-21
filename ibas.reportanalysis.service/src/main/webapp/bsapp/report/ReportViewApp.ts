/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "../../borep/bo/index";
import { BORepositoryReportAnalysis } from "../../borep/BORepositories";
import { ReportEditApp } from "./ReportEditApp";

/** 查看应用-报表 */
export class ReportViewApp extends ibas.BOViewService<IReportViewView> {

    /** 应用标识 */
    static APPLICATION_ID: string = "3c42c391-4dc3-4188-a9d7-b6cc757428ae";
    /** 应用名称 */
    static APPLICATION_NAME: string = "reportanalysis_app_report_view";
    /** 业务对象编码 */
    static BUSINESS_OBJECT_CODE: string = bo.Report.BUSINESS_OBJECT_CODE;
    /** 构造函数 */
    constructor() {
        super();
        this.id = ReportViewApp.APPLICATION_ID;
        this.name = ReportViewApp.APPLICATION_NAME;
        this.boCode = ReportViewApp.BUSINESS_OBJECT_CODE;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 注册视图 */
    protected registerView(): void {
        super.registerView();
        // 其他事件
        this.view.editDataEvent = this.editData;
    }
    /** 视图显示后 */
    protected viewShowed(): void {
        // 视图加载完成
    }
    /** 编辑数据，参数：目标数据 */
    protected editData(): void {
        let app = new ReportEditApp();
        app.navigation = this.navigation;
        app.viewShower = this.viewShower;
        app.run(this.viewData);
    }
    /** 运行,覆盖原方法 */
    run(...args: any[]): void {
        if (!ibas.objects.isNull(args) && args.length === 1 && args[0] instanceof bo.Report) {
            this.viewData = args[0];
            this.show();
        } else {
            super.run(args);
        }
    }
    private viewData: bo.Report;
    /** 查询数据 */
    protected fetchData(criteria: ibas.ICriteria | string): void {
        this.busy(true);
        let that = this;
        if (typeof criteria === "string") {
            criteria = new ibas.Criteria();
            // 添加查询条件

        }
        try {
            let boRepository: BORepositoryReportAnalysis = new BORepositoryReportAnalysis();
            boRepository.fetchReport({
                criteria: criteria,
                onCompleted(opRslt: ibas.IOperationResult<bo.Report>): void {
                    try {
                        if (opRslt.resultCode !== 0) {
                            throw new Error(opRslt.message);
                        }
                        that.viewData = opRslt.resultObjects.firstOrDefault();
                        that.viewShowed();
                    } catch (error) {
                        that.messages(error);
                    }
                }
            });
            this.proceeding(ibas.emMessageType.INFORMATION, ibas.i18n.prop("sys_shell_fetching_data"));
        } catch (error) {
            that.messages(error);
        }
    }
    /** 获取服务的契约 */
    protected getServiceProxies(): ibas.IServiceProxy<ibas.IServiceContract>[] {
        return [];
    }
}
/** 视图-报表 */
export interface IReportViewView extends ibas.IBOViewView {

}
/** 报表连接服务映射 */
export class ReportLinkServiceMapping extends ibas.BOLinkServiceMapping {
    /** 构造函数 */
    constructor() {
        super();
        this.id = ReportViewApp.APPLICATION_ID;
        this.name = ReportViewApp.APPLICATION_NAME;
        this.boCode = ReportViewApp.BUSINESS_OBJECT_CODE;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 创建服务并运行 */
    create(): ibas.IService<ibas.IServiceContract> {
        return new ReportViewApp();
    }
}
