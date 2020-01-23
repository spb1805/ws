// /////////////////////////////////////////////////////////////////////////////
// @ Copyright Surecomp Israel, 2019.
// This document contains proprietary and confidential information, and shall
// not be reproduced, transferred, or disclosed to others, without the prior
// written consent of Surecomp.

package unit_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import netimex.util.CustomerDataTest;
import netimex.util.GetCustomerData;
import netimex.util.GetCustomerDataRequest;
import netimex.util.ObjectFactory;
import netimex.util.SetCustomerData;
import services.ws.WebServiceClient;
import services.ws.to.InterceptorsDataTo;

//@ComponentScan(basePackages = { "unit_test" })
@TestInstance(Lifecycle.PER_CLASS)
public class WsTest {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(WsTest.class);

	protected WebServiceClient webServiceClient;

	@BeforeAll
	public void init() {
		InterceptorsDataTo interceptorsDataTo = new InterceptorsDataTo();
		interceptorsDataTo.setUseSoapEnvPrefix(true);
		/*
		 * List<NamespaceDeclarationTo> namespaceDeclarationTo = new
		 * ArrayList<NamespaceDeclarationTo>(); namespaceDeclarationTo.add(new
		 * NamespaceDeclarationTo("ns", "http://www.rabobank.nl/XMLHeader/10"));
		 * namespaceDeclarationTo.add(new NamespaceDeclarationTo("req",
		 * "http://www.rabobank.nl/CRM/CRMI/GetRelation/7/Req"));
		 * interceptorsDataTo.setNamespaceDeclarationToList(namespaceDeclarationTo);
		 * interceptorsDataTo.setHeaderElementsPrefix("ns");
		 * interceptorsDataTo.setBodyElementsPrefix("req");
		 */
		webServiceClient = new WebServiceClient();
		webServiceClient.init("netimex.*", "http://localhost:8089/ws", interceptorsDataTo);
	}

	@Test
	public void getCustomerData() {
		ObjectFactory factory = new ObjectFactory();
		GetCustomerData request = factory.createGetCustomerData();
		GetCustomerDataRequest value = factory.createGetCustomerDataRequest();
		request.setGetCustDataRequest(value);
		Object response = webServiceClient.callWebService(request, null, null);
		Assertions.assertNotNull(response);
	}
	
	@Test
	public void setCustomerData() {
		ObjectFactory factory = new ObjectFactory();
		SetCustomerData request = factory.createSetCustomerData();
		CustomerDataTest value = factory.createCustomerDataTest();
		request.setCustomerDataTest(value);
		Object response = webServiceClient.callWebService(request, null, null);
		Assertions.assertNotNull(response);
	}
}
