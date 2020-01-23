// /////////////////////////////////////////////////////////////////////////////
// @ Copyright Surecomp Israel, 2019.
// This document contains proprietary and confidential information, and shall
// not be reproduced, transferred, or disclosed to others, without the prior
// written consent of Surecomp.

package com.surecomp.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import netimex.util.CustomerDataTest;
import netimex.util.GetCustomerDataRequest;
import netimex.util.GetCustomerDataResponse;
import netimex.util.ObjectFactory;
import netimex.util.SetCustomerDataResponse;
import netimex.util.SetCustomerDataResponse2;

@Endpoint
public class WsEndpoint {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(WsEndpoint.class);

	/**
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = "http://util.netimex", localPart = "getCustomerData")
	@ResponsePayload
	public GetCustomerDataResponse getCustomerData(@RequestPayload GetCustomerDataRequest request) {
		ObjectFactory factory = new ObjectFactory();
		GetCustomerDataResponse ret = factory.createGetCustomerDataResponse();
		CustomerDataTest retVal = factory.createCustomerDataTest();
		ret.setGetCustomerDataReturn(retVal);
		return ret;
	}

	/**
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = "http://util.netimex", localPart = "setCustomerData")
	@ResponsePayload
	public SetCustomerDataResponse getCustomerData(@RequestPayload CustomerDataTest request) {
		ObjectFactory factory = new ObjectFactory();
		SetCustomerDataResponse ret = factory.createSetCustomerDataResponse();
		SetCustomerDataResponse2 retVal = factory.createSetCustomerDataResponse2();
		ret.setSetCustomerDataReturn(retVal);
		return ret;
	}
}
