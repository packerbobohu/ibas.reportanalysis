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

/** 查看应用-报表 */
export class ReportViewApp extends ibas.BOApplicationWithServices<IReportViewView> {
    /** 应用标识 */
    static APPLICATION_ID: string = "3c42c391-4dc3-4188-a9d7-b6cc757428ae";
    /** 应用名称 */
    static APPLICATION_NAME: string = "reportanalysis_app_report_view";
    /** 构造函数 */
    constructor() {
        super();
        this.id = ReportViewApp.APPLICATION_ID;
        this.name = ReportViewApp.APPLICATION_NAME;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 注册视图 */
    protected registerView(): void {
        super.registerView();
        // 其他事件
        this.view.runReportEvent = this.runReport;
        this.view.resetReportEvent = this.viewShowed;
    }
    /** 视图显示后 */
    protected viewShowed(): void {
        // 视图加载完成
        this.view.showReport(this.report);
    }
    /** 运行,覆盖原方法 */
    run(...args: any[]): void {
        try {
            if (arguments.length === 1) {
                let par: any = arguments[0];
                let that = this;
                let run: Function = function (report: bo.UserReport): void {
                    try {
                        if (ibas.objects.isNull(report)) {
                            throw new Error(ibas.i18n.prop("sys_invalid_parameter", "report"));
                        }
                        that.report = report;
                        that.description = ibas.strings.format("{0} - {1}", that.description, that.report.name);
                        that.show();
                    } catch (error) {
                        that.messages(error);
                    }
                }
                if (par instanceof bo.UserReport) {
                    run(par);
                    return;
                } else if (par instanceof bo.Report) {
                    run(bo.UserReport.create(par));
                    return;
                } else if (typeof par === "string" || typeof par === "number") {
                    let criteria: ibas.ICriteria = new ibas.Criteria();
                    let condition: ibas.ICondition = criteria.conditions.create();
                    condition.alias = bo.Report.PROPERTY_OBJECTKEY_NAME;
                    condition.value = <any>par;
                    condition = criteria.conditions.create();
                    condition.alias = bo.Report.PROPERTY_ACTIVATED_NAME;
                    condition.value = <any>ibas.emYesNo.YES;
                    let boRepository: BORepositoryReportAnalysis = new BORepositoryReportAnalysis();
                    boRepository.fetchReport({
                        criteria: criteria,
                        onCompleted(opRslt: ibas.IOperationResult<bo.Report>): void {
                            run(bo.UserReport.create(opRslt.resultObjects.firstOrDefault()));
                        }
                    });
                    return;
                }
            }
            throw new Error(ibas.i18n.prop("reportanalysis_run_report_error"));
        } catch (error) {
            this.messages(error);
        }
    }
    private report: bo.UserReport;
    runReport(): void {
        let that = this;
        let boRepository: BORepositoryReportAnalysis = new BORepositoryReportAnalysis();
        boRepository.runUserReport({
            report: this.report,
            onCompleted(opRslt: ibas.IOperationResult<ibas.DataTable>): void {
                try {
                    if (opRslt.resultCode !== 0) {
                        throw new Error(opRslt.message);
                    }
                    let table: ibas.DataTable = opRslt.resultObjects.firstOrDefault();
                    if (ibas.objects.isNull(table)) {
                        throw new Error(ibas.i18n.prop("reportanalysis_report_no_data"));
                    }
                    that.view.showResults(table);
                    that.proceeding(ibas.emMessageType.SUCCESS, ibas.i18n.prop("sys_shell_sucessful"));
                } catch (error) {
                    that.messages(error);
                }
            }
        });
        this.proceeding(ibas.emMessageType.INFORMATION, ibas.i18n.prop("reportanalysis_running_report", this.report.name));
    }
    /** 获取服务的契约 */
    protected getServiceProxies(): ibas.IServiceProxy<ibas.IServiceContract>[] {
        return [];
    }
}
/** 视图-报表 */
export interface IReportViewView extends ibas.IBOViewWithServices {
    /** 调用服务事件 */
    callServicesEvent: Function;
    /** 运行报表 */
    runReportEvent: Function;
    /** 重置报表 */
    resetReportEvent: Function;
    /** 显示报表 */
    showReport(report: bo.UserReport): void;
    /** 显示报表结果 */
    showResults(table: ibas.DataTable): void;
}
