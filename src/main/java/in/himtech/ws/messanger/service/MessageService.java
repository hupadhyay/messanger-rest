package in.himtech.ws.messanger.service;

import java.util.List;

import in.himtech.ws.messanger.domain.Message;
import in.himtech.ws.messanger.util.RestResponse;

public interface MessageService {

	RestResponse<Message> createMessage(Message msg);
	
	RestResponse<Message> updateMessage(Message msg);
	
	RestResponse<Message> deleteMessage(int msgId);
	
	RestResponse<Message> getMessage(int msgId);

	RestResponse<List<Message>> getAllMessages();
}
