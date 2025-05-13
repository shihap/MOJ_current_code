package com.asset.MOD;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keepautomation.barcodereader.BarcodeReader;
import com.keepautomation.barcodereader.BarcodeType;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.opentext.cws.BooleanObject;
import com.opentext.ecm.api.OTAuthentication;
import com.opentext.ecm.services.authws.AuthenticationService;
import com.opentext.livelink.service.collaboration.Collaboration_Service;
import com.opentext.livelink.service.core.AdminService_Service;
import com.opentext.livelink.service.core.Authentication;
import com.opentext.livelink.service.core.Authentication_Service;
import com.opentext.livelink.service.core.ContentService;
import com.opentext.livelink.service.core.ContentService_Service;
import com.opentext.livelink.service.core.DataValue;
import com.opentext.livelink.service.core.DateValue;
import com.opentext.livelink.service.core.FileAtts;
import com.opentext.livelink.service.core.IntegerValue;
import com.opentext.livelink.service.core.StringValue;
import com.opentext.livelink.service.docman.Attribute;
import com.opentext.livelink.service.docman.AttributeGroup;
import com.opentext.livelink.service.docman.AttributeGroupDefinition;
import com.opentext.livelink.service.docman.DateAttribute;
import com.opentext.livelink.service.docman.DocumentManagement;
import com.opentext.livelink.service.docman.DocumentManagement_Service;
import com.opentext.livelink.service.docman.GetNodesInContainerOptions;
import com.opentext.livelink.service.docman.IntegerAttribute;
import com.opentext.livelink.service.docman.Metadata;
import com.opentext.livelink.service.docman.Node;
import com.opentext.livelink.service.docman.NodePermissions;
import com.opentext.livelink.service.docman.NodeRight;
import com.opentext.livelink.service.docman.StringAttribute;
import com.opentext.livelink.service.memberservice.Group;
import com.opentext.livelink.service.memberservice.Member;
import com.opentext.livelink.service.memberservice.MemberPrivileges;
import com.opentext.livelink.service.memberservice.MemberService;
import com.opentext.livelink.service.memberservice.MemberService_Service;
import com.opentext.livelink.service.memberservice.User;
import com.opentext.livelink.service.searchservices.SGraph;
import com.opentext.livelink.service.searchservices.SResultPage;
import com.opentext.livelink.service.searchservices.SearchService;
import com.opentext.livelink.service.searchservices.SearchService_Service;
import com.opentext.livelink.service.searchservices.SingleSearchRequest;
import com.opentext.livelink.service.searchservices.SingleSearchResponse;
import com.spire.pdf.PdfDocument;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.StreamingDataHandler;
import com.sun.xml.ws.developer.WSBindingProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Fun {
	private static final String CS_IP = "10.107.191.3"; 
	String authToken = " ";
	Metadata metadata = new Metadata();
	AttributeGroup categoryTemplate = null;
	AttributeGroup categoryTemplate2 = null;

	public Fun() {
	}

	public DocMngmtTuple createDocManClient(String authToken) {

		// //System.out.println("inside and value of token = " + authToken);
		DocumentManagement_Service docManService = new DocumentManagement_Service();
		DocumentManagement docManClient = docManService.getBasicHttpBindingDocumentManagement();

		OTAuthentication otAuth = new OTAuthentication();
		otAuth.setAuthenticationToken(authToken);
		// //System.out.println("token value = " + authToken);

		try {
			String ECM_API_NAMESPACE = "urn:api.ecm.opentext.com";

			SOAPHeader header = MessageFactory.newInstance().createMessage().getSOAPPart().getEnvelope().getHeader();

			SOAPHeaderElement otAuthElement = header
					.addHeaderElement(new QName("urn:api.ecm.opentext.com", "OTAuthentication"));

			SOAPElement authTokenElement = otAuthElement
					.addChildElement(new QName("urn:api.ecm.opentext.com", "AuthenticationToken"));
			authTokenElement.addTextNode(otAuth.getAuthenticationToken());

			((WSBindingProvider) docManClient).setOutboundHeaders(new Header[] { Headers.create(otAuthElement) });
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		DocMngmtTuple rv = new DocMngmtTuple(docManClient, otAuth);

		System.out.println();
		return rv;
	}

	public String authenticate(String name, String pass) {
		Authentication_Service authService = new Authentication_Service();
		Authentication authClient = authService.getBasicHttpBindingAuthentication();
		try {
			System.out.print("Authenticating User...");

			authToken = authClient.authenticateUser(name, pass);
			System.out.println("SUCCESS!\n");
			return authToken;
		} catch (SOAPFaultException e) {
			e.getMessage();
			return "error";
		}

		// return authToken;
	}

	public String uploadFile(String authToken, long id, String fileID, String title, byte[] fileContent,
			boolean overwriteFlag, boolean verifyFlag, Metadata m, String doc_extension) throws IOException {
		DocMngmtTuple rv = createDocManClient(authToken);
		DocumentManagement docManClient = rv.docManagement;
		OTAuthentication otAuth = rv.OTAuth;

		Node nn = docManClient.getNode(id);

		// System.out.println("done till here");
		// System.out.println(title);
		if ((!verifyFlag) && (docManClient.getNode(344L) == null)) {
			// System.out.println("doesnt exist");
			return "";
		}
		if ((verifyFlag) && (docManClient.getNode(344L) != null)) {
			// System.out.println("already exists");
			return "";
		}

		File file = null;

		String str1 = title;
		String str2 = doc_extension;
		FileOutputStream fo = null;
		try {
			file = File.createTempFile(str1, "." + str2, null);
			fo = new FileOutputStream(file);
			fo.write(fileContent);
			fo.close();

		} catch (IOException e) {
			e.printStackTrace();

			try {
				fo.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		} finally {
			try {
				fo.close();
			} catch (IOException e) {
				System.out.println("inside finally");
				e.printStackTrace();
			}
		}

		long PARENT_ID = id;
		String contextID = null;
		try {
			System.out.print("Generating context ID...");
			String str = "";
			if ((!fileID.equals("")) && (fileID != null))
				str = "File ID:" + fileID + "\n";
			str = str + "Title:" + title;
			contextID = docManClient.createDocumentContext(PARENT_ID, title, "", false, m);
			System.out.println("SUCCESS!\n");
		} catch (SOAPFaultException e) {
			System.out.println("FAILED!\n");
			System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage());
			
			return "";
		}
		ContentService_Service contentService = new ContentService_Service();
		ContentService contentServiceClient = contentService
				.getBasicHttpBindingContentService(new WebServiceFeature[] { new MTOMFeature() });
		int CHUNK_SIZE = 10240;
		((BindingProvider) contentServiceClient).getRequestContext()
				.put("com.sun.xml.ws.transport.http.client.streaming.chunk.size", Integer.valueOf(10240));

		BasicFileAttributes fileAttributes;
		try {
			fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]);
		} catch (IOException e) {
			// BasicFileAttributes fileAttributes;
			System.out.println("Failed to read file attributes!\n");
			System.out.println(e.getMessage());
			return "";
		}

		// BasicFileAttributes fileAttributes;
		FileAtts fileAtts = new FileAtts();
		try {
			fileAtts.setCreatedDate(
					DatatypeFactory.newInstance().newXMLGregorianCalendar(fileAttributes.creationTime().toString()));
			fileAtts.setFileName(file.getName());
			fileAtts.setFileSize(Long.valueOf(file.length()));
			fileAtts.setModifiedDate(DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(fileAttributes.lastModifiedTime().toString()));

		} catch (DatatypeConfigurationException e) {
			System.out.println("Failed to set file attributes!\n");
			System.out.println(e.getMessage());
			return "";
		}
		try {
			String ECM_API_NAMESPACE = "urn:api.ecm.opentext.com";
			String CORE_NAMESPACE = "urn:Core.service.livelink.opentext.com";

			SOAPHeader header = MessageFactory.newInstance().createMessage().getSOAPPart().getEnvelope().getHeader();

			SOAPHeaderElement otAuthElement = header
					.addHeaderElement(new QName("urn:api.ecm.opentext.com", "OTAuthentication"));

			SOAPElement authTokenElement = otAuthElement
					.addChildElement(new QName("urn:api.ecm.opentext.com", "AuthenticationToken"));
			authTokenElement.addTextNode(otAuth.getAuthenticationToken());

			SOAPHeaderElement contextIDElement = header
					.addHeaderElement(new QName("urn:Core.service.livelink.opentext.com", "contextID"));
			contextIDElement.addTextNode(contextID);

			SOAPHeaderElement fileAttsElement = header
					.addHeaderElement(new QName("urn:Core.service.livelink.opentext.com", "fileAtts"));

			SOAPElement createdDateElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "CreatedDate"));
			createdDateElement.addTextNode(fileAtts.getCreatedDate().toString());

			SOAPElement modifiedDateElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "ModifiedDate"));
			modifiedDateElement.addTextNode(fileAtts.getModifiedDate().toString());

			SOAPElement fileSizeElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "FileSize"));
			fileSizeElement.addTextNode(fileAtts.getFileSize().toString());

			SOAPElement fileNameElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "FileName"));
			fileNameElement.addTextNode(fileAtts.getFileName());

			List<Header> headers = new ArrayList();
			headers.add(Headers.create(otAuthElement));
			headers.add(Headers.create(contextIDElement));
			headers.add(Headers.create(fileAttsElement));

			((WSBindingProvider) contentServiceClient).setOutboundHeaders(headers);
		} catch (SOAPException e) {
			System.out.println("Failed to set SOAP headers!\n");
			System.out.println(e.getMessage());
			return "";
		}

		try {
			System.out.print("Uploading document...");
			String objectID = contentServiceClient.uploadContent(new DataHandler(new FileDataSource(file)));

			System.out.println("SUCCESS!\n");
			System.out.println("New document uploaded with ID = " + objectID);
			return objectID;

		} catch (Exception e) {
			System.out.println("value error");
			System.out.println("FAILED!\n");
//			 System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage() +
//			 e.getClass());
			System.out.println(e.getMessage());
			if (e.getMessage().contains("One or more invalid values for attribute "))
				return "700";
			else if (e.getMessage().contains("The value is not one of the valid values")) {
				return "The value is not one of the valid values";}
			else if (e.getMessage().contains("Value exceeds defined attribute length"))
				return "403";
			
			else
				return "501";
		}
	}

	public String uploadFile_version(String authToken, long id, String fileID, String title, byte[] fileContent,
			boolean overwriteFlag, boolean verifyFlag, Metadata m) throws IOException {
		DocMngmtTuple rv = createDocManClient(authToken);
		DocumentManagement docManClient = rv.docManagement;
		OTAuthentication otAuth = rv.OTAuth;

		System.out.println("done till here");
		System.out.println(title);
		if ((!verifyFlag) && (docManClient.getNode(344L) == null)) {
			System.out.println("doesnt exist");
			return "1";
		}
		if ((verifyFlag) && (docManClient.getNode(344L) != null)) {
			System.out.println("already exists");
			return "0";
		}

		File file = null;

		String str1 = title;
		String str2 = "pdf";

		FileOutputStream fo = null;
		try {
			String[] arr = str1.split("[.]");
			System.out.println("arr[0] will be = " + arr[0]);
			System.out.println("arr[1] will be = " + arr[1]);
			file = File.createTempFile(arr[0], "." + arr[1], null);
			// file = File.createTempFile(str1, null);
			fo = new FileOutputStream(file);
			fo.write(fileContent);
			fo.close();

		} catch (IOException e) {
			System.out.println("here here here");
			e.printStackTrace();

			// try {
			// fo.close();
			// } catch (IOException ee) {
			// ee.printStackTrace();
			// }
		}
		// finally {
		// try {
		// fo.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }

		long PARENT_ID = id;
		String contextID = null;
		System.out.println("file content size = " + fileContent.length);
		try {
			System.out.print("Generating context ID...");
			String str = "";
			if ((!fileID.equals("")) && (fileID != null))
				str = "File ID:" + fileID + "\n";
			str = str + "Title:" + title;

			contextID = docManClient.addVersionContext(id, null);
			System.out.println("content id = " + contextID);
			System.out.println("SUCCESS!\n");
		} catch (SOAPFaultException e) {
			System.out.println("FAILED!\n");
			System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage());
			return "1";
		}
		ContentService_Service contentService = new ContentService_Service();
		ContentService contentServiceClient = contentService
				.getBasicHttpBindingContentService(new WebServiceFeature[] { new MTOMFeature() });
		int CHUNK_SIZE = 10240;
		((BindingProvider) contentServiceClient).getRequestContext()
				.put("com.sun.xml.ws.transport.http.client.streaming.chunk.size", Integer.valueOf(10240));

		BasicFileAttributes fileAttributes;
		try {
			fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]);
		} catch (IOException e) {
			// BasicFileAttributes fileAttributes;
			System.out.println("Failed to read file attributes!\n");
			System.out.println(e.getMessage());
			return "1";
		}

		// BasicFileAttributes fileAttributes;
		FileAtts fileAtts = new FileAtts();
		try {
			fileAtts.setCreatedDate(
					DatatypeFactory.newInstance().newXMLGregorianCalendar(fileAttributes.creationTime().toString()));
			fileAtts.setFileName(file.getName());
			fileAtts.setFileSize(Long.valueOf(file.length()));
			fileAtts.setModifiedDate(DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(fileAttributes.lastModifiedTime().toString()));
		} catch (DatatypeConfigurationException e) {
			System.out.println("Failed to set file attributes!\n");
			System.out.println(e.getMessage());
			return "1";
		}
		try {
			String ECM_API_NAMESPACE = "urn:api.ecm.opentext.com";
			String CORE_NAMESPACE = "urn:Core.service.livelink.opentext.com";

			SOAPHeader header = MessageFactory.newInstance().createMessage().getSOAPPart().getEnvelope().getHeader();

			SOAPHeaderElement otAuthElement = header
					.addHeaderElement(new QName("urn:api.ecm.opentext.com", "OTAuthentication"));

			SOAPElement authTokenElement = otAuthElement
					.addChildElement(new QName("urn:api.ecm.opentext.com", "AuthenticationToken"));
			authTokenElement.addTextNode(otAuth.getAuthenticationToken());

			SOAPHeaderElement contextIDElement = header
					.addHeaderElement(new QName("urn:Core.service.livelink.opentext.com", "contextID"));
			contextIDElement.addTextNode(contextID);

			SOAPHeaderElement fileAttsElement = header
					.addHeaderElement(new QName("urn:Core.service.livelink.opentext.com", "fileAtts"));

			SOAPElement createdDateElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "CreatedDate"));
			createdDateElement.addTextNode(fileAtts.getCreatedDate().toString());

			SOAPElement modifiedDateElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "ModifiedDate"));
			modifiedDateElement.addTextNode(fileAtts.getModifiedDate().toString());

			SOAPElement fileSizeElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "FileSize"));
			fileSizeElement.addTextNode(fileAtts.getFileSize().toString());

			SOAPElement fileNameElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "FileName"));
			fileNameElement.addTextNode(fileAtts.getFileName());

			List<Header> headers = new ArrayList();
			headers.add(Headers.create(otAuthElement));
			headers.add(Headers.create(contextIDElement));
			headers.add(Headers.create(fileAttsElement));

			((WSBindingProvider) contentServiceClient).setOutboundHeaders(headers);
		} catch (SOAPException e) {
			System.out.println("Failed to set SOAP headers!\n");
			System.out.println(e.getMessage());
			return "1";
		}

		try {
			System.out.print("Uploading document...");
			String objectID = contentServiceClient.uploadContent(new DataHandler(new FileDataSource(file)));

			System.out.println("SUCCESS!\n");
			System.out.println("New document uploaded ");
			objectID = get_version_no(Long.toString(id));
			return objectID;

		} catch (SOAPFaultException e) {

			System.out.println("FAILED!\n");
			System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage() + e.getClass());
			return "1";
		}
		// return "1";

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

	public GregorianCalendar format_date(String s) throws IOException, DatatypeConfigurationException, ParseException {
		System.out.println("String passed to fun = " + s);
		String[] arr = s.split("/");
		Integer day = Integer.valueOf(Integer.parseInt(arr[0]));
		Integer month = Integer.valueOf(Integer.parseInt(arr[1]));
		Integer year = Integer.valueOf(Integer.parseInt(arr[2]));
		System.out.println("date inside format_date");
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		GregorianCalendar date = new GregorianCalendar(year.intValue(), month.intValue() - 1, day.intValue());

		System.out.println();
		System.out.println("date inside format = " + date);
		return date;
	}

	public GregorianCalendar format_date2(String s) throws IOException, DatatypeConfigurationException, ParseException {
		String[] arr = s.split("-");
		Integer day = Integer.valueOf(Integer.parseInt(arr[0]));
		Integer month = Integer.valueOf(Integer.parseInt(arr[1]));
		Integer year = Integer.valueOf(Integer.parseInt(arr[2]));
		System.out.println();
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		GregorianCalendar date = new GregorianCalendar(year.intValue(), month.intValue() - 1, day.intValue());

		System.out.println();
		System.out.println("date inside format = " + date);
		return date;
	}

	public List<Node> get_nodes(long id) {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		GetNodesInContainerOptions nodesListOptions = new GetNodesInContainerOptions();
		nodesListOptions.setMaxResults(Integer.MAX_VALUE);
		nodesListOptions.setMaxDepth(0);

		// Node rootNode = docClient.getRootNode("EnterpriseWS");
		// Node contianterNode;
		// List paths = new Vector();
		List<Node> er = docClient.getNodesInContainer(id, nodesListOptions);
		return er;
	}

	public long get_id(List<Node> tes, String node_name) {
		long id = 0;
		for (int i = 0; i < tes.size(); i++) {

			String tr = tes.get(i).getName();
			if (tr.equalsIgnoreCase(node_name.trim())) {
				id = tes.get(i).getID();
				return id;
			}
		}
		return id;
	}

	public long get_id_hash(HashMap<String, String> hash, String node_name) {

		return (Long.parseLong(hash.get(node_name.trim())));

	}

	public String load_data() {
		List<String> list = new ArrayList<String>();
		String url = "", username = "", password = "";
		Properties prop = new Properties();
		InputStream input = null;
		String id = "";

		try {
			String currentRelativePath = System.getProperty("catalina.base");
			input = new FileInputStream(currentRelativePath + "/ot_wsdls_config.properties");

			// //System.out.println("full path will be = " + currentRelativePath +
			// "/config.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			id = prop.getProperty("enterprise");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return id;

	}
	
	public String create_folder_poc(String parent_id, String name, Metadata m) {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		try {
			Node tt = docManClient.createFolder(Long.valueOf(parent_id), name, null, m);
			Long id = tt.getID();
//			Node nn = docManClient.createFolder(id, name + "", null, null);
			return id.toString();
		} catch (Exception e) {
			System.out.println("drb");
			return "error";
		}

	}
	
	public String return_cat_id(String cat_name) {
		String cat_id="";
		
		if (cat_name.equals("أرشيف الأحكام")) 
		{
			cat_id="37026";
		}
		else if (cat_name.equals("أرشيف الأشخاص")) 
		{
			cat_id="37028";
		}
		else if (cat_name.equals("أرشيف الاوامر")) 
		{
			cat_id="37030";
		}
		else if (cat_name.equals("أرشيف الإعلانات والإنذارات")) 
		{
			cat_id="37020";
		}
		else if (cat_name.equals("أرشيف التفتيش")) 
		{
			cat_id="37018";
		}
		else if (cat_name.equals("أرشيف التوكيلات")) 
		{
			cat_id="37022";
		}
		else if (cat_name.equals("أرشيف الدعاوى")) 
		{
			cat_id="37024";
		}
		else if (cat_name.equals("أرشيف الشهادات")) 
		{
			cat_id="37016";
		}
		else if (cat_name.equals("أرشيف الصور")) 
		{
			cat_id="37032";
		}
		else if (cat_name.equals("أرشيف المستندات الأخرى")) 
		{
			cat_id="37014";
		}
		else {return "error";}
		
		
		
		
		return cat_id;
	}

	public List<Node> get_nodes_under_path(String path) {
		List<Node> er = null;
		if (path.contains("/")) {
			if (check_path_pattern(path)) {
				String[] arr = path.split("/");
				if (arr[0].equals("Enterprise") || arr[0].equals("enterprise")) {

					long id = Long.parseLong(load_data());
					er = get_nodes(id);
					for (int i = 1; i < arr.length; i++) {
						id = get_id(er, arr[i]);
						er = get_nodes(id);
					}
				}
			}

		}

		return er;

	}

	public String search_cat(String name) {
		String cat_id = "error";
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
		ssReq.setResultSetSpec("where1=\"OTSubType\":\"131\" &where2=\"OTName\":" + name + "&boolean2=and");
		List<String> alResultTran = new ArrayList<String>();
		alResultTran.add("OTDataID");
		alResultTran.add("OTName");
		alResultTran.add("OTLocation");
		alResultTran.add("OTObjectSize");
		ssReq.setResultTransformationSpec(alResultTran);
		SingleSearchResponse ft = docManClient.search(ssReq, "");
		SResultPage ttt = ft.getResults();
		List<SGraph> gh = ttt.getItem();

		if (gh.size() >= 1) {
			System.out.println("size = " + gh.size());
			SGraph ui = gh.get(0);
			String rt = ui.getID();
			String[] arr = rt.split("=|&");
			System.out.println(arr[1]);
			cat_id = arr[1];
			return cat_id;
		}
		return cat_id;

	}

	public String search_folder(String path) {
		String folder_id = "error";
		SearchService_Service docService = new SearchService_Service();
		SearchService docManClient = docService.getBasicHttpBindingSearchService();
		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		String[] arr2 = path.split(":");
		String folder_name = arr2[arr2.length - 1];

		SingleSearchRequest ssReq = new SingleSearchRequest();
		ssReq.setNumResultsToRetrieve(50);
		ssReq.setDataCollectionSpec("\"LES Enterprise\" ");
		ssReq.setFirstResultToRetrieve(1);
		ssReq.setQueryLanguage("Livelink Search API V1");
		ssReq.setResultSetSpec("where1=\"OTSubType\":\"0\" &where2=\"OTName\":" + folder_name + "&boolean2=and");
		List<String> alResultTran = new ArrayList<String>();
		alResultTran.add("OTDataID");
		alResultTran.add("OTName");
		alResultTran.add("OTLocation");
		alResultTran.add("OTObjectSize");
		ssReq.setResultTransformationSpec(alResultTran);
		SingleSearchResponse ft = docManClient.search(ssReq, "");
		SResultPage ttt = ft.getResults();
		List<SGraph> gh = ttt.getItem();
		System.out.println("size = " + gh.size());

		if (gh.size() >= 1) {
			SGraph ui = gh.get(0);
			String rt = ui.getID();
			String[] arr = rt.split("=|&");
			System.out.println(arr[1]);
			folder_id = arr[1];
			return folder_id;
		}
		return folder_id;

	}
	
	public Metadata get_cat_data(String id, String cat_values ) throws NumberFormatException, IOException,
			DatatypeConfigurationException, ParseException, ParserConfigurationException, SAXException {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();
		
		Metadata metadata_1 = new Metadata();


		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		HashMap<String, String> hash = new HashMap<String, String>();
		HashMap<String, String> hash2 = new HashMap<String, String>();
		String[] arrr;
		List<Attribute> ft = get_cat_by_id(id);
		cat_size = ft.size();
		// System.out.println("cat size = " + cat_size);
		arrr = cat_values.split(";");

		List<String> required_atrr = new ArrayList<String>();
		for (Attribute ft1 : ft) {
			if (ft1.isRequired())
				required_atrr.add(ft1.getDisplayName());
		}

		for (Attribute t : ft) {
			String atr = t.getDisplayName();
			// atr = atr.replaceAll("\\s", "");
			// atr = atr.toLowerCase();
			cat_attr_names.add(atr);
			hash.put(atr, t.getType());
			String[] arr4 = t.getKey().split("[.]");
			// System.out.println("name = " + atr + "----" + "index = " + arr4[2]);
			hash2.put(atr, arr4[2]);
		}

		for (int i = 0; i < arrr.length; i++) {
			String str = arrr[i];
			// str = str.replaceAll("\\s", "");
			// str = str.toLowerCase();
			// System.out.println("inside loop that reform = " + str);
			if (cat_attr_names.contains(str)) {
				// System.out.println("inside if that form");
				arrr[i] = str;
				// continue;
			}

		}
		List<String> check_if_required = new ArrayList<String>();
		for (String rr : arrr) {
			if (cat_attr_names.contains(rr)) {
				check_if_required.add(rr);
			}
		}

		if (check_if_required.size() < required_atrr.size())
			return null;
		boolean check = check_cat_name(cat_attr_names, cat_values);

		List<String> check_list = new ArrayList<String>();

		// check_list = convert_array_to_list(arrr);

		if (check) {
			// System.out.println("valid");
			String type = "";
			int index = -1;
			int index_3 = 0;
			boolean found = false;
			String name = "";
			int counter = 0;
			Stack<String> stack = new Stack<String>();
			// for (String t : cat_attr_names)
			// System.out.println("cat_attr_names = " + t);

			// for (int t = 0; t < arrr.length; t++)
			// System.out.println("arrr values are = " + arrr[t]);

			for (int i = 0; i < arrr.length; i++) {

				// System.out.println("value that will iterated on = " + arrr[i]);

				if (cat_attr_names.contains(arrr[i]) && found) {
					update_cat_val(name, stack, index, type);
					type = hash.get(arrr[i]);
					found = true;
					name = arrr[i];
					index = Integer.parseInt(hash2.get(arrr[i]));

				} else if (cat_attr_names.contains(arrr[i])) {
					// System.out.println("inside first if");
					counter++;
					found = true;
					name = arrr[i];
					type = hash.get(arrr[i]);
					index = Integer.parseInt(hash2.get(arrr[i]));
				} else {
					counter++;
					// System.out.println("inside third if");
					if ((i + 1) == arrr.length) {
						stack.push(arrr[i]);
						update_cat_val(name, stack, index, type);
					}
					stack.push(arrr[i]);
				}
			}

//			String cat_id = test_return("test");
			categoryTemplate2 = docManClient.getCategoryTemplate(Long.valueOf(id));
			List<AttributeGroup> list_ff = new ArrayList<>();
			list_ff.add(categoryTemplate2);
//			list_ff.add(categoryTemplate);
			metadata_1.getAttributeGroups().addAll(list_ff);
			
			// System.out.println("metadata size = " +
			// metadata.getAttributeGroups().size());
			// metadata.getAttributeGroups().add(categoryTemplate);
			
			return metadata_1;

		} else
			return null;
	}


	public String get_id(String id, byte[] arr, String cat_values, String parent_path, String folder_id,
			String doc_name, String doc_extension) throws NumberFormatException, IOException,
			DatatypeConfigurationException, ParseException, ParserConfigurationException, SAXException {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();
		
		String object_id="";

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		HashMap<String, String> hash = new HashMap<String, String>();
		HashMap<String, String> hash2 = new HashMap<String, String>();
		String[] arrr;
		List<Attribute> ft = get_cat_by_id(id);
		cat_size = ft.size();
		// System.out.println("cat size = " + cat_size);
		arrr = cat_values.split("=|~|;");

		List<String> required_atrr = new ArrayList<String>();
		for (Attribute ft1 : ft) {
			if (ft1.isRequired())
				required_atrr.add(ft1.getDisplayName());
		}

		for (Attribute t : ft) {
			String atr = t.getDisplayName();
			// atr = atr.replaceAll("\\s", "");
			// atr = atr.toLowerCase();
			cat_attr_names.add(atr);
			hash.put(atr, t.getType());
			String[] arr4 = t.getKey().split("[.]");
			// System.out.println("name = " + atr + "----" + "index = " + arr4[2]);
			hash2.put(atr, arr4[2]);
		}

		for (int i = 0; i < arrr.length; i++) {
			String str = arrr[i];
			// str = str.replaceAll("\\s", "");
			// str = str.toLowerCase();
			// System.out.println("inside loop that reform = " + str);
			if (cat_attr_names.contains(str)) {
				// System.out.println("inside if that form");
				arrr[i] = str;
				// continue;
			}

		}
		List<String> check_if_required = new ArrayList<String>();
		for (String rr : arrr) {
			if (cat_attr_names.contains(rr)) {
				check_if_required.add(rr);
			}
		}

		if (check_if_required.size() < required_atrr.size()) {
			return "error required";}
		
		boolean check = check_cat_name(cat_attr_names, cat_values);

		List<String> check_list = new ArrayList<String>();

		// check_list = convert_array_to_list(arrr);

		if (check) {
			// System.out.println("valid");
			String type = "";
			int index = -1;
			int index_3 = 0;
			boolean found = false;
			String name = "";
			int counter = 0;
			Stack<String> stack = new Stack<String>();
			// for (String t : cat_attr_names)
			// System.out.println("cat_attr_names = " + t);

			// for (int t = 0; t < arrr.length; t++)
			// System.out.println("arrr values are = " + arrr[t]);

			for (int i = 0; i < arrr.length; i++) {

				// System.out.println("value that will iterated on = " + arrr[i]);

				if (cat_attr_names.contains(arrr[i]) && found) {
					update_cat_val(name, stack, index, type);
					type = hash.get(arrr[i]);
					found = true;
					name = arrr[i];
					index = Integer.parseInt(hash2.get(arrr[i]));

				} else if (cat_attr_names.contains(arrr[i])) {
					// System.out.println("inside first if");
					counter++;
					found = true;
					name = arrr[i];
					type = hash.get(arrr[i]);
					index = Integer.parseInt(hash2.get(arrr[i]));
				} else {
					counter++;
					// System.out.println("inside third if");
					if ((i + 1) == arrr.length) {
						stack.push(arrr[i]);
						String catVal=update_cat_val(name, stack, index, type);
						if (catVal.equals("error for value")) {
							object_id="error for value";
							return object_id;
						}
					}
					stack.push(arrr[i]);
				}
			}

//			String cat_id = test_return("RMS");
//			categoryTemplate2 = docManClient.getCategoryTemplate(Long.parseLong(id));
			List<AttributeGroup> list_ff = new ArrayList<>();
//			list_ff.add(categoryTemplate2);
			list_ff.add(categoryTemplate);
			metadata.getAttributeGroups().addAll(list_ff);
			// System.out.println("metadata size = " +
			// metadata.getAttributeGroups().size());
			// metadata.getAttributeGroups().add(categoryTemplate);
			 object_id = uploadFile(authToken, Long.parseLong(folder_id), "", doc_name, arr, true, true, metadata,
					doc_extension);
			return object_id;

		} else
			return "error";
	}

	private boolean check_cat_name(List<String> cat_attr_names, String cat_values) {
		String[] arr = cat_values.split(";");
		String val = "";
		for (int i = 0; i < arr.length; i++) {
			// String str = arr[i].replaceAll("\\s", "").toLowerCase();
			String str = arr[i];
			// System.out.println("str = " + str);
			if (cat_attr_names.contains(str)) {
				val = arr[i];
				if (!check(str, cat_attr_names))
					return false;

			}
		}

		return true;
	}

	private boolean check_length(int counter, List<String> cat_attr_names) {

		for (String str : cat_attr_names) {
			if (str.length() == counter) {
				return true;
			}
		}
		return false;
	}

	boolean check(String str, List<String> cat_attr_names) {
		for (String s : cat_attr_names) {
			if (s.equals(str))
				return true;
		}
		return false;
	}

	public List<Attribute> get_cat_by_id(String id) {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		// //System.out.println("inside update cat val");

		// AttributeGroup categoryTemplate2 =
		// docManClient.getCategoryTemplate(Long.parseLong(id));
		AttributeGroupDefinition rt = docManClient.getCategoryDefinition(Long.parseLong(id));

		List<Attribute> ft = rt.getAttributes();
		if (ft.size() >= 1) {
			categoryTemplate = docManClient.getCategoryTemplate(Long.parseLong(id));
			return ft;
		}

		return null;
	}

	public String update_cat_val(String name, Stack<String> stack, int index, String type)
			throws IOException, DatatypeConfigurationException, ParseException {
		int counter = 0;
		// System.out.println("type passed = " + type);
		// System.out.println("index passed = " + index);

		try {
			if (type.equals("String")) {
				List<DataValue> ls = categoryTemplate.getValues();
				StringValue first = null;
				for (DataValue f : ls) {
					String str = f.getDescription();
					if(str.contains("-")) {
						return "error for value";
					}
					// System.out.println("list values are" + str);
					// System.out.println("attr name = " + name + " " + "attr index " + index);
					// str = str.replaceAll("\\s", "");
					// str = str.toLowerCase();
					if (name.equals(str))
						first = (StringValue) f;
				}
				first.getValues().clear();
				// System.out.println("stack size = " + stack.size());
				for (String url : stack) {
					first.getValues().add(url);
				}
				stack.clear();
			}

			else if (type.equals("Integer")) {
				List<DataValue> ls = categoryTemplate.getValues();
				IntegerValue first = null;
				for (DataValue f : ls) {
					String str = f.getDescription();
					if(str.contains("-")) {
						return "error for value";
					}
					// System.out.println("list values are" + str);
					// str = str.replaceAll("\\s", "");
					// str = str.toLowerCase();
					if (name.equals(str))
						first = (IntegerValue) f;
				}
				first.getValues().clear();
				// System.out.println("stack size = " + stack.size());
				for (String url : stack) {
					first.getValues().add(Long.parseLong(url));
				}
				stack.clear();
			}

			else if (type.equals("Date")) {
				DateValue second = null;
				List<DataValue> ls = categoryTemplate.getValues();
				for (DataValue f : ls) {
					String str = f.getDescription();
					// System.out.println("list values are" + str);
					// str = str.replaceAll("\\s", "");
					// str = str.toLowerCase();
					if (name.equals(str))
						second = (DateValue) f;
				}
				second.getValues().clear();
				for (String url : stack) {
					GregorianCalendar sd56 = format_date(url);
					second.getValues().add(DatatypeFactory.newInstance().newXMLGregorianCalendar(sd56));
				}
				stack.clear();

			}
		} catch (Exception e) {
			// e.printStackTrace();
			// e.getMessage();
			System.out.println(e);
			return ("error for value");
		}
		return "1";

	}

	public String update_cat_val_2(String name, Stack<String> stack, int index, String type, String nodeid,
			String cat_name, Boolean found2) throws IOException, DatatypeConfigurationException, ParseException {
		int counter = 0;
		System.out.println("type passed = " + type);
		System.out.println("index passed = " + index);

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		StringValue first = null;
		IntegerValue fourth = null;
		Node child = docManClient.getNode(Long.parseLong(nodeid));
		// Long parent_id = child.getParentID();
		// Node parent = docManClient.getNode(parent_id);
		List<AttributeGroup> list = child.getMetadata().getAttributeGroups();

		if (type.equals("String")) {
			for (AttributeGroup atr : list) {
				if (atr.getDisplayName().equals(cat_name)) {
					List<DataValue> less = atr.getValues();
					for (DataValue f : less) {
						String str = f.getDescription();
						System.out.println("list values are" + str);
						str = str.replaceAll("\\s", "");
						str = str.toLowerCase();
						if (name.equals(str))
							first = (StringValue) f;
					}
					if (found2) {
						System.out.println("stack size = " + stack.size());
						for (String url : stack) {
							first.getValues().add(url);
						}
						stack.clear();
					} else {
						first.getValues().clear();
						System.out.println("stack size = " + stack.size());
						for (String url : stack) {
							first.getValues().add(url);
						}
						stack.clear();

					}
				}
			}
		}

		if (type.equals("Integer")) {
			for (AttributeGroup atr : list) {
				if (atr.getDisplayName().equals(cat_name)) {
					List<DataValue> less = atr.getValues();
					for (DataValue f : less) {
						String str = f.getDescription();
						System.out.println("list values are" + str);
						str = str.replaceAll("\\s", "");
						str = str.toLowerCase();
						if (name.equals(str))
							fourth = (IntegerValue) f;
					}
					if (found2) {
						System.out.println("stack size = " + stack.size());
						for (String url : stack) {
							fourth.getValues().add(Long.parseLong(url));
						}
						stack.clear();
					} else {
						fourth.getValues().clear();
						System.out.println("stack size = " + stack.size());
						for (String url : stack) {
							fourth.getValues().add(Long.parseLong(url));
						}
						stack.clear();
					}
				}
			}

		}

		if (type.equals("Date")) {
			DateValue second = null;
			for (AttributeGroup atr : list) {
				if (atr.getDisplayName().equals(cat_name)) {
					List<DataValue> less = atr.getValues();
					for (DataValue f : less) {
						String str = f.getDescription();
						System.out.println("list values are" + str);
						str = str.replaceAll("\\s", "");
						str = str.toLowerCase();
						if (name.equals(str))
							second = (DateValue) f;
					}
					second.getValues().clear();
					System.out.println("stack size = " + stack.size());
					second.getValues().clear();
					for (String url : stack) {
						GregorianCalendar sd56 = format_date(url);
						second.getValues().add(DatatypeFactory.newInstance().newXMLGregorianCalendar(sd56));
					}
					stack.clear();
				}
			}

		}

		try {
			docManClient.updateNode(child);
		} catch (SOAPFaultException e) {
			return "error more values";
		}

		return "";

	}

	public byte[] get_doc_by_id(String doc_id, String version_no) throws IOException {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
		// We need to manually set the SOAP header to include the authentication
		// token
		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			System.out.println("Failed to set authentication SOAP header!\n" + e.getMessage());
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		String n = docClient.getVersionContentsContext(Long.parseLong(doc_id), Long.parseLong(version_no));

		ContentService_Service contentService = new ContentService_Service();
		ContentService contentServiceClient = contentService.getBasicHttpBindingContentService(new MTOMFeature());
		try {
			setSoapHeader((WSBindingProvider) contentServiceClient);
		} catch (Exception e) {
			// _logger.error("Failed to set authentication SOAP header!\n" +
			// e.getMessage());
			// //System.out.println("Failed to set authentication SOAP
			// header!\n");
			// //System.out.println(e.getMessage());
			// //System.out.println(e.getStackTrace());
		}

		StreamingDataHandler downloadStream = null;

		// Call the downloadContent() method to download the file
		try {
			System.out.print("Downloading file...");
			downloadStream = (StreamingDataHandler) contentServiceClient.downloadContent(n);
			InputStream in = downloadStream.getInputStream();
			byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(in);
			// File file = new File("");
			// OutputStream os = new FileOutputStream(file);
			// os.write(byteArray);
			// os.close();
			System.out.println("length = " + byteArray.length);
			return byteArray;
		} catch (SOAPFaultException e) {
			System.out.println("FAILED!\n");
			System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage());
			// return null;
		}

		// Stream the download to the local file path
		try {
			// File file = new File("C:/test.pdf");
			// downloadStream.moveTo(file);
			// //System.out.println("SUCCESS!\n");
			// //System.out.println("Downloaded " + file.length() + " bytes to " +
			// ".\n");
			//
			// ByteArrayOutputStream output = new ByteArrayOutputStream();
			// downloadStream.writeTo(output);
			// //System.out.println("output size = " + output.size());
			// return output.toByteArray();
		} catch (Exception e) {
			System.out.println("Failed to download file!\n");
			System.out.println(e.getMessage());
		} finally {
			// Always close the streams
			try {
				downloadStream.close();
			} catch (IOException e) {
				System.out.println("Failed to close the download stream!\n");
				System.out.println(e.getMessage());
			}
		}
		// return null;
		return null;
	}

	public String construct_search(String cat_id, String cat_values, String username, String password)
			throws IOException {
		Long cat_long = Long.parseLong(cat_id);
		String[] arr = cat_values.split("\\s");
		String search = "";
		Long lll = new Long(1);
		// String search2 =
		// "where1=\"OTSubType\":\"144\"&where2=\"Attr_468586_2\":\"asset\"&where3=\"Attr_468586_9\":\"ahmed\"&where4=\"Attr_468586_9\":\"kareem\"&boolean4=and&where5=\"Attr_468586_10\":\"sherton\"&boolean5=and";
		search += "where=OTSubType:144 AND ";
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		String[] arrr;
		boolean check = false;
		int counterrr = 2;
		HashMap<String, String> hash = new HashMap<String, String>();

		List<Attribute> ft = get_cat_by_id(cat_id);
		arrr = cat_values.split("\\s");

		for (Attribute t : ft)
			cat_attr_names.add(t.getDisplayName());

		for (String f : cat_attr_names)
			hash.put(f, Integer.toString(counterrr++));

		int index_3 = 0;
		int index = -1;
		int index2 = 2;
		boolean found = false;
		boolean found2 = false;
		String name = "";
		int counter = 0;
		Stack<String> stack = new Stack<String>();
		String end = "";
		int counter_and = 0;
		int counter_or = 0;
		String operator = "";

		List<String> check_list = new ArrayList<String>();

		check_list = convert_array_to_list(arrr);

		// for (String tt : check_list)
		// System.out.print("check list = " + tt);

		for (int i = 0; i < check_list.size(); i++) {
			// for (String ss : check_list) {

			if (cat_attr_names.contains(check_list.get(i))) {
				index_3 = search(ft, check_list.get(i), hash);
				continue;

			} else if (check_list.get(i).equals("="))
				operator = ":";

			else if (check_list.get(i).equals("and"))
				search += "AND ";

			else if (check_list.get(i).equals("or"))
				search += "OR ";

			else if (check_list.get(i).equals("~"))
				// continue;
				search += "AND ";
			else if (check_list.get(i).equals("like"))
				operator = "like";
			else if (check_list.get(i).equals(">"))
				operator = ":>";

			else if (check_list.get(i).equals("<"))
				operator = ":<";

			else if (check_list.get(i).equals("!="))
				operator = ":!";
			else {
				if (operator.equals("like")) {
					if (cat_attr_names.contains(check_list.get(i - 1))) {
						search += "Attr_" + cat_long + "_" + index_3 + ":" + check_list.get(i).replaceAll("\\s", "")
								+ "*" + " ";
					} else {
						// System.out.println("inside without attr" + check_list.get(i));
						search += check_list.get(i).replaceAll("\\s", "") + "*" + " ";
					}

				} else
					search += "Attr_" + cat_long + "_" + index_3 + operator + check_list.get(i);
			}

		}

		String doc_id = " ";
		String ticket = authenticate_rest(username, password);
		doc_id = search_api(ticket, search);
		if (doc_id.equals("error2"))
			return "error2";
		if (doc_id.equals("error no item found"))
			return "error no item found";
		System.out.println("doc_id = " + doc_id);
		if (!doc_id.equals(" "))
			return doc_id;
		return "error";

	}

	private List<String> convert_array_to_list(String[] arrr) {

		String str6 = "";
		List<String> check_list = new ArrayList<String>();
		for (int i = 0; i < arrr.length; i++) {

			if (arrr[i].equals("=") || arrr[i].equals(">") || arrr[i].equals("<") || arrr[i].equals("!=")
					|| arrr[i].equals("like")) {
				// if (str6.length() == 0) {
				// //System.out.println("inside latest");
				// check_list.add(arrr[i]);
				// continue;
				// }
				if (str6.equals("") || str6.equals(" ")) {
					check_list.add(arrr[i]);
				} else {
					str6 = str6.replaceAll("\\s+$", "");
					check_list.add(str6);
					check_list.add(arrr[i]);
					str6 = "";
				}

			} else if (arrr[i].equals("and") || arrr[i].equals("or")) {
				check_list.add(str6);
				check_list.add(arrr[i]);
				str6 = "";
			}

			else {
				if (i == arrr.length - 1)
					check_list.add(arrr[i]);
				else
					str6 += arrr[i] + " ";
			}

		}
		return check_list;
	}

	public String search_api(String ticket, String search) throws IOException {
		List<String> data_list = new ArrayList<String>();
		data_list = get_data_2();
		int counter = 0;
		// System.out.println("search query = " + search);
		String id = " ";
		String data2 = search;
		byte[] postdata = data2.getBytes(StandardCharsets.UTF_8);
		URL url2 = new URL(
				data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2) + "/otcs/cs.exe/api/v2/search");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con2.setRequestProperty("charset", "utf-8");
		con2.setRequestProperty("Content-Length", Integer.toString(postdata.length));
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("POST");
		con2.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con2.getOutputStream())) {
			wr.write(postdata);
		} catch (Exception e) {
			System.out.println("error happened in server");
		}
		System.out.println("status code retured = " + con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("results");
		if (nn.size() == 0)
			return "error no item found";
		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			id = parameterNode.findValue("id").asText();
			if (!id.equals(" ")) {
				counter++;
			}
			// return id;

		}
		// System.out.println("counter = " + counter);
		if (counter > 1)
			return "error2";
		else
			return id;
	}

	public String authenticate_rest(String username, String password) throws IOException {
		List<String> data_list = new ArrayList<String>();
		data_list = get_data_2();
		String data = "username=" + username + "&password=" + password;
		URL url = new URL(
				data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2) + "/otcs/cs.exe/api/v1/auth");
		 System.out.println("authentication url will be = " + data_list.get(0) + "://"
		 + data_list.get(1) + ":" + data_list.get(2) + "/otcs/cs.exe/api/v1/auth");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
		con.getInputStream();
		// System.out.println(con.getResponseCode());
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader buff = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output = "";
		String result = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
		JSONObject json = new JSONObject(result);
		String ticket = (String) json.get("ticket");
		return ticket;
	}

	private int search(List<Attribute> ft, String str, HashMap<String, String> hash) {

		// for (Map.Entry<String, String> entry : hash.entrySet()) {
		// System.out.println("map values are = " + entry.getKey() + " = " +
		// entry.getValue());
		// }

		// for (Attribute ff : ft)
		// System.out.println("types found = " + ff.getDisplayName() + " " +
		// ff.getType());

		for (Attribute f : ft) {
			// //System.out.println("types found = " + f.getDisplayName() + " " +
			// f.getType());
			if (f.getDisplayName().equals(str)) {
				int xx = Integer.parseInt(hash.get(f.getDisplayName()));
				String[] arr = f.getKey().split("[.]");
				return (Integer.parseInt(arr[2]));
			}

		}
		return 0;

	}

	public List<String> construct_search2(String cat_id, String cat_values, String username, String password)
			throws IOException {
		List<String> list = new ArrayList<String>();
		Long cat_long = Long.parseLong(cat_id);
		String[] arr = cat_values.split("\\s");
		String search = "";
		Long lll = new Long(1);
		String search2 = "where1=\"OTSubType\":\"144\"&where2=\"Attr_468586_2\":\"asset\"&where3=\"Attr_468586_9\":\"ahmed\"&where4=\"Attr_468586_9\":\"kareem\"&boolean4=and&where5=\"Attr_468586_10\":\"sherton\"&boolean5=and";
		search += "where=OTSubType:144 AND ";
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		String[] arrr;
		boolean check = false;
		int counterrr = 2;
		HashMap<String, String> hash = new HashMap<String, String>();

		List<Attribute> ft = get_cat_by_id(cat_id);
		arrr = cat_values.split("\\s");

		for (Attribute t : ft)
			cat_attr_names.add(t.getDisplayName());

		for (String f : cat_attr_names)
			hash.put(f, Integer.toString(counterrr++));

		int index_3 = 0;
		// int index = -1;
		// int index2 = 2;
		// boolean found = false;
		// boolean found2 = false;
		// String name = "";
		// int counter = 0;
		// Stack<String> stack = new Stack<String>();
		// String end = "";
		// int counter_and = 0;
		// int counter_or = 0;
		String operator = "";

		List<String> check_list = new ArrayList<String>();

		check_list = convert_array_to_list(arrr);

		// for (int i = 0; i < arrr.length; i++) {
		for (String ss : check_list) {

			if (cat_attr_names.contains(ss)) {
				index_3 = search(ft, ss, hash);
				continue;

			} else if (ss.equals("="))
				operator = ":";
			else if (ss.equals(">"))
				operator = ":>";
			else if (ss.equals("<"))
				operator = ":<";
			else if (ss.equals("!="))
				operator = ":!";
			else if (ss.equals("like"))
				operator = "like";

			else if (ss.equals("and"))
				search += "AND ";

			else if (ss.equals("or"))
				search += "OR ";

			else if (ss.equals("~"))
				continue;
			else {
				if (operator.equals("like"))
					search += "Attr_" + cat_long + "_" + index_3 + ":" + ss + "*" + " ";
				else
					search += "Attr_" + cat_long + "_" + index_3 + operator + ss + " ";
			}

		}

		String doc_id = " ";
		String ticket = authenticate_rest(username, password);
		list = search_api2(ticket, search);

		return list;

	}

	private List<String> search_api2(String ticket, String search) throws IOException {
		List<String> data_list = new ArrayList<String>();
		data_list = get_data_2();
		List<String> list = new ArrayList<String>();
		System.out.println("search query = " + search);
		String id = " ";
		String data2 = search;
		byte[] postdata = data2.getBytes(StandardCharsets.UTF_8);
		URL url2 = new URL(
				data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2) + "/otcs/cs.exe/api/v2/search");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con2.setRequestProperty("charset", "utf-8");
		con2.setRequestProperty("Content-Length", Integer.toString(postdata.length));
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("POST");
		con2.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con2.getOutputStream())) {
			wr.write(postdata);
		}
		System.out.println(con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("results");
		if (nn.size() == 0) {
			list.add("error no item found");
			return list;
		}

		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			id = parameterNode.findValue("id").asText();
			if (!id.equals(" "))
				list.add(id);
		}
		return list;

	}

	public String get_node(String node_id, byte[] arr, String doc_name) throws IOException {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();
		Node n;
		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		System.out.println("inside try");

		n = docManClient.getNode(Long.parseLong(node_id));
		if (n == null)
			return "document not found";

		Long parent_id = n.getParentID();

		// if (check_document_exist(String.valueOf(parent_id), username, password,
		// doc_name))
		// return "doc exist";

		// String obj_id = uploadFile_version(authToken, Long.parseLong(node_id), "",
		// n.getName(), arr, true, true, null);
		String obj_id = uploadFile_version(authToken, n.getID(), "", doc_name, arr, true, true, null);
		// n.setName(doc_name);
		// docManClient.updateNode(n);
		return obj_id;
	}

	public String get_node_version(String node_id, byte[] arr, String doc_name, String username, String password)
			throws IOException {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();
		Node n;
		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		System.out.println("inside try");

		n = docManClient.getNode(Long.parseLong(node_id));
		System.out.println(n);
		if (n == null) {
			return "document not found";
		}

		String old_name = n.getName();
	
		Long parent_id = n.getParentID();

		System.out.println("arr will be = " + arr.length);

		String obj_id = uploadFile_version(authToken, n.getID(), "", doc_name, arr, true, true, null);
		
		// new logic for rename but not applied
//		System.out.println("old name will be = " + old_name);
//		System.out.println("new name will be = " + doc_name);
//		if (!old_name.equals(doc_name)) {
//			System.out.println("inside name not the same");
//			if (check_document_exist(String.valueOf(parent_id), username, password, doc_name))
//				return "doc exist";
//		}
//
//		n.setName(doc_name);
//		docManClient.updateNode(n);
		return obj_id;
	}

	public boolean get_node_by_id(String id) {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		// try {
		// System.out.println("inside try two");
		Node n = docManClient.getNode(Long.parseLong(id));
		if (n != null)
			return true;
		else
			return false;
		// return n;
		// }
		// catch (NullPointerException e) {
		// //System.out.println("inside exception");
		// }
		// return null;

	}

	public byte[] import_doc(String doc_id) throws IOException {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
		// We need to manually set the SOAP header to include the authentication
		// token
		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			// _logger.error("Failed to set authentication SOAP header!\n" +
			// e.getMessage());
			// //System.out.println("Failed to set authentication SOAP
			// header!\n");
			// //System.out.println(e.getMessage());
			// //System.out.println(e.getStackTrace());
		}

		String n = docClient.getVersionContentsContext(Long.parseLong(doc_id), 0);

		ContentService_Service contentService = new ContentService_Service();
		ContentService contentServiceClient = contentService.getBasicHttpBindingContentService(new MTOMFeature());
		try {
			setSoapHeader((WSBindingProvider) contentServiceClient);
		} catch (Exception e) {
			// _logger.error("Failed to set authentication SOAP header!\n" +
			// e.getMessage());
			// //System.out.println("Failed to set authentication SOAP
			// header!\n");
			// //System.out.println(e.getMessage());
			// //System.out.println(e.getStackTrace());
		}

		StreamingDataHandler downloadStream = null;

		// Call the downloadContent() method to download the file
		try {
			System.out.print("Downloading file...");
			downloadStream = (StreamingDataHandler) contentServiceClient.downloadContent(n);
			InputStream in = downloadStream.getInputStream();
			byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(in);
			System.out.println("length = " + byteArray.length);
			return byteArray;
		} catch (SOAPFaultException e) {
			System.out.println("FAILED!\n");
			System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage());
			// return null;
		}
		return null;

	}

	public HashMap<String, String> get_names(List<String> list) {
		HashMap<String, String> hash = new HashMap<String, String>();

		for (String s : list) {
			DocumentManagement_Service docService = new DocumentManagement_Service();
			DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
			// We need to manually set the SOAP header to include the
			// authentication
			// token
			try {
				setSoapHeader((WSBindingProvider) docClient);
			} catch (Exception e) {
				System.out.println("Failed to set authentication SOAP header!\n" + e.getMessage());
				System.out.println("Failed to set authentication SOAP header!\n");
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace());
			}

			Node n = docClient.getNode(Long.parseLong(s));
			// //System.out.println("id = " + s + "name = " + n.getName());
			
			hash.put(s, n.getName());

		}
		return hash;
	}

	public String get_version_no(String doc_id) {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
		// We need to manually set the SOAP header to include the authentication
		// token
		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			System.out.println("Failed to set authentication SOAP header!\n" + e.getMessage());
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		Node r = docClient.getNode(Long.parseLong(doc_id));
		Long version_no = r.getVersionInfo().getVersionNum();
		return (version_no.toString());
		// return null;
	}

	public HashMap<String, String> get_names_by_path(List<Node> list, String cat_name) {
		HashMap<String, String> hash = new HashMap<String, String>();
		for (Node n : list) {

			//if (n.getType().equals("Document")) {
				Metadata rr = n.getMetadata();
				List<AttributeGroup> rt = rr.getAttributeGroups();
				for (AttributeGroup er : rt) {
					if (er.getDisplayName().equals(cat_name))
						hash.put(Long.toString(n.getID()), n.getName());
				}
				// if (rt.contains(cat_name))

			//}

		}

		if (hash.size() == 0) {
			hash.put("error", "error");
			return hash;
		}
		return hash;
	}

	public String delete(String doc_id) {
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
		// We need to manually set the SOAP header to include the authentication
		// token
		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			System.out.println("Failed to set authentication SOAP header!\n" + e.getMessage());
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		try {
			docClient.deleteNode(Long.parseLong(doc_id));
			return "document deleted";
		} catch (Exception e) {
			return "document not deleted";
		}

	}

	public List<String> search_cat2(List<String> list) throws ParserConfigurationException, SAXException, IOException {
		List<String> less = new ArrayList<String>();
		String cat_id = "error";
		SearchService_Service docService = new SearchService_Service();
		SearchService docManClient = docService.getBasicHttpBindingSearchService();
		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		for (String str : list) {
			// SingleSearchRequest ssReq = new SingleSearchRequest();
			// ssReq.setNumResultsToRetrieve(50);
			// ssReq.setDataCollectionSpec("\"LES Enterprise\" ");
			// ssReq.setFirstResultToRetrieve(1);
			// ssReq.setQueryLanguage("Livelink Search API V1");
			// ssReq.setResultSetSpec("where1=\"OTSubType\":\"131\" &where2=\"OTName\":" +
			// str + "&boolean2=and");
			// List<String> alResultTran = new ArrayList<String>();
			// alResultTran.add("OTDataID");
			// alResultTran.add("OTName");
			// alResultTran.add("OTLocation");
			// alResultTran.add("OTObjectSize");
			// ssReq.setResultTransformationSpec(alResultTran);
			// SingleSearchResponse ft = docManClient.search(ssReq, "");
			// SResultPage ttt = ft.getResults();
			// List<SGraph> gh = ttt.getItem();
			//
			// if (gh.size() >= 1) {
			// SGraph ui = gh.get(0);
			// String rt = ui.getID();
			// String[] arr = rt.split("=|&");
			// //System.out.println(arr[1]);
			// less.add(arr[1]);
			// }
			less.add(get_catogory_id_xml(str));

		}

		if (less.size() > 1) {
			List<String> less2 = filter(less);
			return less2;
		} else {
			less.add("error");
			return less;
		}

		// return less2;
	}

	private List<String> filter(List<String> less) {

		List<String> filtered = new ArrayList<>(new HashSet<>(less));
		return filtered;
	}

	public String construct_search3(List<String> cat_id, String cat_values, String username, String password)
			throws IOException {

		// for (String str : cat_values) {

		Long cat_long = null;
		String[] arr = cat_values.split("\\s");
		String search = "";
		search += "where=OTSubType:144 AND ";
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		List<Attribute> cat_attr_names2 = new ArrayList<Attribute>();
		String[] arrr;
		boolean check = false;
		int counterrr = 2;
		HashMap<String, String> hash = new HashMap<String, String>();
		HashMap<String, String> cat_attr_with_id = new HashMap<String, String>();

		cat_attr_with_id = get_cat_by_id2(cat_id);

		arrr = cat_values.split("\\s");
		cat_attr_names = get_cat_attr_name(cat_id);
		cat_attr_names2 = get_cat_attr_name2(cat_id);

		int index_3 = 0;
		int index = -1;
		int index2 = 2;
		boolean found = false;
		boolean found2 = false;
		String name = "";
		int counter = 0;
		Stack<String> stack = new Stack<String>();
		String end = "";
		int counter_and = 0;
		int counter_or = 0;
		String operator = "";

		List<String> check_list = new ArrayList<String>();

		check_list = convert_array_to_list(arrr);

		// for (int i = 0; i < arrr.length; i++) {

		for (String ss : check_list) {

			if (cat_attr_names.contains(ss)) {
				index_3 = search3(cat_attr_names2, ss);
				cat_long = get_cat_attr_id(cat_attr_with_id, ss);
				continue;

			} else if (ss.equals("="))
				operator = ":";
			else if (ss.equals(">"))
				operator = ":>";
			else if (ss.equals("<"))
				operator = ":<";
			else if (ss.equals("!="))
				operator = ":!";
			else if (ss.equals("like"))
				operator = "like";

			else if (ss.equals("and"))
				search += "AND ";

			else if (ss.equals("or"))
				search += "OR ";

			else if (ss.equals("~"))
				continue;
			else {
				if (operator.equals("like"))
					search += "Attr_" + cat_long + "_" + index_3 + ":" + ss + "*" + " ";
				else
					search += "Attr_" + cat_long + "_" + index_3 + operator + ss + " ";
			}

		}
		String doc_id = " ";
		String ticket = authenticate_rest(username, password);
		doc_id = search_api(ticket, search);
		if (doc_id.equals("error no item found"))
			return "error no item found";
		if (doc_id.equals("error2"))
			return "error2";
		System.out.println("doc_id = " + doc_id);
		if (!doc_id.equals(" "))
			return doc_id;
		return "error";

		// }

	}

	public List<String> construct_search5(List<String> cat_id, String cat_values, String username, String password)
			throws IOException {

		// for (String str : cat_values) {

		Long cat_long = null;
		String[] arr = cat_values.split("\\s");
		String search = "";
		search += "where=OTSubType:144 OR OTSubType:1 AND  ";
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		List<Attribute> cat_attr_names2 = new ArrayList<Attribute>();
		String[] arrr;
		boolean check = false;
		int counterrr = 2;
		HashMap<String, String> hash = new HashMap<String, String>();
		HashMap<String, String> cat_attr_with_id = new HashMap<String, String>();

		cat_attr_with_id = get_cat_by_id2(cat_id);

		arrr = cat_values.split("\\s");
		cat_attr_names = get_cat_attr_name(cat_id);
		cat_attr_names2 = get_cat_attr_name2(cat_id);

		int index_3 = 0;
		int index = -1;
		int index2 = 2;
		boolean found = false;
		boolean found2 = false;
		String name = "";
		int counter = 0;
		Stack<String> stack = new Stack<String>();
		String end = "";
		int counter_and = 0;
		int counter_or = 0;
		String operator = "";

		List<String> check_list = new ArrayList<String>();

		check_list = convert_array_to_list(arrr);

		// for (int i = 0; i < arrr.length; i++) {
		for (String ss : check_list) {

			if (cat_attr_names.contains(ss)) {
				index_3 = search3(cat_attr_names2, ss);
				cat_long = get_cat_attr_id(cat_attr_with_id, ss);
				continue;

			} else if (ss.equals("="))
				operator = ":";
			else if (ss.equals(">"))
				operator = ":>";
			else if (ss.equals("<"))
				operator = ":<";
			else if (ss.equals("!="))
				operator = ":!";
			else if (ss.equals("like"))
				operator = "like";

			else if (ss.equals("and"))
				search += "AND ";

			else if (ss.equals("or"))
				search += "OR ";

			else if (ss.equals("~"))
				continue;
			else {
				if (operator.equals("like"))
					search += "Attr_" + cat_long + "_" + index_3 + ":" + ss + "*" + " ";
				else
					search += "Attr_" + cat_long + "_" + index_3 + operator + ss + " ";
			}

		}
		String ticket = authenticate_rest(username, password);
		List<String> list = search_api2(ticket, search);
		return list;

		// }

	}

	private Long get_cat_attr_id(HashMap<String, String> cat_attr_with_id, String str) {
		String id = cat_attr_with_id.get(str);
		return (Long.parseLong(id));
	}

	private List<Attribute> get_cat_attr_name2(List<String> cat_id) {

		List<Attribute> blk = new ArrayList<Attribute>();
		AttributeGroup categoryTemplate = null;

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		for (String str : cat_id) {
			AttributeGroupDefinition rt = docManClient.getCategoryDefinition(Long.parseLong(str));
			List<Attribute> ft = rt.getAttributes();
			if (ft.size() >= 1) {
				for (Attribute atr : ft)
					blk.add(atr);

			}
		}
		return blk;
		// return null;
	}

	private int search3(List<Attribute> list, String str) {

		for (Attribute f : list) {

			if (f.getDisplayName().equals(str)) {
				String[] arr = f.getKey().split("[.]");
				return (Integer.parseInt(arr[2]));

			}

		}
		return 0;
	}

	private List<String> get_cat_attr_name(List<String> cat_id) {
		List<String> blk = new ArrayList<String>();
		AttributeGroup categoryTemplate = null;

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		for (String str : cat_id) {
			AttributeGroupDefinition rt = docManClient.getCategoryDefinition(Long.parseLong(str));
			List<Attribute> ft = rt.getAttributes();
			if (ft.size() >= 1) {
				for (Attribute atr : ft)
					blk.add(atr.getDisplayName());

			}
		}
		return blk;
	}

	private HashMap<String, String> get_cat_by_id2(List<String> cat_id) {
		HashMap<String, String> hash = new HashMap<String, String>();
		AttributeGroup categoryTemplate = null;

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		for (String str : cat_id) {
			AttributeGroupDefinition rt = docManClient.getCategoryDefinition(Long.parseLong(str));
			List<Attribute> ft = rt.getAttributes();
			if (ft.size() >= 1) {
				for (Attribute atr : ft) {
					hash.put(atr.getDisplayName(), str);
					System.out.println("values = " + atr.getDisplayName() + " " + str);
				}

			}
		}

		// return null;

		return hash;
	}

	public String check_path(String path, String username, String password) throws IOException, SQLException {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		String ticket = authenticate_rest(username, password);
		// String id = "error";
		// List<String> list = new ArrayList<String>();
		// if (path.contains("/")) {
		// String[] arr = path.split("/");
		// if (arr[0].equals("enterprise") || arr[0].equals("Enterprise")) {
		// id = load_data();
		// for (int i = 1; i < arr.length; i++) {
		// list.add(arr[i]);
		// }
		//
		// Node n = docManClient.getNodeByPath(Long.parseLong(id), list);
		// if (n == null)
		// return "error";
		// else
		// return (String.valueOf(n.getID()));
		//
		// }
		// }
		// return id;

		String id = "error";
		if (path.contains("/")) {

System.out.println("PATH IS ="+path);
			String[] arr = path.split("/");
			for (int i = 0; i < arr.length; i++) {
				System.out.println("index"+i +arr[i]);
			}
			if (arr[0].equals("enterprise") || arr[0].equals("Enterprise")) {
				id = load_data();
				
				System.out.println("id is"+id);

				for (int i = 1; i < arr.length; i++) {
					System.out.println("id will be = "+id +" the iterate on value will be = "+arr[i]);
					id = get_nodes_hash(id, ticket, arr[i]);
					System.out.println("id is="+id);
					if (id.equals(""))
						return "error";
				}
			} else
				return "error";

		}
		System.out.println("id will be = " + id);
		return id;

		// List<String> list = Arrays.asList(path.split("/"));
		// for (String str : list)
		// System.out.println("path values will be = " + str);
		// Node n = docManClient.getNodeByPath(Long.valueOf(id), list);
		// if (n == null)
		// return "error";
		// else
		// return String.valueOf(n.getID());
		//
		// }
		//
		// else
		// return "error";

		// String id = "error";
		// if (path.contains("/")) {
		// String[] arr = path.split("/");
		// if (arr[0].equals("enterprise") || arr[0].equals("Enterprise")) {
		// id = load_data();
		//
		// for (int i = 1; i < arr.length; i++) {
		//
		// id = test_path_database(id, arr[i]);
		// if (id.equals("error"))
		// return id;
		// }
		//
		// }
		// }
		// return id;

	}

