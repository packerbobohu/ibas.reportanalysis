package org.colorcoding.ibas.businessobjectsenterprise;

import com.crystaldecisions.sdk.exception.SDKException;

import junit.framework.TestCase;

public class testBOEService extends TestCase {

	public void testToken() throws SDKException, BOEException {
		BOEService service = new BOEService();
		service.setAddress("http://192.168.0.43:8080/BOE/");
		service.logon("administrator", "Aa123456");
		System.out.println(String.format("token: %s", service.getToken()));
	}
}