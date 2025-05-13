package com.asset.MOD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.soap.SOAPFaultException;

import com.opentext.livelink.service.core.Authentication;
import com.opentext.livelink.service.core.Authentication_Service;
import com.opentext.livelink.service.searchservices.SGraph;
import com.opentext.livelink.service.searchservices.SResultPage;
import com.opentext.livelink.service.searchservices.SearchService;
import com.opentext.livelink.service.searchservices.SearchService_Service;
import com.opentext.livelink.service.searchservices.SingleSearchRequest;
import com.opentext.livelink.service.searchservices.SingleSearchResponse;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class OpenText {

	String authToken = null;

	public String[] split_string(String str) {
		String[] arr = str.split("\\W");
		return arr;
	}

	public String authenticate(String name, String pass) throws IOException {

		// this function authenticate by generate token .
		Authentication_Service authService = new Authentication_Service();
		Authentication authClient = authService.getBasicHttpBindingAuthentication();
		try {
			System.out.print("Authenticating User...");

			authToken = authClient.authenticateUser(name, pass);
			System.out.println("SUCCESS!\n");
			System.out.println("inside authenticate fun value of token " + authToken);
			return authToken;
		} catch (SOAPFaultException e) {
		}

		return authToken;
	}

	public void get_cat(String cat_name) {
		SearchService_Service docService = new SearchService_Service();
		SearchService docManClient = docService.getBasicHttpBindingSearchService();
		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		SingleSearchRequest ssReq = new SingleSearchRequest();
		ssReq.setNumResultsToRetrieve(50);
		ssReq.setDataCollectionSpec("\"LES Enterprise\" ");
		ssReq.setFirstResultToRetrieve(1);
		ssReq.setQueryLanguage("Livelink Search API V1");
		ssReq.setResultSetSpec("where1=\"OTSubType\":\"131\" &where2=\"OTName\":\"test\"&boolean2=and");
		List<String> alResultTran = new ArrayList<String>();
		alResultTran.add("OTDataID");
		alResultTran.add("OTName");
		alResultTran.add("OTLocation");
		ssReq.setResultTransformationSpec(alResultTran);
		SingleSearchResponse ft = docManClient.search(ssReq, "");
		SResultPage ttt = ft.getResults();
		List<SGraph> gh = ttt.getItem();
		System.out.println("size = " + gh.size());
		SGraph ui = gh.get(0);
		// String[] arr = ui.getID().split("\\w");
		String rt = ui.getID();
		String[] arr = rt.split("=|&");
		System.out.println(arr[1]);

	}

	public void setSoapHeader(WSBindingProvider bindingProvider) throws SOAPException {
		String ECM_API_NAMESPACE = "urn:api.ecm.opentext.com";

		SOAPHeader header = MessageFactory.newInstance().createMessage().getSOAPPart().getEnvelope().getHeader();

		SOAPHeaderElement otAuthElement = header
				.addHeaderElement(new QName("urn:api.ecm.opentext.com", "OTAuthentication"));

		SOAPElement authTokenElement = otAuthElement
				.addChildElement(new QName("urn:api.ecm.opentext.com", "AuthenticationToken"));
		authTokenElement.addTextNode(authToken);

		bindingProvider.setOutboundHeaders(new Header[] { Headers.create(otAuthElement) });
	}

}
