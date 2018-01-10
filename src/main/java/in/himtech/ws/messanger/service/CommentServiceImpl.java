package in.himtech.ws.messanger.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.himtech.ws.messanger.domain.Comment;
import in.himtech.ws.messanger.repository.CommentRepository;
import in.himtech.ws.messanger.util.RestResponse;

@Singleton
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentRepository cmntRepository;

	@Override
	public RestResponse<Comment> saveComment(Integer msgId, Comment comment) {
		RestResponse<Comment> response = new RestResponse<>();
	
		Comment cmnt = cmntRepository.saveComment(msgId, comment);

		if (cmnt == null) {
			response.setCode("500");
			response.setMessage("Message could not persisted.");
			response.setTypeObj(null);
		} else {
			response.setCode("201");
			response.setMessage("Message successfully persisited");
			response.setTypeObj(cmnt);
		}
		return response;
	}

	@Override
	public RestResponse<Comment> updateComment(Integer msgId, Integer cmntId, Comment comment) {
		RestResponse<Comment> response = new RestResponse<>();
		
		comment.setId(cmntId);
		Comment cmnt = cmntRepository.updateComment(msgId, comment);

		if (cmnt == null) {
			response.setCode("500");
			response.setMessage("Message could not persisted.");
			response.setTypeObj(null);
		} else {
			response.setCode("200");
			response.setMessage("Message successfully persisited");
			response.setTypeObj(cmnt);
		}
		return response;
	}

	@Override
	public RestResponse<Comment> deleteComment(Integer msgId, Integer cmntId) {
		RestResponse<Comment> response = new RestResponse<>();
		Comment cmnt = cmntRepository.getComment(msgId, cmntId);
		if (cmnt == null) {
			response.setCode("404");
			response.setMessage("Resource with following id " + msgId + " is not available.");
			response.setTypeObj(null);
		}

		boolean bool = cmntRepository.deleteComment(msgId, cmntId);

		if (bool) {
			response.setCode("204");
			response.setMessage("Resource with following id " + cmntId + " is removed.");
			response.setTypeObj(null);
		} else {
			response.setCode("500");
			response.setMessage(
					"Resource with following id " + cmnt + " could not deleted due to some internal error.");
			response.setTypeObj(null);
		}
		return response;
	}

	@Override
	public RestResponse<Comment> getComment(Integer msgId, Integer cmntId) {
		RestResponse<Comment> response = new RestResponse<>();
		Comment cmnt = cmntRepository.getComment(msgId, cmntId);
		if (cmnt == null) {
			response.setCode("404");
			response.setMessage("Resource with following id " + msgId + " is not available.");
			response.setTypeObj(null);
		} else {
			response.setCode("200");
			response.setMessage("successfully retrived.");
			response.setTypeObj(cmnt);
		}
		return response;
	}

	@Override
	public RestResponse<List<Comment>> getAllComments(Integer msgId) {
		RestResponse<List<Comment>> response = new RestResponse<>();
		List<Comment> listMessage = cmntRepository.getAllComments(msgId);
		response.setCode("200");
		response.setMessage("successfully retrieved");
		response.setTypeObj(listMessage);
		return response; 
	}

}
