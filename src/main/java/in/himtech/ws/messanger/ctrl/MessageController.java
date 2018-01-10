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

import in.himtech.ws.messanger.domain.Message;
import in.himtech.ws.messanger.service.MessageService;
import in.himtech.ws.messanger.util.RestResponse;

@Singleton
@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageController {

	@Inject
	CommentController cmntController;

	@Inject
	private MessageService msgService;

	@POST
	public Response saveMessage(Message msg) {
		RestResponse<Message> restResponse = msgService.createMessage(msg);
		if (restResponse.getCode().equals("201")) {
			return Response.status(Status.CREATED).entity(restResponse.getTypeObj()).type(MediaType.APPLICATION_JSON)
					.build();
		} else {
			throw new WebApplicationException(
					Response.status(Status.INTERNAL_SERVER_ERROR).entity(restResponse.getMessage()).build());
		}
	}

	@DELETE
	@Path("{msgId}")
	public Response deleteMessage(@PathParam("msgId") int msgId) {
		RestResponse<Message> restResponse = msgService.deleteMessage(msgId);
		if (restResponse.getCode().equals("404")) {
			throw new WebApplicationException(
					Response.status(Status.NOT_FOUND).entity(restResponse.getMessage()).build());
		} else if (restResponse.getCode().equals("204")) {
			return Response.status(Status.NO_CONTENT).build();
		} else {
			throw new WebApplicationException(
					Response.status(Status.INTERNAL_SERVER_ERROR).entity(restResponse.getMessage()).build());
		}
	}

	@PUT
	@Path("{msgId}")
	public Response updateMessage(@PathParam("msgId") int msgId, Message msg) {
		msg.setId(msgId);
		RestResponse<Message> restResponse = msgService.updateMessage(msg);
		if (restResponse.getCode().equals("200")) {
			return Response.status(Status.OK).entity(restResponse.getTypeObj()).build();
		} else {
			throw new WebApplicationException(
					Response.status(Status.INTERNAL_SERVER_ERROR).entity(restResponse.getMessage()).build());
		}
	}

	@GET
	@Path("{msgId}")
	public Response getMessage(@PathParam("msgId") int msgId) {
		RestResponse<Message> restResponse = msgService.getMessage(msgId);
		if (restResponse.getCode().equals("200")) {
			return Response.status(Status.OK).entity(restResponse.getTypeObj()).build();
		} else {
			throw new WebApplicationException(
					Response.status(Status.NOT_FOUND).entity(restResponse.getMessage()).build());
		}
	}

	@GET
	public Response getAllMessage() {
		RestResponse<List<Message>> restResponse = msgService.getAllMessages();
		if (restResponse.getCode().equals("200")) {
			return Response.status(Status.OK).entity(restResponse.getTypeObj()).build();
		} else {
			return Response.status(Status.NOT_FOUND).entity(restResponse.getMessage()).build();
		}
	}

	@Path("{msgId}/comment")
	public CommentController getCommentController() {
		return cmntController;
	}
}
