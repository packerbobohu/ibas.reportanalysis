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
import { IReportImportView } from "../../../bsapp/tools/index";

/**
 * 视图-BOEReport
 */
export class ReportImportView extends ibas.BOListView implements IReportImportView {
    /** 连接BOE */
    connectEvent: Function;
    /** 获取目录 */
    fetchFolderEvent: Function;
    /** 获取报表 */
    fetchReportEvent: Function;
    /** 导入报表 */
    importReportEvent: Function;
    /** 绘制视图 */
    darw(): any {
        
        let that: this = this;
        this.iptServer = new sap.m.Input("", {
value: ibas.b
        });
        this.iptUser = new sap.m.Input("", {

        });
        this.iptPassword = new sap.m.Input("", {
            type: sap.m.InputType.Password 
        });
        this.form = new sap.ui.layout.form.SimpleForm("", {
            content: [
                new sap.m.Toolbar("", {
                    design: sap.m.ToolbarDesign.Transparent,
                    content: [
                        new sap.m.Title("", {
                            text: ibas.i18n.prop("businessobjectsenterprise_import_boe") }),
                        new sap.m.ToolbarSpacer("", {}),
                        new sap.m.Button("", {
                            text: ibas.i18n.prop("businessobjectsenterprise_import_connect"),
                            icon:"sap-icon://connected",
                            type: sap.m.ButtonType.Transparent,
                            press(): void {
                                that.fireViewEvents(that.connectEvent);
                            }
                        }),
                    ]
                }),
                new sap.m.Label("", { text: ibas.i18n.prop("businessobjectsenterprise_import_server") }),
                this.iptServer,
                new sap.m.Label("", { text: ibas.i18n.prop("businessobjectsenterprise_import_user") }),
                this.iptUser,
                new sap.m.Label("", { text: ibas.i18n.prop("businessobjectsenterprise_import_password") }),
                this.iptPassword,
                new sap.m.Toolbar("", {
                    design: sap.m.ToolbarDesign.Transparent,
                    content: [
                        new sap.m.Title("", { text: ibas.i18n.prop("businessobjectsenterprise_import_reports") }),
                        new sap.m.ToolbarSpacer("", {}),
                        new sap.m.Button("", {
                            text: ibas.i18n.prop("businessobjectsenterprise_import_import"),
                            icon:"sap-icon://connected",
                            type: sap.m.ButtonType.Transparent,
                            press(): void {
                                that.fireViewEvents(that.connectEvent);
                            }
                        }),
                    ]
                }),
            ]
        });
        this.table = new sap.ui.table.Table("", {
            enableSelectAll: false,
            visibleRowCount: ibas.config.get(utils.CONFIG_ITEM_LIST_TABLE_VISIBLE_ROW_COUNT, 15),
            visibleRowCountMode: sap.ui.table.VisibleRowCountMode.Interactive,
            rows: "{/rows}",
            columns: [
            ]
        });
        this.form.addContent(this.table);
        this.id = this.form.getId();
        return this.form;
    }
    private form: sap.ui.layout.form.SimpleForm;
    private table: sap.ui.table.Table;
    private iptServer: sap.m.Input;
    private iptUser: sap.m.Input;
    private iptPassword: sap.m.Input;
    /** BOE服务地址 */
    get server(): string {
        return this.iptServer.getValue();
    }
    /** BOE用户 */
    get user(): string {
        return this.iptUser.getValue();
    }
    /** BOE密码 */
    get password(): string {
        return this.iptPassword.getValue();
    }
    /** 显示目录 */
    showFolders(datas: bo.BOEFolder[]): void {
//
    }
    /** 显示报表 */
    showReports(datas: bo.BOEReport[]): void {
//
    }
}
