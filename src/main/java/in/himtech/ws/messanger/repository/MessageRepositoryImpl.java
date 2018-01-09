package in.himtech.ws.messanger.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import in.himtech.ws.messanger.domain.Message;
import in.himtech.ws.messanger.util.EntityManagerUtils;

@Singleton
public class MessageRepositoryImpl implements MessageRepository {

	private EntityManagerFactory emf = EntityManagerUtils.getInstance();

	public Message retriveMessage(int msgId) {
		EntityManager em = emf.createEntityManager();
		Message msg = em.find(Message.class, msgId);
		return msg;
	}

	public Message saveMessage(Message message) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(message);
			transaction.commit();
			em.close();
			return message;
		} catch (Exception exp) {
			System.out.println("Exception ocurs while saving : " + exp.getMessage());
			transaction.rollback();
			em.close();
			return null;
		}
	}

	public boolean removeMessage(Message msg) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			Message message = em.find(Message.class, msg.getId());
			em.remove(message);
			transaction.commit();
			em.close();
			return true;
		} catch (Exception exp) {
			System.out.println("Exception ocurs while deleting : " + exp.getMessage());
			transaction.rollback();
			em.close();
			return false;
		}
	}

	public List<Message> retrievAllMessages() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("allRecords", Message.class);
		List<Message> listMessage = query.getResultList();
		em.close();
		return listMessage;
	}

	public Message updateMessage(Message msg) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		Message msgUpdate = null;

		try {
			transaction.begin();
			msgUpdate = em.find(Message.class, msg.getId());
			msgUpdate.setContent(msg.getContent());
			msgUpdate.setOwner(msg.getOwner());
			transaction.commit();
			em.close();
		} catch (Exception exp) {
			System.out.println("Exception ocurs while saving : " + exp.getMessage());
			transaction.rollback();
			em.close();
		}

		return msgUpdate;
	}
}
