package in.himtech.ws.messanger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import in.himtech.ws.messanger.ctrl.CommentController;
import in.himtech.ws.messanger.repository.CommentRepository;
import in.himtech.ws.messanger.repository.CommentRepositoryImpl;
import in.himtech.ws.messanger.repository.MessageRepository;
import in.himtech.ws.messanger.repository.MessageRepositoryImpl;
import in.himtech.ws.messanger.service.CommentService;
import in.himtech.ws.messanger.service.CommentServiceImpl;
import in.himtech.ws.messanger.service.MessageService;
import in.himtech.ws.messanger.service.MessageServiceImpl;

/**
 * Hello world!
 *
 */

@ApplicationPath("/api")
public class Application extends ResourceConfig 
{
	public Application() {
		packages("in.himtech.ws.messanger");
		
		register(new AbstractBinder() {
			
			@Override
			protected void configure() {
				bind(MessageServiceImpl.class).to(MessageService.class);
				bind(MessageRepositoryImpl.class).to(MessageRepository.class);
				bind(CommentServiceImpl.class).to(CommentService.class);
				bind(CommentRepositoryImpl.class).to(CommentRepository.class);
				bind(CommentController.class).to(CommentController.class);
			}
		});
	}
	
}
