package com.asset.MOD;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.glassfish.jersey.internal.util.collection.ByteBufferInputStream;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

import com.aspose.pdf.internal.html.net.MultipartFormDataContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.itextpdf.text.pdf.PdfReader;
import com.opentext.cws.MemberService;
import com.opentext.cws.Metadata;
import com.opentext.livelink.service.docman.Node;
import com.opentext.livelink.service.memberservice.GetUserByLoginName;

import jersey.repackaged.com.google.common.io.ByteArrayDataInput;

@Path("/myresource")
public class Main {

	@POST
	@Path("/CreateDocument")
	@Consumes({ "multipart/form-data" })
	public Response CreateDocument(@FormDataParam("file") InputStream input,
			@FormDataParam("file") FormDataContentDisposition fileDispositions,
			@FormDataParam("cat_name") String cat_name, @FormDataParam("cat_values") String cat_values,
			@FormDataParam("parent_path") String parent_path, @FormDataParam("username") String username,
			@FormDataParam("password") String password)
			throws IOException, NumberFormatException, DatatypeConfigurationException, ParseException,
			ParserConfigurationException, SAXException, InterruptedException, SQLException, JSONException, org.json.simple.parser.ParseException {
		
		
		
		

		if (input == null || cat_name == null || cat_name.equals("") || cat_name.equals(" ") || cat_values == null
				|| cat_values.equals("") || cat_values.equals(" ") || parent_path == null || parent_path.equals("")
				|| parent_path.equals(" ") || username == null || username.equals("") || username.equals(" ")
				|| password == null || password.equals("") || password.equals(" ")) {
			return Response.status(400).entity("400 Bad request - missing parameters")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept,X-Requested-With").build();

		}
		if (cat_values.contains("-") ) {
			return Response.status(400).entity("400 Invalid Value Data Type")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
		

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String start_time = dtf.format(now);
		Fun f = new Fun();
		// f.logs("CreateDocument Request Start");
		System.out.println("CreateDocument Request Start");
		long startTime = System.nanoTime();

		f.URL();
		String[] arr = fileDispositions.getFileName().split("[.]");
		byte[] doc = null;
		doc = IOUtils.toByteArray(input);
		// ByteArrayOutputStream os = new ByteArrayOutputStream();
		// byte[] buffer = new byte[1024];
		// int len;
		// while ((len = input.read(buffer)) != -1) {
		// os.write(buffer, 0, len);
		// }
		// doc = os.toByteArray();
		String doc_name = fileDispositions.getFileName();

		byte ptext[] = doc_name.getBytes("ISO-8859-1");
		String value = new String(ptext, "UTF-8");

		String token = f.authenticate(username, password);
		boolean b=false;
		if (username.equals("admin")|| username.equals("Admin")) {
			b=true;
		}
		else {b=f.checkUserInGroup(token);}
		if (b==false) {
			
			
			return Response.status(401).entity("401 User has no permmisons")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
			
		}

		if (token.equals("error")) {
			return Response.status(401).entity("401 Unauthorized (username or password incorrect )")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();

		}
		long createdCaseFolderId = -1;
		
		if (cat_name.equals("أرشيف الدعاوى") || cat_name.equals("أرشيف الاوامر")){
			
			createdCaseFolderId = f.create_exception_folder(parent_path, cat_name, token, cat_values);
		}
		
	

		String folder_id = f.check_path(parent_path, username, password);
		if (folder_id.equals("error")) {
			return Response.status(400).entity("400 Path Not Found").header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept,X-Requested-With").build();

		}
		if(createdCaseFolderId > 0) {
			folder_id = String.valueOf(createdCaseFolderId);
		}
		if (f.check_document_exist(folder_id, username, password, doc_name)) {
			return Response.status(500).entity("500 Document Already Exist")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept,X-Requested-With").build();

		}
		// String cat_id = f.search_cat(cat_name);
		// String ticket = f.authenticate_rest(username, password);
		// String cat_id = f.search_api_cat_test(ticket, cat_name);
		// String cat_id = f.get_catogory_id_xml(cat_name);
//		String cat_id = f.test_return(cat_name);
//		String cat_id = "";
//		if (cat_name.equals("أرشيف الأشخاص")) {
//			cat_id="9773";
//		}
		String cat_id=f.return_cat_id(cat_name);
		System.out.println(cat_name);
		System.out.println("cat id= "+cat_id);
		if (cat_id.equals("error") || cat_id.equals("error no item found")) {
			return Response.status(400).entity("400 Bad request - missing parameters")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept,X-Requested-With").build();
		}
		String object_id = f.get_id(cat_id, doc, cat_values, parent_path, folder_id, value, arr[1]);
		
		if (object_id.equals("error for value")) {
			return Response.status(400).entity("400 Invalid Value Data Type")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
	
		if (object_id.equals("The value is not one of the valid values") || object_id.equals("error required")) {
			return Response.status(400).entity("The value is not one of the valid values")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();}
		
		if (object_id.equals("error for value")) {
			return Response.status(400).entity("400 Invalid Value Data Type")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
		if (object_id.equals("403")) {
			return Response.status(403).entity("403 Value exceeds defined attribute length")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}

		if (object_id.equals("501")) {
			return Response.status(500).entity("500 Error Uploading Document")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
		if (object_id.equals("700")) {
			return Response.status(501)
					.entity("501 Lookup Attribute Value�s is not found in the lookup table Or Wrong Value")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}

		if (object_id.equals("error")) {
			return Response.status(400).entity("400 Bad request - missing parameters")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		if (object_id.equals("value error")) {
			return Response.status(501).entity("501 Lookup Attribute Value�s is not found in the lookup table")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
		 else {
//			long endTime = System.nanoTime();
//			long timeElapsed = endTime - startTime;
//			System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
//			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			LocalDateTime now1 = LocalDateTime.now();
//			String end_time = dtf1.format(now1);
//			f.logs(" >> CreateDocument Request Start for " + fileDispositions.getFileName() + " started at :"
//					+ start_time + " Completed at : " + end_time);
			return Response.ok("SUCCESS DOCUMENT ID IS: "+object_id).status(200).header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}

	}

	@GET
	@Path("/RetrieveDocumentById")
	// @Consumes({ "multipart/form-data" })
	// @Produces({ "application/x-octet-stream" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response RetrieveDocumentById(@QueryParam("doc_id") String doc_id, @QueryParam("username") String username,
			@QueryParam("password") String password, @QueryParam("version_no") String version_no) throws IOException, JSONException, org.json.simple.parser.ParseException {
		String JSONObject = "";
		if (doc_id == null || doc_id.equals("") || doc_id.equals(" ") || username == null || username.equals("")
				|| username.equals(" ") || password == null || password.equals("") || password.equals(" ")) {
			return Response.status(400).entity("400  missing parameters")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();

		}
		
		if (doc_id.contains("-")||version_no.contains("-")) {
			
			return Response.status(400).entity("400  DOC ID OR VERSION NUMBER CAN'T BE NEGATIVE VALUE")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
		

//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		String start_time = dtf.format(now);
//		System.out.println("RetrieveDocumentById Request Start");
//		long startTime = System.nanoTime();
		Fun f = new Fun();
		f.URL();
		byte[] arr = null;
		String token = f.authenticate(username, password);
		String latestVersion=f.checkVersions(token, doc_id);

		

		
		if (token.equals("error")) {
			return Response.status(401).entity("401 Unauthorized (username or password incorrect)")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
		boolean b=false;
		if (username.equals("admin")|| username.equals("Admin")) {
			b=true;
		}
		else {b=f.checkUserInGroup_r(token);}
		if (b==false) {
			
			
			return Response.status(401).entity("401 User has no permmisons")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
			
		}
		
//		Integer latest = Integer.valueOf(latestVersion);
//		Integer version = Integer.valueOf(version_no);
//		System.out.println(latest);
//		System.out.println(version);
//		
//		if(version>latest) {
//			return Response.status(400).entity("400 Version dosen't exist")
//					.header("Access-Control-Allow-Origin", (Object) "*")
//					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
//					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
//			
//		}
		
//		if(Long.parseLong(version_no)>Long.parseLong(latestVersion)) {
//			return Response.status(400).entity("400 Version dosen't exist")
//					.header("Access-Control-Allow-Origin", (Object) "*")
//					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
//					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
//			
//		}
		

		if (f.get_node_by_id(doc_id)) {
			List list = new ArrayList<>();
			if (version_no != null && version_no.length() > 0 && !version_no.equals(" ")) {
				// arr = f.get_doc_by_id(doc_id, version_no);
				list = f.get_doc_by_id2_test(doc_id, version_no);
				System.out.println(list+"ERORRRRRRRR");
				if(list.get(0).toString().contains("not found")) {
					System.out.println("notfound");
					return Response.status(400).entity(list.get(0))
							.header("Access-Control-Allow-Origin", (Object) "*")
							.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
							.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
					
				}
			} else if (version_no == null || version_no.equals("") || version_no.equals(" ")) {
				String ver_no = f.get_version_no(doc_id);
				arr = f.get_doc_by_id(doc_id, ver_no);
				list = f.get_doc_by_id2_test(doc_id, ver_no);
			}

			// return Response.ok(arr, MediaType.APPLICATION_OCTET_STREAM)
			// .header("Content-Disposition", "attachment; filename=\"" + n.getName() +
			// "\"") // optional
			// .build();

			GsonBuilder gsonMapBuilder = new GsonBuilder();
			Gson gsonObject = gsonMapBuilder.create();
			JSONObject = gsonObject.toJson(list);
//			long endTime = System.nanoTime();
//			long timeElapsed = endTime - startTime;
//			System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
//
//			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			LocalDateTime now1 = LocalDateTime.now();
//			String end_time = dtf1.format(now1);
//			f.logs(" >> RetrieveDocumentById  started at :" + start_time + " Completed at : " + end_time);

			return Response.ok(JSONObject).status(200).header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();

		} else {
			return Response.status(404).entity("404 Document Not Found")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
	}

	@DELETE
	@Path("/DeleteDocumentById")
//		@Consumes("application/pdf")
	public Response DeleteDocumentById(@FormDataParam("username") String username,
			@FormDataParam("password") String password, @FormDataParam("Doc_id") String Doc_id,
			@FormDataParam("Version_no") String Version_no) throws IOException, JSONException, org.json.simple.parser.ParseException {

		
		
		if (Version_no == null || Version_no.equals("") ||  Version_no.equals("  ") ||  Doc_id == null
				|| Doc_id.equals("") || Doc_id.equals(" ") || username == null || username.equals("") || username.equals(" ")
				|| password == null || password.equals("") || password.equals(" ")) {
			return Response.status(400).entity("400 Bad request - missing parameters")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept,X-Requested-With").build();

		}
		
	if (Doc_id.contains("-")||Version_no.contains("-")) {
			
			return Response.status(400).entity("400  DOC ID OR VERSION NUMBER CAN'T BE NEGATIVE VALUE")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		Fun f = new Fun();
		
		String ticket = f.auth_rest(username, password);
		if (ticket.equals("error")) {
			return Response.status(401).entity("INVALID USERNAME OR PASSWORD")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Content-Security-Policy", "default-src self")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();}
		boolean b=false;
		if (username.equals("admin")|| username.equals("Admin")) {
			b=true;
		}
		else {b=f.checkUserInGroup(ticket);}
		if (b==false) {
			
			
			return Response.status(401).entity("401 User has no permmisons")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
			
		}
		
		String latestVersion=f.checkVersions(ticket, Doc_id);
		System.out.println("latest before"+latestVersion);
		
		if (latestVersion.equals("error")) {
			return Response.status(400).entity("Document dosen't exist")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Content-Security-Policy", "default-src self")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();}

		latestVersion=f.compareVersions(latestVersion, Version_no);
		
		System.out.println("latest AFTER"+latestVersion);


		String del_response = "";
		if (latestVersion.equals("0")) {
			del_response = f.deletenode(Doc_id, ticket);
		} 
		else if(latestVersion.equals("Version dosen't exist")) {
			
			return Response.status(400).entity("Version dosen't exist")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Content-Security-Policy", "default-src self")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		else {
			del_response = f.deletenodeVer(Doc_id, Version_no, ticket);

		}

		System.out.println(del_response);

		
		if (del_response.equals("error")) {
			return Response.status(400).entity("Error getting version for document")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Content-Security-Policy", "default-src self")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		} else {
			return Response.status(200).entity("DELETED").header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Content-Security-Policy", "default-src self")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
	}
	
	
	
	

	@POST
	@Path("/ImportNewVersionByID")
	@Consumes({ "multipart/form-data" })
	public Response ImportNewVersionByID(@FormDataParam("doc_id") String doc_id,
			@FormDataParam("file") InputStream input,
			@FormDataParam("file") FormDataContentDisposition fileDispositions,
			@FormDataParam("username") String username, @FormDataParam("password") String password) throws IOException, JSONException, org.json.simple.parser.ParseException {
		// System.out.println("input sent = " + input.read());
		if (input == null || doc_id == null || doc_id.equals("") || doc_id.equals(" ") || username == null
				|| username.equals("") || username.equals(" ") || password == null || password.equals("")
				|| password.equals(" ")) {

			return Response.status(400).entity("400 missing parameters")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		
	if (doc_id.contains("-")) {
			
			return Response.status(400).entity("400  DOC ID  CAN'T BE NEGATIVE VALUE")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String start_time = dtf.format(now);

		// System.out.println("ImportNewVersionByID Request Start");
		long startTime = System.nanoTime();
		Fun f = new Fun();
		f.URL();
		byte[] bytes = IOUtils.toByteArray(input);
		String doc_name = fileDispositions.getFileName();

		byte ptext[] = doc_name.getBytes("ISO-8859-1");
		String value = new String(ptext, "UTF-8");

		String token = f.authenticate(username, password);
		
		boolean b=false;
		if (username.equals("admin")|| username.equals("Admin")) {
			b=true;
		}
		else {b=f.checkUserInGroup(token);}
		if (b==false) {
			
			
			return Response.status(401).entity("401 User has no permmisons")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET,PUT,UPDATE,OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type,Accept,X-Requested-With").build();
			
		}

		if (token.equals("error")) {
			return Response.status(401).entity("401 Unauthorized (username or password incorrect)")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		// String obj_id = f.get_node(doc_id, bytes, value, username, password);
		String obj_id = f.get_node_version(doc_id, bytes, value, username, password);

		if (obj_id.equals("doc exist")) {
			return Response.status(500).entity("500 Version Uploaded successfully But Document Name Already Exist")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}

		if (obj_id.equals("document not found")) {
			return Response.status(404).entity("404 Document dosen't exist ")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		}
		if (obj_id == null || obj_id.equals("") || obj_id.equals(" ")) {
			return Response.status(500).entity("500 Internal server error")
					.header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();
		} else {
//			long endTime = System.nanoTime();
//			long timeElapsed = endTime - startTime;
//			System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
//
//			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			LocalDateTime now1 = LocalDateTime.now();
//			String end_time = dtf1.format(now1);
//
//			f.logs(" >> ImportNewVersionByID For " + fileDispositions.getFileName() + "  started at :" + start_time
//					+ " Completed at : " + end_time);

			return Response.ok("SUCCESS DOCUMENT VERSION IS:"+obj_id).status(200).header("Access-Control-Allow-Origin", (Object) "*")
					.header("Access-Control-Allow-Methods", (Object) "POST, GET, PUT, UPDATE, OPTIONS")
					.header("Access-Control-Allow-Headers", (Object) "Content-Type, Accept, X-Requested-With").build();

		}

	}
}
