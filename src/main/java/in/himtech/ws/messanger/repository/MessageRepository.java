package in.himtech.ws.messanger.repository;

import java.util.List;

import in.himtech.ws.messanger.domain.Message;

public interface MessageRepository {

	Message retriveMessage(int msgId);

	Message saveMessage(Message message);

	boolean removeMessage(Message msg);

	public List<Message> retrievAllMessages();

	public Message updateMessage(Message msg);
}
