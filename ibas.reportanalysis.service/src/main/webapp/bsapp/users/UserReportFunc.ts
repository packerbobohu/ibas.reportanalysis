/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import { UserReportPageApp } from "./UserReportPageApp";

export class UserReportPageFunc extends ibas.ModuleFunction {

    /** 功能标识 */
    static FUNCTION_ID = "c7b448c1-54e0-4b4e-ba15-21dc5b4b96f0";
    /** 功能名称 */
    static FUNCTION_NAME = "reportanalysis_func_user_report_page";
    /** 构造函数 */
    constructor() {
        super();
        this.id = UserReportPageFunc.FUNCTION_ID;
        this.name = UserReportPageFunc.FUNCTION_NAME;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 默认功能 */
    default(): ibas.IApplication<ibas.IView> {
        let app: UserReportPageApp = new UserReportPageApp();
        app.navigation = this.navigation;
        return app;
    }
}