//	private String test_path_database(String id, String string) throws IOException, SQLException {
//
//		Dbclass db = new Dbclass();
//		// db.connect();
//		String result = db.get_path(id, string);
//		if (result == "")
//			return "error";
//		else
//			return result;
//
//	}
	
	
	public boolean checkUserInGroup(String ticket) throws IOException, JSONException, org.json.simple.parser.ParseException {
		URL url = new URL ("http://" + CS_IP + "/otcs/cs.exe/api/v2/members/memberof");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("OTCSTicket", ticket);
		

		con.setDoOutput(true);

		int code = con.getResponseCode();
		System.out.println(code);
		
		con.getInputStream();
		String results = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);

		JSONObject obj = new JSONObject(results);
//		System.out.println(obj);

	    JSONArray arr = obj.getJSONArray("results");
	    
	    JSONParser parser = new JSONParser();
	    org.json.simple.JSONObject object = (org.json.simple.JSONObject) parser.parse(arr.getJSONObject(0).get("data").toString());
	    
	    JSONParser parse = new JSONParser();
	    org.json.simple.JSONObject objec = (org.json.simple.JSONObject) parse.parse(object.get("properties").toString());
	    objec.get("name");
	    
	    System.out.println(objec);
	    if(objec.get("name").toString().equals("RW"))
	    {return true;}
	    else {return false;}
