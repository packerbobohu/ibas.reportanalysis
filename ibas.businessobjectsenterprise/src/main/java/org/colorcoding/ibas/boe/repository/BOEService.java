package org.colorcoding.ibas.boe.repository;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.i18n.i18n;
import org.colorcoding.ibas.boe.bo.BOEFolder;
import org.colorcoding.ibas.boe.bo.BOEReport;

import com.crystaldecisions.sdk.exception.SDKException;
import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.occa.security.ILogonTokenMgr;

/**
 * BOE服务
 * 
 * @author Niuren.Zhu
 *
 */
public class BOEService {

	private String address;

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	private String authentication;

	public final String getAuthentication() {
		if (this.authentication == null || this.authentication.isEmpty()) {
			this.setAuthentication("secEnterprise");
		}
		return authentication;
	}

	public final void setAuthentication(String authenticationType) {
		this.authentication = authenticationType;
	}

	private IEnterpriseSession session;

	public final IEnterpriseSession getSession() {
		return session;
	}

	private final void setSession(IEnterpriseSession session) {
		this.session = session;
	}

	public void logon(String user, String password) throws SDKException, BOEException {
		IEnterpriseSession session = CrystalEnterprise.getSessionMgr().logon(user, password, this.getAddress(),
				this.getAuthentication());
		this.setSession(session);
	}

	public String getToken() throws BOEException, SDKException {
		IEnterpriseSession session = this.getSession();
		if (session == null) {
			throw new BOEException(i18n.prop("msg_boe_not_logon_system"));
		}
		ILogonTokenMgr tokenMgr = session.getLogonTokenMgr();
		if (tokenMgr == null) {
			throw new BOEException(i18n.prop("msg_boe_not_found_token_manager"));
		}
		return tokenMgr.getDefaultToken();

	}

	/**
	 * 查询-BOE文件夹
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<BOEFolder> fetchFolder(ICriteria criteria, String token) {
		OperationResult<BOEFolder> operationResult = new OperationResult<>();
		try {

		} catch (Exception e) {
			operationResult = new OperationResult<>(e);
		}
		return operationResult;
	}

	/**
	 * 查询-BOE报表
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<BOEReport> fetchReport(ICriteria criteria, String token) {
		OperationResult<BOEReport> operationResult = new OperationResult<>();
		try {

		} catch (Exception e) {
			operationResult = new OperationResult<>(e);
		}
		return operationResult;
	}
}
