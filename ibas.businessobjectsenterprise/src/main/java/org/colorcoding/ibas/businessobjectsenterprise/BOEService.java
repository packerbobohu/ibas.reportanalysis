package org.colorcoding.ibas.businessobjectsenterprise;

import org.colorcoding.ibas.bobas.i18n.i18n;

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
}
