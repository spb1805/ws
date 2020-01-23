// /////////////////////////////////////////////////////////////////////////////
// @ Copyright Surecomp Israel, 2019.
// This document contains proprietary and confidential information, and shall
// not be reproduced, transferred, or disclosed to others, without the prior
// written consent of Surecomp.

package services.ws;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import services.ws.to.InterceptorsDataTo;

/**
 * 
 * <code>WebServiceClient</code> is the universal Web services client, which
 * provides calls of different Web services the parameters for access of which
 * can be set by this class' init(..) method.
 *
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WebServiceClient extends WebServiceGatewaySupport {
	private ClientInterceptor[] interceptors;

	/**
	 * 
	 * @param contextPath            package name, which contains schema derived
	 *                               classes
	 * @param defaultUri             Web service endpoint URI
	 * @param namespaceDeclarationTo contains namespaces, which should be set for
	 *                               Envelope
	 */
	public void init(String contextPath, String defaultUri, InterceptorsDataTo interceptorsDataTo) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(contextPath);

		setDefaultUri(defaultUri);
		setMarshaller(marshaller);
		setUnmarshaller(marshaller);
		initInterceptors(interceptorsDataTo);
	}

	private void initInterceptors(InterceptorsDataTo interceptorsDataTo) {
		SoapClientInterceptor soapClientInterceptor = new SoapClientInterceptor();
		soapClientInterceptor.init(interceptorsDataTo);
		interceptors = new ClientInterceptor[] { soapClientInterceptor };
	}

	/**
	 * 
	 * @param request     any object, which can be recognized as SOAM envelope
	 * @param headerValue the object for creation SOAP message's header
	 * @param headerQName SOAP message's header's node name and namespaces' URI.
	 * <pre>
	 * For instance created by the invoker like: 
	 * <i>new QName("http://www.rabobank.nl/XMLHeader/10", "RaboHeader")</i>
	 * </pre>
	 * @return Web service's response
	 */
	public <T> Object callWebService(Object request, T headerValue, QName headerQName) {
		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
		webServiceTemplate.setInterceptors(interceptors);

		if (headerValue != null) {
			return webServiceTemplate.marshalSendAndReceive(getDefaultUri(), request, new WebServiceMessageCallback() {
				public void doWithMessage(WebServiceMessage message) {
					if (message instanceof SaajSoapMessage) {
						try {
							@SuppressWarnings({ "rawtypes", "unchecked" })
							JAXBElement<T> header = new JAXBElement(headerQName, headerValue.getClass(), null,
									headerValue);
							JAXBContext context = JAXBContext.newInstance(headerValue.getClass());
							Marshaller marshaller = context.createMarshaller();
							SoapHeader soapHeader = ((SoapMessage) message).getSoapHeader();
							marshaller.marshal(header, soapHeader.getResult());
						} catch (JAXBException e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			});
		} else {
			return webServiceTemplate.marshalSendAndReceive(getDefaultUri(), request);
		}
	}
}
