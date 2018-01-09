package in.himtech.ws.messanger.service;

import java.util.List;

import in.himtech.ws.messanger.domain.Comment;
import in.himtech.ws.messanger.util.RestResponse;

public interface CommentService {
	
	RestResponse<Comment> saveComment(Integer msgId, Comment comment);
	
	RestResponse<Comment> updateComment(Integer msgId, Integer cmntId, Comment comment);
	
	RestResponse<Comment> deleteComment(Integer msgId, Integer cmntId);
	
	RestResponse<Comment> getComment(Integer msgId, Integer cmntId);
	
	RestResponse<List<Comment>> getAllComments(Integer msgId);

}
