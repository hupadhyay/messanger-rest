package in.himtech.ws.messanger.repository;

import java.util.List;

import in.himtech.ws.messanger.domain.Comment;

public interface CommentRepository {

	Comment saveComment(Comment comment);
	
	Comment updateComment(Comment comment);
	
	boolean deleteComment(Integer msgId, Integer cmntId);
	
	Comment getComment(Integer msgId, Integer cmntId);
	
	List<Comment> getAllComments(Integer msgId);

}