//	return    objec.get("name").toString();


	}
	
	public boolean checkUserInGroup_r(String ticket) throws IOException, JSONException, org.json.simple.parser.ParseException {
		URL url = new URL ("http://" + CS_IP + "/otcs/cs.exe/api/v2/members/memberof");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("OTCSTicket", ticket);
		

		con.setDoOutput(true);

		int code = con.getResponseCode();
		System.out.println(code);
		
		con.getInputStream();
		String results = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);

		JSONObject obj = new JSONObject(results);
		System.out.println(obj);

	    JSONArray arr = obj.getJSONArray("results");
	    
	    JSONParser parser = new JSONParser();
	    org.json.simple.JSONObject object = (org.json.simple.JSONObject) parser.parse(arr.getJSONObject(0).get("data").toString());
	    
	    JSONParser parse = new JSONParser();
	    org.json.simple.JSONObject objec = (org.json.simple.JSONObject) parse.parse(object.get("properties").toString());
	    objec.get("name");
	    
	    System.out.println(objec);
	    if(objec.get("name").toString().equals("RW")||objec.get("name").toString().equals("R"))
	    {return true;}
	    else {return false;}
//	return    objec.get("name").toString();


	}
	
	
	public String checkVersions(String ticket,String node) throws IOException, JSONException, org.json.simple.parser.ParseException {
		URL url = new URL ("http://" + CS_IP + "/otcs/cs.exe/api/v1/nodes/"+node+"/versions/latest");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("OTCSTicket", ticket);
		

		con.setDoOutput(true);

		int code = con.getResponseCode();
		if (code!=200) {return "error";}
		else {
		System.out.println(code);
		
		con.getInputStream();
		String results = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);


		JSONObject obj = new JSONObject(results);
//		System.out.println(obj);

	    JSONObject ob = (JSONObject) obj.get("data");
	    

		
		return ob.get("version_number_name").toString();
		}


	}
	
	public String compareVersions(String latestVersion, String inputVersion) {
		
		if(latestVersion.equals("1") && inputVersion.equals("1")) {
			return "0";
		}
		else if(latestVersion.equals("1") && inputVersion.equals("0")) {
			return "0";
		}
		else if(inputVersion.equals("0")) {
			return "0";
		}
		else if(Long.parseLong(inputVersion)>Long.parseLong(latestVersion)) {
			return "Version dosen't exist";
		}
		
		
		return "";
	}

	private String get_nodes_hash(String id, String ticket, String node) throws IOException {
		String id_returned = "";
		List<String> data_list = get_data();
		HashMap<String, String> hash = new HashMap<String, String>();
		String api = "http://" + CS_IP + "/otcs/cs.exe/api/v1/nodes/{id}/nodes?id=" + id + "&where_name="
				+ URLEncoder.encode(node, StandardCharsets.UTF_8.toString()) + "&extra=false";
		 System.out.println("url will be = " + api);
		URL url2 = new URL(api);
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("GET");
		con2.setDoOutput(true);

		// System.out.println("status code retured = " + con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("data");
		// if (nn.size() == 0)
		// return "error no item found";
		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			
			String parent_id=parameterNode.findValue("parent_id").asText();
			id_returned = parameterNode.findValue("id").asText();
			String name=parameterNode.findValue("name").asText();
			System.out.println("parent id found will be = "+parent_id);
			//System.out.println("node id will be = "+id_returned);
			
			
			
			if(name.equalsIgnoreCase(node))
			{
				if(!id_returned.equals(""))
				{
					if(parent_id.equals(id))
						return id_returned;
				}
			}
			
			
			
			
			
			else 
				return "";

		}

		return id_returned;
	}

	
	public long create_exception_folder(String parent_path, String cat_name,String token,String cat_values) throws NumberFormatException, IOException, DatatypeConfigurationException, ParseException, ParserConfigurationException, SAXException {
		String unique_path[]=parent_path.split("/");
		String last_node= "";
		long folderCreatedId = -1;
		if (cat_name.equals("أرشيف الدعاوى")) {
			String cat_id=return_cat_id(cat_name);
			 last_node = "ملف الدعوى " + "(" + getCaseNum(cat_values) + ")";
			 folderCreatedId = createfolder_2(token, 37046, last_node, cat_id, cat_values);
		}
		else if (cat_name.equals("أرشيف الاوامر")) {
			String cat_id=return_cat_id(cat_name);
			 last_node= "ملف الأمر " + "(" + getCaseNum(cat_values) + ")";
			 folderCreatedId = createfolder_2(token, 37041, last_node, cat_id, cat_values);
		}
		
		return folderCreatedId;
		
	}
	
	private String getCaseNum(String catValues) {
        String [] values = catValues.split(";");
        for(String value: values) {
            String [] attr = value.split("=");
            if(attr[0].equals("مسلسل الدعوى") || attr[0].equals("مسلسل الأمر")) {
                return attr[1];
            }
        }
        return String.valueOf(System.currentTimeMillis());
    }
	
	private boolean get_nodes_hash_2(String id, String ticket, String node) throws IOException {
		String id_returned = "";
		List<String> data_list = get_data();
		HashMap<String, String> hash = new HashMap<String, String>();
		String api = "http://" + CS_IP + "/otcs/cs.exe/api/v1/nodes/{id}/nodes?id=" + id + "&where_name="
				+ URLEncoder.encode(node, StandardCharsets.UTF_8.toString()) + "&extra=false";
		// System.out.println("url will be = " + api);
		URL url2 = new URL(api);
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("GET");
		con2.setDoOutput(true);

		// System.out.println("status code retured = " + con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("data");
		// if (nn.size() == 0)
		// return "error no item found";
		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			
			String parent_id=parameterNode.findValue("parent_id").asText();
			id_returned = parameterNode.findValue("id").asText();
			String name=parameterNode.findValue("name").asText();
//			System.out.println("parent id found will be = "+parent_id);
//			System.out.println("node id sent will be = "+id);
//			System.out.println("node id will be = "+id_returned);
//			
//			
//			System.out.println("name will be = "+name);
//			System.out.println("node will be = "+node);
			if(name.equals(node))
			{
				
					if(parent_id.equals(id))
						return true;
				
			}
			
			
			
			
			
		else 
				continue;

		}

		return false;
	}

	public boolean check_path_pattern(String path) {

		// String res = check_path(path);
		// if (res.equals("error"))
		// return false;
		// else
		return true;

		// String[] arr = path.split("/");
		// String path_final = "";
		//
		// for (String s : arr)
		// path_final += s;
		//
		// //System.out.println("path final = " + path_final);
		//
		// for (int i = 0; i < path_final.length(); i++) {
		//
		// char x = path_final.charAt(i);
		// if (x == ' ')
		// continue;
		// if (!(x >= 'a' && x <= 'z') && !(Character.UnicodeBlock.of(x) ==
		// Character.UnicodeBlock.ARABIC)
		// && !(x >= 'A' && x <= 'Z'))
		// return false;
		//
		// }
		// return true;

	}

	public void URL() throws MalformedURLException {
		List<String> data = get_data();
		URL AuthenticationUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/Authentication?wsdl");
		// System.out.println(AuthenticationUrl.toString());
		// javax.xml.namespace.QName QNameAuthentication = new
		// javax.xml.namespace.QName(
		// "urn:authws.services.ecm.opentext.com", "AuthenticationService");
		// //System.out.println("befor");
		AuthenticationService tt = new AuthenticationService(AuthenticationUrl);

		URL CollaborationUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/Collaboration?wsdl");
		// javax.xml.namespace.QName QNameCollaboration = new javax.xml.namespace.QName(
		// "urn:Collaboration.service.livelink.opentext.com", "Collaboration");
		Collaboration_Service tt1 = new Collaboration_Service(CollaborationUrl);

		URL AdminServiceUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/AdminService?wsdl");
		// javax.xml.namespace.QName QNameAdminService = new javax.xml.namespace.QName(
		// "urn:Core.service.livelink.opentext.com", "AdminService");
		AdminService_Service tt2 = new AdminService_Service(AdminServiceUrl);

		URL Authentication_serviceUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/Authentication?wsdl");
		// javax.xml.namespace.QName QNameAuthentication_service = new
		// javax.xml.namespace.QName(
		// "urn:Core.service.livelink.opentext.com", "Authentication");
		Authentication_Service tt3 = new Authentication_Service(Authentication_serviceUrl);

		URL Content_serviceUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/ContentService?wsdl");
		// javax.xml.namespace.QName QNameContent_service = new
		// javax.xml.namespace.QName(
		// "urn:Core.service.livelink.opentext.com", "ContentService");
		ContentService_Service tt4 = new ContentService_Service(Content_serviceUrl);

		URL DocumentManagement_ServiceUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/DocumentManagement?wsdl");
		// javax.xml.namespace.QName QNameDocumentManagement_Service = new
		// javax.xml.namespace.QName(
		// "urn:DocMan.service.livelink.opentext.com", "DocumentManagement");
		DocumentManagement_Service tt5 = new DocumentManagement_Service(DocumentManagement_ServiceUrl);

		URL MemberService_ServiceUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/MemberService?wsdl");
		// javax.xml.namespace.QName QNameMemberService_Service = new
		// javax.xml.namespace.QName(
		// "urn:MemberService.service.livelink.opentext.com", "MemberService");
		MemberService_Service tt6 = new MemberService_Service(MemberService_ServiceUrl);

		URL SearchService_ServiceUrl = new URL(
				data.get(1) + "://" + data.get(0) + ":" + data.get(2) + "/cws/services/SearchService?wsdl");
		// javax.xml.namespace.QName QNameSearchService_Service = new
		// javax.xml.namespace.QName(
		// "urn:SearchServices.service.livelink.opentext.com", "SearchService");
		SearchService_Service tt7 = new SearchService_Service(SearchService_ServiceUrl);

	}

	public List<String> get_data() {
		List<String> data = new ArrayList<String>();
		Properties prop = new Properties();
		InputStream input = null;
		String result = "";

		try {
			String currentRelativePath = System.getProperty("catalina.base");
			input = new FileInputStream(currentRelativePath + "/ot_wsdls_config.properties");

			// load a properties file
			prop.load(input);
			// get the property value and print it out
			data.add(prop.getProperty("hostname"));
			data.add(prop.getProperty("protocol"));
			data.add(prop.getProperty("port"));
			return data;

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	public List<String> get_data_2() {
		List<String> data = new ArrayList<String>();
		Properties prop = new Properties();
		InputStream input = null;
		String result = "";

		try {
			String currentRelativePath = System.getProperty("catalina.base");
			input = new FileInputStream(currentRelativePath + "/ot_wsdls_config.properties");

			// load a properties file
			prop.load(input);
			// get the property value and print it out
			data.add(prop.getProperty("cs_protocol"));
			data.add(prop.getProperty("cs_host"));
			data.add(prop.getProperty("cs_port"));

			return data;

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	public String test_return(String cat_name) {
		List<String> data = new ArrayList<String>();
		Properties prop = new Properties();
		InputStream input = null;
		String result = "";

		try {
			String currentRelativePath = System.getProperty("catalina.base");
			input = new FileInputStream(currentRelativePath + "/categories.properties");

			// load a properties file
			prop.load(input);
			// get the property value and print it out
			cat_name = cat_name.replaceAll("\\s", "");
			System.out.println(cat_name);
			return (prop.getProperty(cat_name));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "error";
	}

	public boolean check_document_exist(String folder_id, String username, String password, String doc_name)
			throws IOException {

		String id = get_nodes_hash(folder_id, authenticate_rest(username, password), doc_name);
		if (!(id.equals("")))
			return true;
		else
			return false;
	}

	public String get_node_2(String nodeid) {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		Node n = docManClient.getNode(Long.parseLong(nodeid));
		if (n == null) {
			return "error";
		}
		return "found";
	}

	public String update_category(String nodeid, String cat_values, String cat_id, String cat_name)
			throws IOException, DatatypeConfigurationException, ParseException {

		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docManClient = docService.getBasicHttpBindingDocumentManagement();

		try {
			setSoapHeader((WSBindingProvider) docManClient);
		} catch (SOAPException e) {
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		int cat_size = 0;
		List<String> cat_attr_names = new ArrayList<String>();
		HashMap<String, String> hash = new HashMap<String, String>();
		HashMap<String, String> hash2 = new HashMap<String, String>();
		String[] arrr;
		List<Attribute> ft = get_cat_by_id(cat_id);
		cat_size = ft.size();
		boolean found2 = false;
		System.out.println("cat size = " + cat_size);

		// preparation for multi value field .
		List<String> list = new ArrayList<String>();
		list = chech_if_append_or_override(cat_values);
		if (list.contains("true")) {
			found2 = true;
			arrr = list.get(1).split("=|~|;");
		}

		else
			arrr = cat_values.split("=|~|;");

		// List<String> required_atrr = new ArrayList<String>();
		// for (Attribute ft1 : ft) {
		// if (ft1.isRequired())
		// required_atrr.add(ft1.getDisplayName());
		// }

		for (Attribute t : ft) {
			String atr = t.getDisplayName();
			atr = atr.replaceAll("\\s", "");
			atr = atr.toLowerCase();
			cat_attr_names.add(atr);
			hash.put(atr, t.getType());
			String[] arr4 = t.getKey().split("[.]");
			System.out.println("name = " + atr + "----" + "index = " + arr4[2]);
			hash2.put(atr, arr4[2]);
		}

		for (int i = 0; i < arrr.length; i++) {
			String str = arrr[i];
			str = str.replaceAll("\\s", "");
			str = str.toLowerCase();
			System.out.println("inside loop that preform = " + str);
			if (cat_attr_names.contains(str)) {
				System.out.println("inside if that form");
				arrr[i] = str;
				// continue;
			}

		}
		// List<String> check_if_required = new ArrayList<String>();
		// for (String rr : arrr) {
		// if (cat_attr_names.contains(rr)) {
		// check_if_required.add(rr);
		// }
		// }

		// if (check_if_required.size() < required_atrr.size())
		// return "error required";
		boolean check = check_cat_name(cat_attr_names, cat_values);

		List<String> check_list = new ArrayList<String>();

		// check_list = convert_array_to_list(arrr);
		String result = "";
		if (check) {
			System.out.println("valid");
			String type = "";
			int index = -1;
			int index_3 = 0;
			boolean found = false;
			String name = "";
			int counter = 0;
			Stack<String> stack = new Stack<String>();
			for (String t : cat_attr_names)
				System.out.println("cat_attr_names = " + t);

			for (int t = 0; t < arrr.length; t++)
				System.out.println("arrr values are = " + arrr[t]);

			for (int i = 0; i < arrr.length; i++) {

				System.out.println("value that will iterated on = " + arrr[i]);
				String str = arrr[i];
				str = str.replaceAll("\\s", "");
				str = str.toLowerCase();
				if (cat_attr_names.contains(arrr[i]) && found) {
					result = update_cat_val_2(name, stack, index, type, nodeid, cat_name, found2);
					if (result.equals("error more values"))
						return "error more values";
					type = hash.get(arrr[i]);
					found = true;
					name = arrr[i];
					// index = Integer.parseInt(hash2.get(arrr[i]));
					index = Integer.parseInt(hash2.get(str));

				} else if (cat_attr_names.contains(arrr[i])) {
					System.out.println("inside first if");
					counter++;
					found = true;
					name = arrr[i];
					type = hash.get(arrr[i]);
					// index = Integer.parseInt(hash2.get(arrr[i]));
					index = Integer.parseInt(hash2.get(str));
				} else {
					counter++;
					System.out.println("inside third if");
					if ((i + 1) == arrr.length) {
						stack.push(arrr[i]);
						result = update_cat_val_2(name, stack, index, type, nodeid, cat_name, found2);
						if (result.equals("error more values"))
							return "error more values";
					}
					stack.push(arrr[i]);
				}
			}

		}
		return "";
	}

	private List<String> chech_if_append_or_override(String cat_values) {
		boolean found = false;
		char x;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < cat_values.length(); i++) {
			x = cat_values.charAt(i);
			if (x == '=') {
				if (cat_values.charAt(i + 1) == '~') {
					found = true;
					StringBuilder str = new StringBuilder(cat_values);
					str.deleteCharAt(i + 1);
					list.add("true");
					list.add(str.toString());

				}

			}
		}

		if (found)
			return list;
		else {
			list.add("not_append");
			return list;
		}

	}

	public String get_volume(String username, String password) throws IOException {
		List<String> data_list = new ArrayList<String>();
		String ticket = authenticate_rest(username, password);
		String id = "";
		data_list = get_data_2();
		URL url = new URL(data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2)
				+ "/otcs/cs.exe/api/v2/configurationvolumes");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.setRequestProperty("OTCSTicket", ticket);
		con.getInputStream();
		System.out.println(con.getResponseCode());
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader buff = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output = "";
		String result = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
		System.out.println(result);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result);
		JsonNode nn = jsonf.get("results");
		System.out.println("node = " + nn);

		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			id = parameterNode.findValue("id").asText();
			System.out.println("id = " + id);

		}
		return id;
	}

	public String createworkspacejson(String folder_id, String cat_name, String cat_values, String ws_name,
			String Template_id) {
		Map map_cat = new HashMap();
		// Map m = new LinkedHashMap();

		int counter = 0;
		String name = "", val = "", type = "";
		String[] arr;
		String json = "", json1 = "", json_end = "", json_final = "", index = "";
		HashMap<String, String> hash = new HashMap<String, String>();
		HashMap<String, String> hash_type = new HashMap<String, String>();
		HashMap<String, String> hash_required = new HashMap<String, String>();
		List<String> cat_attr_names = new ArrayList<String>();
		boolean found = false;
		boolean date_found = false;
		String id = search_cat(cat_name);
		List<Attribute> ft = get_cat_by_id(id);

		List<String> required_atrr = new ArrayList<String>();
		for (Attribute ft1 : ft) {
			String name_ = ft1.getDisplayName();
			// name_ = name_.replaceAll("\\s", "");
			// name_ = name_.toLowerCase();
			if (ft1.isRequired()) {
				required_atrr.add(name_);
				arr = ft1.getKey().split("[.]");
				// System.out.println("hash that hold the index = " + name_ + "--" + arr[2]);
				hash_required.put(name_, arr[2]);
				hash.put(name_, arr[2]);
				hash_type.put(name_, ft1.getType());
				cat_attr_names.add(name_);
			} else {
				cat_attr_names.add(name_);
				arr = ft1.getKey().split("[.]");
				hash.put(name_, arr[2]);
				hash_type.put(name_, ft1.getType());

			}
		}

		String[] arrr = cat_values.split("=|~|;");

		// for (String str : cat_attr_names)
		// System.out.println("cat atr name = " + str);
		// check if all required attributes is entered .

		boolean check = check_cat_name(cat_attr_names, cat_values);
		if (check) {

			// for (String tt : cat_attr_names) {
			// System.out.println("===============");
			// System.out.println(tt);
			// System.out.println("===============");
			// }

			for (int i = 0; i < arrr.length; i++) {

				String strrr = arrr[i];
				// strrr = strrr.replaceAll("\\s", "");
				// strrr = strrr.toLowerCase();

				// System.out.println("arrr[i] = " + arrr[i]);
				if (cat_attr_names.contains(strrr)) {
					// System.out.println("done with arrr[i] = " + arrr[i]);
					if (hash_required.get(strrr) != null)
						++counter;
				}

			}
			// System.out.println("hash size = " + hash_required.size() + " counter = " +
			// counter);
			if (hash_required.size() == counter) {
				// System.out.println("the data is entered fine .");

				for (int i = 0; i < arrr.length; i++) {
					String strrr = arrr[i];
					// strrr = strrr.replaceAll("\\s", "");
					// strrr = strrr.toLowerCase();
					// System.out.println("value that will iterated on = " + arrr[i]);
					// System.out.println("value that will iterated on 2 = " + strrr);

					if (cat_attr_names.contains(strrr)) {
						// System.out.println("------------------");
						// System.out.println("this item is atrr name = " + strrr);
						// System.out.println("------------------");
						found = true;
						name = strrr;
						type = hash_type.get(strrr);
						index = hash.get(strrr);
						// System.out.println("$$$$$$$$ type will be = " + type);
						if (type.equals("Date"))
							date_found = true;

					}

					else {
						// System.out.println("inside this this");

						if (i + 1 == arrr.length) {
							if (date_found) {
								// System.out.println("inside inside inside");
								String[] arr_ = arrr[i].split("/");
								val = arr_[2] + "-" + arr_[1] + "-" + arr_[0];
								json += "\"" + id + "_" + index + "\"" + ":" + "\"" + val + "\"";
								// System.out.println("json will be = " + json);
								date_found = false;
							} else {
								json += "\"" + id + "_" + index + "\"" + ":" + "\"" + arrr[i] + "\"";
								// System.out.println("json will be = " + json);
							}

						} else {
							if (date_found) {
								// System.out.println("inside inside inside inside");
								String[] arr_ = arrr[i].split("/");
								val = arr_[2] + "-" + arr_[1] + "-" + arr_[0];
								json += "\"" + id + "_" + index + "\"" + ":" + "\"" + val + "\"" + ",";
								// System.out.println("json will be = " + json);
								date_found = false;
							} else {
								json += "\"" + id + "_" + index + "\"" + ":" + "\"" + arrr[i] + "\"" + ",";
								// System.out.println("json will be = " + json);
							}

						}

					}
				}
				json1 = "{" + "\"" + id + "\"" + ":";
				json_final = "{\"template_id\":" + "\"" + Template_id + "\"" + ",\"name\":" + "\"" + ws_name + "\""
						+ ",\"parent_id\":" + "\"" + folder_id + "\""
						+ ",\"description\": \"\",\"roles\":{\"categories\":" + json1 + "{" + json + "}}}}";

			} else
				return "400 Required Values are Missing";

		}

		return json_final;

	}

	public String get_document_template_id(String ticket, String ws_type) throws IOException {
		List<String> data_list = new ArrayList<String>();
		String id = "", name = "";
		ws_type = ws_type.replaceAll("\\s", "");
		ws_type = ws_type.toLowerCase();
		data_list = get_data_2();
		URL url = new URL(data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2)
				+ "/otcs/cs.exe/api/v2/businessworkspacetypes?expand_templates=true");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.setRequestProperty("OTCSTicket", ticket);
		con.getInputStream();
		System.out.println(con.getResponseCode());
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader buff = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output = "";
		String result = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
		System.out.println(result);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result);
		System.out.println("complete output will be = " + result);
		JsonNode nn = jsonf.get("results");
		System.out.println("node = " + nn);

		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			name = parameterNode.findValue("name").asText();
			name = name.replaceAll("\\s", "");
			name = name.toLowerCase();

			if (name.equals(ws_type)) {
				id = parameterNode.findValue("id").asText();
				System.out.println("id = " + id);
				return id;
			}

		}
		return id;

	}

	private String recursion(List<Node> list, String ws_type) {

		for (Node n : list) {
			String name = n.getName().replaceAll("\\s", "");
			name = name.toLowerCase();
			ws_type = ws_type.replaceAll("\\s", "");
			ws_type = ws_type.toLowerCase();
			if (name.equals(ws_type))
				return String.valueOf(n.getID());
		}
		return "";

	}

	public String createworkspace(String ticket, String result) throws IOException {
		List<String> data_list = new ArrayList<String>();
		data_list = get_data_2();
		String id = "";
		System.out.println("json = " + result);
		String data2 = "Body=" + URLEncoder.encode(result, StandardCharsets.UTF_8.toString());
		byte[] postdata = data2.getBytes(StandardCharsets.UTF_8);
		URL url2 = new URL(data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2)
				+ "/otcs/cs.exe/api/v2/businessworkspaces/");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con2.setRequestProperty("charset", "utf-8");
		con2.setRequestProperty("Content-Length", Integer.toString(postdata.length));
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("POST");
		con2.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con2.getOutputStream())) {
			wr.write(postdata);
		} catch (Exception e) {
			System.out.println("error happened in server");
		}
		if (con2.getResponseCode() == 200) {
			String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
			JsonNode jsonf = null;
			ObjectMapper mapperr = new ObjectMapper();
			jsonf = mapperr.readTree(result3);
			JsonNode nn = jsonf.get("results");
			id = nn.findValue("id").toString();
			return id;

		} else if (con2.getResponseCode() == 400)
			return "400 Bad Request - Missing Parameters";
		else if (con2.getResponseCode() == 500)
			return "Can't create workspace";
		return id;
	}

	public boolean check_if_exist(String folder_id, String ws_name, String username, String password)
			throws IOException {

		boolean found = get_nodes_hash_2(folder_id, authenticate_rest(username, password), ws_name);
		//System.out.println("found value will be = "+found);
			if (found)
				return true;
			else
				return false;
		
		
		
	}

	public String search_api_cat_test(String ticket, String cat_name) throws IOException {
		boolean found = false;
		List<String> data_list = new ArrayList<String>();
		data_list = get_data_2();
		int counter = 0;
		String search_query = "where=OTSubType:131 and OTName:" + cat_name;
		// System.out.println("search query = " + search_query);
		String cat_name_ = " ";
		String data2 = search_query;
		byte[] postdata = data2.getBytes(StandardCharsets.UTF_8);
		URL url2 = new URL(
				data_list.get(0) + "://" + data_list.get(1) + ":" + data_list.get(2) + "/otcs/cs.exe/api/v2/search");
		// System.out.println("search url will be = " + data_list.get(0) + "://" +
		// data_list.get(1) + ":"
		// + data_list.get(2) + "/otcs/cs.exe/api/v2/search");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con2.setRequestProperty("charset", "utf-8");
		con2.setRequestProperty("Content-Length", Integer.toString(postdata.length));
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("POST");
		con2.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con2.getOutputStream())) {
			wr.write(postdata);
		} catch (Exception e) {
			System.out.println("error happened in server");
		}
		// System.out.println("status code retured = " + con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("results");
		if (nn.size() == 0)
			return "error no item found";
		Iterator<JsonNode> iter = nn.iterator();
		while (iter.hasNext()) {
			JsonNode parameterNode = (JsonNode) iter.next();
			cat_name_ = parameterNode.findValue("name").asText();
			if (!cat_name_.equals(" ")) {

				if (cat_name_.equals(cat_name)) {
					found = true;
					return (parameterNode.findValue("id").asText());
				}
			}

		}
		if (!found)
			return "error";
		return "error";

	}

	public List get_doc_by_id2_test(String doc_id, String version_no) throws IOException {
		List list = new ArrayList<>();
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
		// We need to manually set the SOAP header to include the authentication
		// token
		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			System.out.println("Failed to set authentication SOAP header!\n" + e.getMessage());
			System.out.println("Failed to set authentication SOAP header!\n");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		Node mm = docClient.getNode(Long.parseLong(doc_id));
		
		String name = mm.getName();
		String mime_type = mm.getVersionInfo().getMimeType();

		String n = docClient.getVersionContentsContext(Long.parseLong(doc_id), Long.parseLong(version_no));

		ContentService_Service contentService = new ContentService_Service();
		ContentService contentServiceClient = contentService.getBasicHttpBindingContentService(new MTOMFeature());
		try {
			setSoapHeader((WSBindingProvider) contentServiceClient);
		} catch (Exception e) {
			// _logger.error("Failed to set authentication SOAP header!\n" +
			// e.getMessage());
			// //System.out.println("Failed to set authentication SOAP
			// header!\n");
			// //System.out.println(e.getMessage());
			// //System.out.println(e.getStackTrace());
		}

		StreamingDataHandler downloadStream = null;

		// Call the downloadContent() method to download the file
		try {
			System.out.print("Downloading file...");
			downloadStream = (StreamingDataHandler) contentServiceClient.downloadContent(n);
			InputStream in = downloadStream.getInputStream();
			byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(in);
			// System.out.println("byte array = " + byteArray);

			// File file = new File("");
			// OutputStream os = new FileOutputStream(file);
			// os.write(byteArray);
			// os.close();
			// System.out.println("length = " + byteArray.length);
			// return byteArray;
			String encode = Base64.getEncoder().encodeToString(byteArray);
			// list.add("{");
			list.add("Document_Content:" + encode);
			list.add("Document_Name:" + name);
			list.add("Document _Mimetype:" + mime_type);
			// list.add("}");
			return list;
		} catch (SOAPFaultException e) {
			System.out.println("FAILED!\n");
			System.out.println(e.getFault().getFaultCode() + " : " + e.getMessage());
			list.add(e.getMessage());
			 return list;
		}

		// Stream the download to the local file path
//		try {
			// File file = new File("C:/test.pdf");
			// downloadStream.moveTo(file);
			// //System.out.println("SUCCESS!\n");
			// //System.out.println("Downloaded " + file.length() + " bytes to " +
			// ".\n");
			//
			// ByteArrayOutputStream output = new ByteArrayOutputStream();
			// downloadStream.writeTo(output);
			// //System.out.println("output size = " + output.size());
			// return output.toByteArray();
		 catch (Exception e) {
			System.out.println("Failed to download file!\n");
			System.out.println(e.getMessage());
			list.add(e.getMessage());
			 return list;}

	}

	public void CreateXml(String ticket) throws IOException, ParserConfigurationException, TransformerException {

		List<String> list_id = new ArrayList<String>();
		List<String> list_name = new ArrayList<String>();
		List<String> list_filtered = new ArrayList<String>();
		List<String> list_data = new ArrayList<String>();

		HashMap<String, String> hash = new HashMap<String, String>();
		int counter = 0;
		// String search = "where=OTSubType : 144 and (OTModifyDate : 20200307 or
		// OTModifyDate :> 20200307) ";
		String search = "where=OTSubType : 131" + "&limit=" + Integer.MAX_VALUE;
		// search += "and limit=100";
		System.out.println("search query = " + search);
		String id = " ";
		String data2 = search;
		byte[] postdata = data2.getBytes(StandardCharsets.UTF_8);
		list_data = get_data();
		URL url2 = new URL("http://" + list_data.get(0) + "/otcs/cs.exe/api/v2/search");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con2.setRequestProperty("charset", "utf-8");
		con2.setRequestProperty("Content-Length", Integer.toString(postdata.length));
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("POST");
		con2.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con2.getOutputStream())) {
			wr.write(postdata);
		} catch (Exception e) {
			System.out.println("error happened in server");
		}
		System.out.println("status code retured = " + con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("results");
		// if (nn.size() == 0)
		System.out.println("error no item found");
		System.out.println("nn = " + nn);
		Iterator<JsonNode> iter = nn.iterator();

		String currentRelativePath = System.getProperty("catalina.base");
		currentRelativePath += "/categories.properties";

		PrintWriter writer = new PrintWriter(currentRelativePath, "UTF-8");
		// writer.println("The first line=" + "one");
		// writer.println("The second line" + "two");
		// writer.close();

		// DocumentBuilderFactory documentFactory =
		// DocumentBuilderFactory.newInstance();
		//
		// DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		//
		// Document document = documentBuilder.newDocument();
		//
		// // root element
		// Element root = document.createElement("categories");
		// document.appendChild(root);
		//
		// // employee element
		// Element category = document.createElement("category");

		// root.appendChild(category);
		// Properties prop = new Properties();
		// OutputStream output = null;
		String name = "";
		while (iter.hasNext())

		{
			JsonNode parameterNode = (JsonNode) iter.next();
			name = parameterNode.findValue("name").asText();
			name = name.replaceAll("\\s", "");
			writer.println(name + "=" + parameterNode.findValue("id").asText());
			// if (list_name.contains(parameterNode.findValue("name").asText()))
			// continue;
			// else {
			// list_name.add(parameterNode.findValue("name").asText());
			// System.out.println("name will be = " +
			// parameterNode.findValue("name").asText());
			// Element category_name = document.createElement("category_name");
			// category_name.appendChild(document.createTextNode(parameterNode.findValue("name").asText()));
			// category.appendChild(category_name);
			//
			// }
			// if (list_id.contains(parameterNode.findValue("id").asText()))
			// continue;
			// else {
			// list_id.add(parameterNode.findValue("id").asText());
			// System.out.println("id will be = " + parameterNode.findValue("id").asText());
			//
			// Element category_id = document.createElement("category_id");
			// category_id.appendChild(document.createTextNode(parameterNode.findValue("id").asText()));
			// category.appendChild(category_id);
			// }
			// TransformerFactory transformerFactory = TransformerFactory.newInstance();
			// Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			// DOMSource domSource = new DOMSource(document);
			// StreamResult streamResult = new StreamResult(new File(currentRelativePath));
			// transformer.transform(domSource, streamResult);

		}
		writer.close();

	}

	public String get_catogory_id_xml(String cat_name) throws ParserConfigurationException, SAXException, IOException {

		// System.out.println("inside new function");
		String currentRelativePath = System.getProperty("catalina.base");
		currentRelativePath += "/CreateXml.xml";
		File file = new File(currentRelativePath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		Element root = doc.getDocumentElement();
		// System.out.println("root element will be = " + root.getNodeName());

		NodeList list = doc.getElementsByTagName("category");
		int j = 1;
		String attr1 = "", attr2 = "", attr = "attr";
		String val = "";
		List<String> ll = new ArrayList<String>();
		List<String> ll2 = new ArrayList<String>();
		// System.out.println("length will be = " + list.getLength());
		int counter = 0;

		List<String> llist = new ArrayList<String>();

		// fill our list with desired category only

		// System.out.println("side befor loop = " + list.getLength());

		for (int i = 0; i < list.getLength(); i++) {
			org.w3c.dom.Node node = list.item(i);
			// System.out.println("");
			cat_name = cat_name.trim();
			if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				NodeList nood = eElement.getChildNodes();
				for (int k = 0; k < nood.getLength(); k++) {
					// System.out.println("counter inside loop will be = " + counter);
					// if (nood.item(k).getNodeName().toString().trim().equals("category_name"))
					// llist.add(nood.item(k).getTextContent().toString().trim());
					// System.out.println("item that will be = " +
					// nood.item(k).getNodeName().toString().trim());

					val = nood.item(k).getTextContent().toString().trim();
					// System.out.println("val will be = " + val);
					if (val.equals(""))
						continue;

					else if (nood.item(k).getNodeName().toString().trim().equals("category_name")) {
						if (val.equalsIgnoreCase(cat_name))
							++counter;
						else if (counter == 1)
							break;

					}

					else if (counter == 1) {
						// System.out.println("cat id will be = " + val);
						return val;
					}

				}

			}
		}
		return "error";

	}

	public String get_template_id_xml(String template_name)
			throws ParserConfigurationException, SAXException, IOException {
		// System.out.println("template name will be = " + template_name);
		// System.out.println("inside new function");
		String currentRelativePath = System.getProperty("catalina.base");
		currentRelativePath += "/CreateDocumentTemplate.xml";
		File file = new File(currentRelativePath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		Element root = doc.getDocumentElement();
		// System.out.println("root element will be = " + root.getNodeName());

		NodeList list = doc.getElementsByTagName("Document_Template");
		int j = 1;
		String attr1 = "", attr2 = "", attr = "attr";
		String val = "";
		List<String> ll = new ArrayList<String>();
		List<String> ll2 = new ArrayList<String>();
		// System.out.println("length will be = " + list.getLength());
		int counter = 0;

		List<String> llist = new ArrayList<String>();

		// fill our list with desired category only

		// System.out.println("side befor loop = " + list.getLength());

		for (int i = 0; i < list.getLength(); i++) {
			org.w3c.dom.Node node = list.item(i);
			// System.out.println("");
			// template_name = template_name.trim();
			if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				NodeList nood = eElement.getChildNodes();
				for (int k = 0; k < nood.getLength(); k++) {
					// System.out.println("counter inside loop will be = " + counter);
					// if (nood.item(k).getNodeName().toString().trim().equals("category_name"))
					// llist.add(nood.item(k).getTextContent().toString().trim());
					// System.out.println("item that will be = " +
					// nood.item(k).getNodeName().toString().trim());

					val = nood.item(k).getTextContent().toString().trim();
					// System.out.println("val will be = " + val);
					if (val.equals(""))
						continue;

					else if (nood.item(k).getNodeName().toString().trim().equals("template_name")) {
						if (val.equalsIgnoreCase(template_name))
							++counter;
						else if (counter == 1)
							break;

					}

					else if (counter == 1) {
						// System.out.println("template id will be = " + val);
						return val;
					}

				}

			}
		}
		return "error";

	}

	public void CreateTemplateXml(String ticket)
			throws IOException, ParserConfigurationException, TransformerException {

		List<String> list_id = new ArrayList<String>();
		List<String> list_name = new ArrayList<String>();
		List<String> list_filtered = new ArrayList<String>();
		List<String> list_data = new ArrayList<String>();

		HashMap<String, String> hash = new HashMap<String, String>();
		int counter = 0;
		String id = " ";
		list_data = get_data();
		URL url2 = new URL(
				"http://" + CS_IP + "/otcs/cs.exe/api/v2/businessworkspacetypes?expand_templates=true");
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
		con2.setRequestProperty("OTCSTicket", ticket);
		con2.setUseCaches(false);
		con2.setInstanceFollowRedirects(false);
		con2.setRequestMethod("GET");
		con2.setDoOutput(true);
		System.out.println("status code retured = " + con2.getResponseCode());
		String result3 = IOUtils.toString(con2.getInputStream(), StandardCharsets.UTF_8);
		JsonNode jsonf = null;
		ObjectMapper mapperr = new ObjectMapper();
		jsonf = mapperr.readTree(result3);
		JsonNode nn = jsonf.get("results");
		 if (nn.size() == 0)
		System.out.println("error no item found");
		System.out.println("nn = " + nn);
		Iterator<JsonNode> iter = nn.iterator();

		String currentRelativePath = System.getProperty("catalina.base");
		currentRelativePath += "/CreateDocumentTemplate.xml";

		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		// root element
		Element root = document.createElement("Document_Templates");
		document.appendChild(root);

		// employee element
		Element Document_Template = document.createElement("Document_Template");

		root.appendChild(Document_Template);

		while (iter.hasNext())

		{

			
			JsonNode parameterNode = (JsonNode) iter.next();

			if( parameterNode.findValue("name")!=null)
				
			{
				if (list_name.contains(parameterNode.findValue("name").asText()))
					continue;
				else {
					
					list_name.add(parameterNode.findValue("name").asText());
					System.out.println("name will be = " + parameterNode.findValue("name").asText());
					Element template_name = document.createElement("template_name");
					template_name.appendChild(document.createTextNode(parameterNode.findValue("name").asText()));
					Document_Template.appendChild(template_name);

				}
				if (list_id.contains(parameterNode.findValue("id").asText()))
					continue;
				else {
					list_id.add(parameterNode.findValue("id").asText());
					System.out.println("id will be = " + parameterNode.findValue("id").asText());

					Element template_id = document.createElement("template_id");
					template_id.appendChild(document.createTextNode(parameterNode.findValue("id").asText()));
					Document_Template.appendChild(template_id);
				}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();

				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult = new StreamResult(new File(currentRelativePath));
				transformer.transform(domSource, streamResult);

			}
				
			
		}

		// URL url = new
		// URL("http://localhost:80/otcs/cs.exe/api/v2/businessworkspacetypes?expand_templates=true");
		// HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// con.setRequestMethod("GET");
		// con.setDoOutput(true);
		// con.setRequestProperty("OTCSTicket", ticket);
		// con.getInputStream();
		// //System.out.println(con.getResponseCode());
		// StringBuilder stringBuilder = new StringBuilder();
		// BufferedReader buff = new BufferedReader(new
		// InputStreamReader(con.getInputStream()));
		// String output = "";
		// String result = IOUtils.toString(con.getInputStream(),
		// StandardCharsets.UTF_8);
		// //System.out.println(result);
		// JsonNode jsonf = null;
		// ObjectMapper mapperr = new ObjectMapper();
		// jsonf = mapperr.readTree(result);
		// //System.out.println("complete output will be = " + result);
		// JsonNode nn = jsonf.get("results");
		// //System.out.println("node = " + nn);
		//
		// Iterator<JsonNode> iter = nn.iterator();
		//
		// String currentRelativePath = System.getProperty("catalina.base");
		// currentRelativePath += "/CreateDocumentTemplate.xml";
		//
		// DocumentBuilderFactory documentFactory =
		// DocumentBuilderFactory.newInstance();
		//
		// DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		//
		// Document document = documentBuilder.newDocument();
		//
		// // root element
		// Element root = document.createElement("Document Templates");
		// document.appendChild(root);
		//
		// // employee element
		// Element category = document.createElement("Document Template");
		//
		// root.appendChild(category);
		//
		// while (iter.hasNext()) {
		// JsonNode parameterNode = (JsonNode) iter.next();
		// //System.out.println("name = " + parameterNode.findValue("name").asText());
		// //System.out.println("id = " + parameterNode.findValue("id").asText());
		// // name = parameterNode.findValue("name").asText();
		// // name = name.replaceAll("\\s", "");
		// // name = name.toLowerCase();
		//
		// // if (name.equals(ws_type)) {
		// // id = parameterNode.findValue("id").asText();
		// // //System.out.println("id = " + id);
		// // return id;
		// // }
		//
		// }
		// return id;
	}

	

	public Long createfolder_2(String authToken, long parent_id, String folder_name,String cat_id, String cat_val) throws NumberFormatException, IOException, DatatypeConfigurationException, ParseException, ParserConfigurationException, SAXException {

		 Metadata m = get_cat_data(cat_id, cat_val);
		Node nod = null;
		long lon = 0L;
		long id = 1;
		DocMngmtTuple rv = createDocManClient(authToken);
		DocumentManagement docManClient = rv.docManagement;

		try {
			System.out.print("Creating Folder...");
			nod = docManClient.createFolder(parent_id, folder_name, "", m);
			lon = nod.getID();

		} catch (SOAPFaultException e) {
			// //System.out.println("FAILED!\n");
			// //System.out.println(e.getFault().getFaultCode() + " : " +
			// e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lon;

	}

	public boolean check_new(List<Node> child, String subject) {

		for (Node n : child) {
			if (n.getName().equals(subject))
				return true;

		}
		return false;

	}

	public Long get_id_new(List<Node> child, String subject) {

		for (Node n : child) {
			if (n.getName().equals(subject))
				return n.getID();

		}
		return 0L;
	}
	
	public String auth_rest(String username, String password) throws IOException
	{
		
		String ticket = "";
		String data = "username="+username+"&password="+password+"";
		
		//URL url = new URL("http://" + CS_IP + "/otcs/cs.exe/api/v1/auth");
		URL url = new URL("http://" + CS_IP + "/otcs/cs.exe/api/v1/auth");
		//URL url = new URL("http://" + CS_IP + "/otcs/cs.exe/api/v1/auth");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
		
		
		// System.out.println(con.getResponseCode());
		if(con.getResponseCode()==200) {
			con.getInputStream();
			System.out.println("inside if");
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader buff = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output = "";
		String result = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
		JSONObject json = new JSONObject(result);
		ticket = (String) json.get("ticket");
		return ticket;

		}
		else {
			System.out.println("inside else");
			return "error";
		}
		
	}
	public String deletenode(String Doc_id, String ticket) throws IOException {
		URL url = new URL ("http://" + CS_IP + "/otcs/cs.exe/api/v2/nodes/"+Doc_id+"");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("OTCSTicket", ticket);
		

		con.setDoOutput(true);

		int code = con.getResponseCode();
		if (code!=200) {
			return "error";
		}
		
		else{return "done";}
//		System.out.println(code);
	//	
//		con.getInputStream();
//		String results = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
	//
//		JSONObject obj = new JSONObject(results);
	//System.out.println(obj);
	//	
//		return code;
		
		
		
	}
	public String deletenodeVer(String Doc_id, String Version_no, String ticket) throws IOException {
		URL url = new URL ("http://" + CS_IP + "/otcs/cs.exe/api/v2/nodes/"+Doc_id+"/versions/"+Version_no+"");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("OTCSTicket", ticket);
		

		con.setDoOutput(true);

		int code = con.getResponseCode();
		if (code!=200) {
			return "error";
		}
		
		else{return "done";}}

	public String uploadFile_version(String authToken, long id, String fileID, String title, byte[] fileContent,
			boolean overwriteFlag, boolean verifyFlag, Metadata m, String doc_extension) throws IOException {
		DocMngmtTuple rv = createDocManClient(authToken);
		DocumentManagement docManClient = rv.docManagement;
		OTAuthentication otAuth = rv.OTAuth;

		// //System.out.println("done till here");
		// //System.out.println(title);
		if ((!verifyFlag) && (docManClient.getNode(344L) == null)) {
			// //System.out.println("doesnt exist");
			return "1";
		}
		if ((verifyFlag) && (docManClient.getNode(344L) != null)) {
			// //System.out.println("already exists");
			return "0";
		}

		File file = null;

		String str1 = title;
		String str2 = doc_extension;

		FileOutputStream fo = null;
		try {
			// String[] arr = str1.split("[.]");
			file = File.createTempFile(str1, "." + str2, null);
			// file = File.createTempFile(str1, null);
			fo = new FileOutputStream(file);
			fo.write(fileContent);
			fo.close();

		} catch (IOException e) {
			e.printStackTrace();

			try {
				fo.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		} finally {
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long PARENT_ID = id;
		String contextID = null;
		// //System.out.println("file content size = " + fileContent.length);
		try {
			System.out.print("Generating context ID...");
			String str = "";
			if ((!fileID.equals("")) && (fileID != null))
				str = "File ID:" + fileID + "\n";
			str = str + "Title:" + title;
			// contextID = docManClient.createDocumentContext(PARENT_ID, title,
			// "", false,
			// m);
			// contextID = docManClient.createNodeAndVersionContext();
			// docManClient.addVersion(id, null, null);
			contextID = docManClient.addVersionContext(id, null);
			System.out.println("SUCCESS!\n");
		} catch (SOAPFaultException e) {
			// //System.out.println("FAILED!\n");
			// //System.out.println(e.getFault().getFaultCode() + " : " +
			// e.getMessage());
			return "1";
		}
		ContentService_Service contentService = new ContentService_Service();
		ContentService contentServiceClient = contentService
				.getBasicHttpBindingContentService(new WebServiceFeature[] { new MTOMFeature() });
		int CHUNK_SIZE = 10240;
		((BindingProvider) contentServiceClient).getRequestContext()
				.put("com.sun.xml.ws.transport.http.client.streaming.chunk.size", Integer.valueOf(10240));

		BasicFileAttributes fileAttributes;
		try {
			fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]);
		} catch (IOException e) {
			// BasicFileAttributes fileAttributes;
			// //System.out.println("Failed to read file attributes!\n");
			// //System.out.println(e.getMessage());
			return "1";
		}

		// BasicFileAttributes fileAttributes;
		FileAtts fileAtts = new FileAtts();
		try {
			fileAtts.setCreatedDate(
					DatatypeFactory.newInstance().newXMLGregorianCalendar(fileAttributes.creationTime().toString()));
			fileAtts.setFileName(file.getName());
			fileAtts.setFileSize(Long.valueOf(file.length()));
			fileAtts.setModifiedDate(DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(fileAttributes.lastModifiedTime().toString()));
		} catch (DatatypeConfigurationException e) {
			// //System.out.println("Failed to set file attributes!\n");
			// //System.out.println(e.getMessage());
			return "1";
		}
		try {
			String ECM_API_NAMESPACE = "urn:api.ecm.opentext.com";
			String CORE_NAMESPACE = "urn:Core.service.livelink.opentext.com";

			SOAPHeader header = MessageFactory.newInstance().createMessage().getSOAPPart().getEnvelope().getHeader();

			SOAPHeaderElement otAuthElement = header
					.addHeaderElement(new QName("urn:api.ecm.opentext.com", "OTAuthentication"));

			SOAPElement authTokenElement = otAuthElement
					.addChildElement(new QName("urn:api.ecm.opentext.com", "AuthenticationToken"));
			authTokenElement.addTextNode(otAuth.getAuthenticationToken());

			SOAPHeaderElement contextIDElement = header
					.addHeaderElement(new QName("urn:Core.service.livelink.opentext.com", "contextID"));
			contextIDElement.addTextNode(contextID);

			SOAPHeaderElement fileAttsElement = header
					.addHeaderElement(new QName("urn:Core.service.livelink.opentext.com", "fileAtts"));

			SOAPElement createdDateElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "CreatedDate"));
			createdDateElement.addTextNode(fileAtts.getCreatedDate().toString());

			SOAPElement modifiedDateElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "ModifiedDate"));
			modifiedDateElement.addTextNode(fileAtts.getModifiedDate().toString());

			SOAPElement fileSizeElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "FileSize"));
			fileSizeElement.addTextNode(fileAtts.getFileSize().toString());

			SOAPElement fileNameElement = fileAttsElement
					.addChildElement(new QName("urn:Core.service.livelink.opentext.com", "FileName"));
			fileNameElement.addTextNode(fileAtts.getFileName());

			List<Header> headers = new ArrayList();
			headers.add(Headers.create(otAuthElement));
			headers.add(Headers.create(contextIDElement));
			headers.add(Headers.create(fileAttsElement));

			((WSBindingProvider) contentServiceClient).setOutboundHeaders(headers);
		} catch (SOAPException e) {
			// //System.out.println("Failed to set SOAP headers!\n");
			// //System.out.println(e.getMessage());
			return "1";
		}

		try {
			System.out.print("Uploading document...");
			String objectID = contentServiceClient.uploadContent(new DataHandler(new FileDataSource(file)));

			// //System.out.println("SUCCESS!\n");
			// //System.out.println("New document uploaded with ID = " +
			// objectID);
			return objectID;

		} catch (SOAPFaultException e) {

			// //System.out.println("FAILED!\n");
			// //System.out.println(e.getFault().getFaultCode() + " : " +
			// e.getMessage() + e.getClass());
			return "1";
		}
		// return "1";

	}

	public Long get_id_(String name) {
		Long target_id = 0L;
		DocumentManagement_Service docService = new DocumentManagement_Service();
		DocumentManagement docClient = docService.getBasicHttpBindingDocumentManagement();
		// We need to manually set the SOAP header to include the authentication
		// token
		try {
			setSoapHeader((WSBindingProvider) docClient);
		} catch (Exception e) {
			// //System.out.println(e.getMessage());

		}

		try {
			target_id = docClient.getNodeByName(2000, name).getID();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return target_id;
	}

//	public void logs(String str) throws IOException {
//
//		String path = get_path();
//
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		String date = dtf.format(now);
//		String[] arr = date.split("\\s+");
//		String[] arr2 = arr[0].split("/");
//		String file_name = "Eradah Logs " + arr2[0] + "-" + arr2[1] + "-" + arr2[2];
//
//		File file = new File(path + file_name + ".txt");
//		FileWriter fr = new FileWriter(file, true);
//		fr.write(str + System.lineSeparator());
//		fr.close();
//
//	}

	private String get_path() {

		List<String> list = new ArrayList<String>();
		String url = "", username = "", password = "";
		Properties prop = new Properties();
		InputStream input = null;
		String path = "";

		try {
			String currentRelativePath = System.getProperty("catalina.base");
			input = new FileInputStream(currentRelativePath + "/ot_wsdls_config.properties");
			prop.load(input);
			path = prop.getProperty("log_path");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}

}