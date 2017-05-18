package org.colorcoding.ibas.businessobjectsenterprise;

import org.colorcoding.ibas.boe.repository.BOEException;
import org.colorcoding.ibas.boe.repository.BOEService;

import com.crystaldecisions.sdk.exception.SDKException;

import junit.framework.TestCase;

public class testBOEService extends TestCase {

	public void testToken() throws SDKException, BOEException {
		BOEService service = new BOEService();
		service.setAddress("192.168.0.43");
		service.logon("administrator", "Aa123456");
		System.out.println(String.format("token: %s", service.getToken()));
	}
}