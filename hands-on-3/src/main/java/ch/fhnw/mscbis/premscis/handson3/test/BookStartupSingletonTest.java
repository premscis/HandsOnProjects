/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.fhnw.mscbis.premscis.handson3.test;

import ch.fhnw.mscbis.premscis.handson3.domain.Book;
import ch.fhnw.mscbis.premscis.handson3.ejb.BookEJB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author andreas.martin
 */
@Singleton
@Startup
public class BookStartupSingletonTest {
    
    @EJB
    private BookEJB bookEJBLocal;

    @PostConstruct
    void init() {
        try {
            shouldCreateABook();
        } catch (Exception ex) {
            Logger.getLogger(BookStartupSingletonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void shouldCreateABook() throws Exception {
        Book book = new Book();
        book.setTitle("HelloNew");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        book = bookEJBLocal.createBook(book);
        assertNotNull("ID should not be null", book.getId());
        List<Book> books = bookEJBLocal.findBooks();
        assertNotNull(books);
    }
}
