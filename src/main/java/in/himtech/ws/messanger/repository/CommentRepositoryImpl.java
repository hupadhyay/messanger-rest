package in.himtech.ws.messanger.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import in.himtech.ws.messanger.domain.Comment;
import in.himtech.ws.messanger.util.EntityManagerUtils;

@Singleton
public class CommentRepositoryImpl implements CommentRepository{
	
	private EntityManagerFactory emf = EntityManagerUtils.getInstance();

	@Override
	public Comment saveComment(Comment comment) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(comment);
			transaction.commit();
			em.close();
			return comment;
		} catch (Exception exp) {
			System.out.println("Exception ocurs while saving comment : " + exp.getMessage());
			transaction.rollback();
			em.close();
			return null;
		}
	}

	@Override
	public Comment updateComment(Comment comment) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(comment);
			transaction.commit();
			em.close();
			return comment;
		} catch (Exception exp) {
			System.out.println("Exception ocurs while saving comment : " + exp.getMessage());
			transaction.rollback();
			em.close();
			return null;
		}
	}

	@Override
	public boolean deleteComment(Integer msgId, Integer cmntId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			Comment comment = em.find(Comment.class, cmntId);
			em.remove(comment);
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

	@Override
	public Comment getComment(Integer msgId, Integer cmntId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("oneCmntRecords", Comment.class);
		Comment comment = (Comment)query.getResultList().get(0);
		em.close();
		return comment;
	}

	@Override
	public List<Comment> getAllComments(Integer msgId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("allCmntRecords", Comment.class);
		List<Comment> listComment = query.getResultList();
		em.close();
		return listComment;
	}

}
