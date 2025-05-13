package com.asset.MOD;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class WSHandler implements SOAPHandler<SOAPMessageContext> {

	 String sAuthenticationToken;

	    public WSHandler(String sAuthenticationToken) {
	        this.sAuthenticationToken = sAuthenticationToken;

	    }

	    public boolean handleMessage(SOAPMessageContext messageContext) {
	        SOAPMessage msg = messageContext.getMessage();
	        if ((Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
	            try {
	                String ECM_API_NAMESPACE = "urn:api.ecm.opentext.com";
	                SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
	                SOAPHeader header = envelope.getHeader();//.addHeader();
	                if(header == null)
	                {
	                    header=envelope.addHeader();
	                }
	                SOAPHeaderElement otAuthElement = header.addHeaderElement(new QName(ECM_API_NAMESPACE, "OTAuthentication"));
	                SOAPElement authTokenElement = otAuthElement.addChildElement(new QName(ECM_API_NAMESPACE, "AuthenticationToken"));
	                authTokenElement.addTextNode(this.sAuthenticationToken);
	                msg.saveChanges();
	                try {
	                    System.out.println("\n");
	                    msg.writeTo(System.out);
	                    System.out.println("\n");
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
//						System.out.println(msg.toString());
	            } catch (SOAPException e) {
	                e.printStackTrace();
	                return false;
	            }
	        }
	        return true;
	    }

	    public boolean handleFault(SOAPMessageContext messageContext) {
	        return true;
	    }

	    public void close(MessageContext messageContext) {
	    }

	    @SuppressWarnings({"rawtypes", "unchecked"})
	    public Set getHeaders() {
	        return new HashSet();
	    }
}
