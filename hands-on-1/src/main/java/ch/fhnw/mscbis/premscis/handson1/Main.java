package ch.fhnw.mscbis.premscis.handson1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.fhnw.mscbis.premscis.handson1.domain.Book;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        Book book = new Book();
        book.setTitle("Java EE");
        book.setPrice(12.5F);
        book.setDescription("Java EE 6");
        book.setIsbn("1-4564-777-8");
        book.setNbOfPage(254);
        book.setIllustrations(Boolean.FALSE);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "primary");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        tx.commit();
        em.close();
        emf.close();
    }
}
