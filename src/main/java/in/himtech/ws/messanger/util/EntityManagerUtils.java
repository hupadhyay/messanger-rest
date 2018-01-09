package in.himtech.ws.messanger.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist-unit");

	public static EntityManagerFactory getInstance() {
		return emf;
	}

}
