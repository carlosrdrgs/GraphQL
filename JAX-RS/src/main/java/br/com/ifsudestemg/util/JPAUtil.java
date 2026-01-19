package br.com.ifsudestemg.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("museus-pu");
        } catch (Throwable ex) {
            System.err.println("Falha na criação do EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
    
    public static void close() {
        emf.close();
    }
}