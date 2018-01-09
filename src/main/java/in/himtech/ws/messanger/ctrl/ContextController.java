package in.himtech.ws.messanger.ctrl;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * demo for content, uriInfo, matrix, cookiee,header, httpheaders, beanparam
 * 
 * @author Himanshu
 *
 */

@Path("context")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class ContextController {

	@GET
	@Path("test")
	public String paramDemo(@MatrixParam("matrix") String matrix, @HeaderParam("pageSize") String pageSize,
			@CookieParam("name") String cookie) {

		return "Matrix Param: " + matrix + ", Header Param: " + pageSize + ", Cookie Param: " + cookie;

	}

	@GET
	@Path("query")
	public String queryDemo(@QueryParam("offset") String offSet, @QueryParam("pageSize") String pageSize) {
		return "OffSet is : " + offSet + ", pageSize is : " + pageSize;

	}

	@GET
	@Path("http")
	public String httpHeaderDemo(@Context HttpHeaders httpHeaders) {
		return "Header Value: Media type is : " + httpHeaders.getMediaType() + ", and Date is :"
				+ httpHeaders.getDate();
	}
	
	@GET
	@Path("uri")
	public String uriDemo(@Context UriInfo uriInfo) {
		return "URI Info Value: " + uriInfo.getAbsolutePath();
	}

}
