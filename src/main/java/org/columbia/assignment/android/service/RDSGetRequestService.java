package org.columbia.assignment.android.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;



@Path("/rdsobject")
public class RDSGetRequestService {
	@GET
	public Response getRequest(@Context UriInfo uri){
		 System.out.println("UI is ..........." + (uri.getAbsolutePath()).toString());
		return Response.status(200).entity("hello").build();
	}
	

}
