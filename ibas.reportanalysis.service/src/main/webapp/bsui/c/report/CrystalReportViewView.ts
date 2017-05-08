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
import { ICrystalReportViewView } from "../../../bsapp/report/index";
import { views as viewUtils } from "./Utils";

/**
 * 视图-Report
 */
export class CrystalReportViewView extends ibas.View implements ICrystalReportViewView {
    /** 运行报表 */
    runReportEvent: Function;
    /** 绘制视图 */
    darw(): any {
        let that = this;
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
    private page: sap.m.Page;
    private form: sap.ui.layout.form.SimpleForm;
    private tableResult: sap.ui.table.Table;

    /** 显示报表 */
    showReport(report: bo.UserReport): void {
        this.form.destroyContent();
        viewUtils.addReportParameterUIs(this.form, report.parameters);
    }
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
                this.application.viewShower.proceeding(this,
                    ibas.emMessageType.INFORMATION,
                    ibas.i18n.prop("reportanalysis_running_report", data.Value),
                );
                let width: number = window.innerWidth - 96;
                let height: number = window.innerHeight - 136;
                console.log(window.pageXOffset, window.pageYOffset);
                let html: string = ibas.strings.format(
                    `<iframe src="{0}" width="{1}" height="{2}" scrolling="no"></iframe>`, data.Value, width, height);
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
