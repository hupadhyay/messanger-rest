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

	private static CommentController cmntController;

	@Inject
	private CommentService cmntService;

	@POST
	public Response persistComment(@PathParam("msgId") Integer msgId, Comment comment) {
		System.out.println("Post Operation: Message id : " + msgId + ", and comment is: " + comment);
		RestResponse<Comment> restResponse = cmntService.saveComment(msgId, comment);
		return Response.status(Status.OK).build();
	}

	@PUT
	@Path("{cmntId}")
	public Response updateComment(@PathParam("msgId") Integer msgId, @PathParam("cmntId") Integer cmntId,
			Comment comment) {
		System.out.println("Put Operation Message id : " + msgId + ", and comment id: " + cmntId
				+ ", and comment data is " + comment);
		RestResponse<Comment> restResponse = cmntService.updateComment(msgId, cmntId, comment);
		return Response.status(Status.OK).build();
	}

	@GET
	@Path("{cmntId}")
	public Response getComment(@PathParam("msgId") Integer msgId, @PathParam("cmntId") Integer cmntId) {
		System.out.println("Get Operation: Message id : " + msgId + ", and comment id : " + cmntId);
		RestResponse<Comment> restResponse = cmntService.getComment(msgId, cmntId);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{cmntId}")
	public Response deleteComment(@PathParam("msgId") Integer msgId, @PathParam("cmntId") Integer cmntId) {
		System.out.println("delete Operation: Message id : " + msgId + ", and comment id : " + cmntId);
		RestResponse<Comment> restResponse = cmntService.deleteComment(msgId, cmntId);
		return Response.status(Status.OK).build();
	}

	@GET
	public Response getAllComments(@PathParam("msgId") Integer msgId) {
		System.out.println("Get All Operation: Message id : " + msgId);
		RestResponse<List<Comment>> restResponse = cmntService.getAllComments(msgId);
		return Response.status(Status.OK).build();
	}

}
