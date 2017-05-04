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
import { IReportViewView } from "../../../bsapp/report/index";

/**
 * 视图-Report
 */
export class ReportViewView extends ibas.View implements IReportViewView {
    /** 调用服务事件 */
    callServicesEvent: Function;
    /** 运行报表 */
    runReportEvent: Function;
    /** 重置报表 */
    resetReportEvent: Function;
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
                    new sap.m.Button("", {
                        text: ibas.i18n.prop("sys_shell_reset"),
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://reset",
                        press: function (): void {
                            that.fireViewEvents(that.resetReportEvent);
                        }
                    })
                ],
                contentRight: [
                    new sap.m.Button("", {
                        type: sap.m.ButtonType.Transparent,
                        icon: "sap-icon://action",
                        press: function (event: any): void {
                            that.fireViewEvents(that.callServicesEvent, {
                                displayServices(services: ibas.IServiceAgent[]): void {
                                    if (ibas.objects.isNull(services) || services.length === 0) {
                                        return;
                                    }
                                    let popover: sap.m.Popover = new sap.m.Popover("", {
                                        showHeader: false,
                                        placement: sap.m.PlacementType.Bottom,
                                    });
                                    for (let service of services) {
                                        popover.addContent(new sap.m.Button({
                                            text: ibas.i18n.prop(service.name),
                                            type: sap.m.ButtonType.Transparent,
                                            icon: service.icon,
                                            press: function (): void {
                                                service.run();
                                                popover.close();
                                            }
                                        }));
                                    }
                                    (<any>popover).addStyleClass("sapMOTAPopover sapTntToolHeaderPopover");
                                    popover.openBy(event.getSource(), true);
                                }
                            });
                        }
                    })
                ]
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
        this.form.addContent(new sap.m.Title("", {
            text: ibas.i18n.prop("reportanalysis_running_parameters")
        }));
        for (let item of report.parameters) {
            if (item.category === bo.emReportParameterType.PRESET) {
                // 预设的不显示
                continue;
            }
            this.form.addContent(new sap.m.Label("", {
                textAlign: sap.ui.core.TextAlign.Left,
                width: "30%",
                text: ibas.objects.isNull(item.description) ? item.name.replace("\$\{", "").replace("\}", "") : item.description
            }));
            if (item.category === bo.emReportParameterType.DATETIME) {
            } else {
                let input = new sap.m.Input("", {
                });
                input.bindProperty("value", {
                    path: "/value"
                });
                input.setModel(new sap.ui.model.json.JSONModel(item));
                this.form.addContent(input);
            }
        }
    }
    /** 显示报表结果 */
    showResults(table: ibas.DataTable): void {
        if (!ibas.objects.isNull(this.tableResult)) {
            this.tableResult.destroy(true);
        }
        this.form.destroyContent();
        this.tableResult = new sap.ui.table.Table("", {
            enableSelectAll: false,
            visibleRowCount: 30,
            editable: false,
            rows: "{/rows}",
        });
        for (let col of table.columns) {
            this.tableResult.addColumn(
                new sap.ui.table.Column("", {
                    label: col.name,
                    autoResizable: true,
                    template: new sap.m.Text("", {
                        wrapping: false
                    }).bindProperty("text", {
                        path: col.name,
                    })
                })
            );
        }
        this.tableResult.setModel(new sap.ui.model.json.JSONModel({ rows: table.convert() }));
        this.form.addContent(this.tableResult);
    }
}
