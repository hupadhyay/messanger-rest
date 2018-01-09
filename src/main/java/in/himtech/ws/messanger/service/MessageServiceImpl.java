package in.himtech.ws.messanger.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.himtech.ws.messanger.domain.Message;
import in.himtech.ws.messanger.repository.MessageRepository;
import in.himtech.ws.messanger.util.RestResponse;

@Singleton
public class MessageServiceImpl implements MessageService{
	
	@Inject
	private MessageRepository msgRepo;

	@Override
	public RestResponse<Message> createMessage(Message msg) {
		RestResponse<Message> response = new RestResponse<>();
		Message message = msgRepo.saveMessage(msg);
	
		if(message == null) {
			response.setCode("500");
			response.setMessage("Message could not persisited.");
			response.setTypeObj(null);
		} else {
			response.setCode("201");
			response.setMessage("Message successfully persisited");
			response.setTypeObj(message);
		}
		return response;
	}

	@Override
	public RestResponse<Message> updateMessage(Message msg) {
		RestResponse<Message> response = new RestResponse<>();
		Message message = msgRepo.updateMessage(msg);
	
		if(message == null) {
			response.setCode("500");
			response.setMessage("Message could not updated.");
			response.setTypeObj(null);
		} else {
			response.setCode("200");
			response.setMessage("Message successfully updated");
			response.setTypeObj(message);
		}
		return response;
	}

	@Override
	public RestResponse<Message> deleteMessage(int msgId) {
		RestResponse<Message> response = new RestResponse<>();
		Message msg = msgRepo.retriveMessage(msgId);
		if(msg == null) {
			response.setCode("404");
			response.setMessage("Resource with following id " + msgId + " is not available.");
			response.setTypeObj(null);
		}
		
		boolean bool = msgRepo.removeMessage(msg);
		
		if(bool) {
			response.setCode("200");
			response.setMessage("Resource with following id " + msgId + " is removed.");
			response.setTypeObj(null);
		} else {
			response.setCode("500");
			response.setMessage("Resource with following id " + msgId + " could not deleted due to some internal error.");
			response.setTypeObj(null);
		}
		return response;
	}

	@Override
	public RestResponse<Message> getMessage(int msgId) {
		RestResponse<Message> response = new RestResponse<>();
		Message msg = msgRepo.retriveMessage(msgId);
		
		if(msg == null) {
			response.setCode("404");
			response.setMessage("Resource with following id " + msgId + " is not available.");
			response.setTypeObj(null);
		} else {
			response.setCode("200");
			response.setMessage("successfully retrived.");
			response.setTypeObj(msg);
		}
		return response;
	}

	@Override
	public RestResponse<List<Message>> getAllMessages() {
		RestResponse<List<Message>> response = new RestResponse<>();
		List<Message> listMessage = msgRepo.retrievAllMessages();
		response.setCode("200");
		response.setMessage("successfully retrieved");
		response.setTypeObj(listMessage);
		return response; 
	}

}
