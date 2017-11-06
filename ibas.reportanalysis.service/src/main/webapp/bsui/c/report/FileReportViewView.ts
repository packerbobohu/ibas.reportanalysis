/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import { utils } from "openui5/typings/ibas.utils";
import * as bo from "../../../borep/bo/index";
import { BORepositoryReportAnalysis } from "../../../borep/BORepositories";
import { IReportViewView } from "../../../bsapp/report/index";
import { ReportViewView, ReportViewTabView } from "./ReportViewView";

/** 配置项目-全屏模式 */
const CONFIG_ITEM_FULL_SCREEN: string = "fullScreen";
/** 获取窗口宽度 */
let getWindowWidth: Function = function (tab: boolean): number {
    let consume: number = 96;
    return window.innerWidth - consume;
};
/** 获取窗口高度 */
let getWindowHeight: Function = function (tab: boolean): number {
    let consume: number = 86;
    if (ibas.config.get(CONFIG_ITEM_FULL_SCREEN, false)) {
        consume = consume - 50;
    }
    if (tab) {
        consume = consume + 50;
    }
    return window.innerHeight - consume;
};
let createHTML: Function = function (url: string): string {
    if (ibas.objects.isNull(url)) {
        return "";
    }
    if (url.endsWith(".swf") || url.indexOf(".swf?") > 0) {
        return ibas.strings.format(
            `<html><iframe src="{0}" width="{1}" height="{2}" class='preview-iframe' frameborder="no" scrolling="no"></iframe></html>`,
            url, getWindowWidth(true), getWindowHeight(true));
    }
    return ibas.strings.format(
        `<iframe src="{0}" width="{1}" height="{2}" frameborder="no" border="0" scrolling="no"></iframe>`,
        url, getWindowWidth(true), getWindowHeight(true));
};
let checkReportUrl: Function = function (url: string): string {
    // 正常地址
    url = ibas.urls.normalize(url).toLowerCase();
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = ibas.strings.format("{0}resources/report_files/{1}",
            require.toUrl(""),
            url
        );
    }
    return url;
};
/**
 * 视图-Report
 */
export class FileReportViewView extends ReportViewView {
    /** 运行报表 */
    runReportEvent: Function;
    /** 绘制视图 */
    darw(): any {
        let that: this = this;
        this.form = new sap.ui.layout.form.SimpleForm("", {
            content: [
            ]
        });
        this.page = new sap.m.Page("", {
            showHeader: false,
            subHeader: new sap.m.Bar("", {
                contentLeft: [
                    new sap.m.Button("", {
                        text: ibas.i18n.prop("sys_shell_run"),
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://begin",
                        press: function (): void {
                            that.fireViewEvents(that.runReportEvent);
                        }
                    }),
                ],
            }),
            content: [this.form]
        });
        this.id = this.page.getId();
        return this.page;
    }
    private tableResult: sap.ui.table.Table;
    /** 显示报表结果 */
    showResults(table: ibas.DataTable): void {
        if (!ibas.objects.isNull(this.tableResult)) {
            this.tableResult.destroy(true);
        }
        this.form.destroyContent();
        this.page.setShowSubHeader(false);
        let datas: any[] = table.convert();
        if (datas.length === 1) {
            let data: any = datas[0];
            if (data.Key === "${Url}") {
                let url: string = checkReportUrl(data.Value);
                this.application.viewShower.proceeding(this,
                    ibas.emMessageType.INFORMATION,
                    ibas.i18n.prop("reportanalysis_running_report", url),
                );
                let html: string = createHTML(url);
                ibas.logger.log(ibas.emMessageLevel.DEBUG, "view: {0}", html);
                this.form.addContent(
                    new sap.ui.core.HTML("", {
                        content: html,
                        preferDOM: false,
                        sanitizeContent: true,
                        visible: true,
                    })
                );
                /*
                window.open(data.Value, this.application.description,
                    "toolbar=no, menubar=no, location=no, status=no, titlebar=no, fullscreen=yes");
                */
            }
        }
    }
}
/**
 * 视图-报表查看-页签，需要与上保持同步
 */
export class FileReportViewTabView extends ReportViewTabView {
    /** 运行报表 */
    runReportEvent: Function;
    /** 绘制视图 */
    darw(): any {
        let that: this = this;
        this.form = new sap.ui.layout.form.SimpleForm("", {
            content: [
            ]
        });
        this.page = new sap.m.Page("", {
            showHeader: false,
            subHeader: new sap.m.Bar("", {
                contentLeft: [
                    new sap.m.Button("", {
                        text: ibas.i18n.prop("sys_shell_run"),
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://begin",
                        press: function (): void {
                            that.fireViewEvents(that.runReportEvent);
                        }
                    }),
                ],
            }),
            content: [this.form]
        });
        this.id = this.page.getId();
        return this.page;
    }
    private tableResult: sap.ui.table.Table;
    /** 显示报表结果 */
    showResults(table: ibas.DataTable): void {
        if (!ibas.objects.isNull(this.tableResult)) {
            this.tableResult.destroy(true);
        }
        this.form.destroyContent();
        this.page.setShowSubHeader(false);
        let datas: any[] = table.convert();
        if (datas.length === 1) {
            let data: any = datas[0];
            if (data.Key === "${Url}") {
                let url: string = checkReportUrl(data.Value);
                url = ibas.urls.normalize(url);
                this.application.viewShower.proceeding(this,
                    ibas.emMessageType.INFORMATION,
                    ibas.i18n.prop("reportanalysis_running_report", url),
                );
                let html: string = createHTML(url);
                ibas.logger.log(ibas.emMessageLevel.DEBUG, "view: {0}", html);
                this.form.addContent(
                    new sap.ui.core.HTML("", {
                        content: html,
                        preferDOM: false,
                        sanitizeContent: true,
                        visible: true,
                    })
                );
                /*
                window.open(data.Value, this.application.description,
                    "toolbar=no, menubar=no, location=no, status=no, titlebar=no, fullscreen=yes");
                */
            }
        }
    }
}
