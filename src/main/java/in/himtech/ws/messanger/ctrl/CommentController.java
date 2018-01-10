package in.himtech.ws.messanger.ctrl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import in.himtech.ws.messanger.domain.Comment;
import in.himtech.ws.messanger.service.CommentService;
import in.himtech.ws.messanger.util.RestResponse;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class CommentController {

	@Inject
	private CommentService cmntService;

	@POST
	public Response persistComment(@PathParam("msgId") Integer msgId, Comment comment) {
		RestResponse<Comment> restResponse = cmntService.saveComment(msgId, comment);

		if (restResponse.getCode().equals("201")) {
			return Response.status(Status.CREATED).entity(restResponse.getTypeObj()).type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(restResponse.getTypeObj()).type(MediaType.TEXT_PLAIN).build());
		}
	}

	@PUT
	@Path("{cmntId}")
	public Response updateComment(@PathParam("msgId") Integer msgId, @PathParam("cmntId") Integer cmntId,
			Comment comment) {
		RestResponse<Comment> restResponse = cmntService.updateComment(msgId, cmntId, comment);

		if (restResponse.getCode().equals("200")) {
			return Response.status(Status.OK).entity(restResponse.getTypeObj()).type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(restResponse.getTypeObj()).type(MediaType.TEXT_PLAIN).build());
		}

	}

	@GET
	@Path("{cmntId}")
	public Response getComment(@PathParam("msgId") Integer msgId, @PathParam("cmntId") Integer cmntId) {
		System.out.println("Get Operation: Message id : " + msgId + ", and comment id : " + cmntId);
		RestResponse<Comment> restResponse = cmntService.getComment(msgId, cmntId);

		if (restResponse.getCode().equals("200")) {
			return Response.status(Status.OK).entity(restResponse.getTypeObj()).type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity(restResponse.getTypeObj())
					.type(MediaType.TEXT_PLAIN).build());
		}
	}

	@DELETE
	@Path("{cmntId}")
	public Response deleteComment(@PathParam("msgId") Integer msgId, @PathParam("cmntId") Integer cmntId) {
		System.out.println("delete Operation: Message id : " + msgId + ", and comment id : " + cmntId);
		RestResponse<Comment> restResponse = cmntService.deleteComment(msgId, cmntId);

		if (restResponse.getCode().equals("204")) {
			return Response.status(Status.NO_CONTENT).build();
		} else if (restResponse.getCode().equals("404")) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity(restResponse.getTypeObj())
					.type(MediaType.TEXT_PLAIN).build());
		} else {
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(restResponse.getTypeObj()).type(MediaType.TEXT_PLAIN).build());
		}
	}

	@GET
	public Response getAllComments(@PathParam("msgId") Integer msgId) {
		System.out.println("Get All Operation: Message id : " + msgId);
		RestResponse<List<Comment>> restResponse = cmntService.getAllComments(msgId);

		if (restResponse.getCode().equals("200")) {
			return Response.status(Status.OK).entity(restResponse.getTypeObj()).type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity(restResponse.getTypeObj())
					.type(MediaType.TEXT_PLAIN).build());
		}
	}

}
