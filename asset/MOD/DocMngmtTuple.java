package com.asset.MOD;

import com.opentext.ecm.api.OTAuthentication;
import com.opentext.livelink.service.docman.DocumentManagement;

public class DocMngmtTuple {
	
	 public final DocumentManagement docManagement;
	    public final OTAuthentication OTAuth;

	    public DocMngmtTuple( DocumentManagement docManagement, OTAuthentication OTAuth  ) {
	        this.docManagement = docManagement;
	        this.OTAuth = OTAuth;
	    }

}
