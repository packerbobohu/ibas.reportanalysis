/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as reportApps from "../../bsapp/report/index";
import * as userApps from "../../bsapp/users/index";
import * as reportViews from "./report/index";
import * as userViews from "./users/index";

/**
 * 视图导航
 */
export default class Navigation extends ibas.ViewNavigation {

    /** 
     * 创建实例
     * @param id 应用id
     */
    protected newView(id: string): ibas.IView {
        let view: ibas.IView = null;
        switch (id) {
            case reportApps.ReportListApp.APPLICATION_ID:
                view = new reportViews.ReportListView();
                break;
            case reportApps.ReportChooseApp.APPLICATION_ID:
                view = new reportViews.ReportChooseView();
                break;
            case reportApps.ReportViewApp.APPLICATION_ID:
                view = new reportViews.ReportViewView();
                break;
            case reportApps.ReportEditApp.APPLICATION_ID:
                view = new reportViews.ReportEditView();
                break;
            case reportApps.CrystalReportViewApp.APPLICATION_ID:
                view = new reportViews.CrystalReportViewView();
                break;
            case userApps.UserReportPageApp.APPLICATION_ID:
                view = new userViews.UserReportPageView();
                break;
            default:
                break;
        }
        return view;
    }
}
