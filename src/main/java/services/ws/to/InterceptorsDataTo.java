// /////////////////////////////////////////////////////////////////////////////
// @ Copyright Surecomp Israel, 2019.
// This document contains proprietary and confidential information, and shall
// not be reproduced, transferred, or disclosed to others, without the prior
// written consent of Surecomp.

package services.ws.to;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import services.ws.WebServiceClient;

/**
 * 
 * It encapsulates data for init all interceptors of the {@link WebServiceClient}
 *
 */
@Data
@ToString
public class InterceptorsDataTo {
	private boolean isUseSoapEnvPrefix; //if use "soapenv" prefix insted of the generated "SOAP-ENV"
	private List<NamespaceDeclarationTo> namespaceDeclarationToList;
	private String headerElementsPrefix;
	private String bodyElementsPrefix;
}
