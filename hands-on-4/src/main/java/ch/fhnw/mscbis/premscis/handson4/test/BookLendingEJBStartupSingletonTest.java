/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.fhnw.mscbis.premscis.handson4.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import ch.fhnw.mscbis.premscis.handson4.domain.Address;
import ch.fhnw.mscbis.premscis.handson4.domain.Book;
import ch.fhnw.mscbis.premscis.handson4.domain.BookLending;
import ch.fhnw.mscbis.premscis.handson4.domain.Customer;
import ch.fhnw.mscbis.premscis.handson4.ejb.BookEJB;
import ch.fhnw.mscbis.premscis.handson4.ejb.BookLendingEJB;
import ch.fhnw.mscbis.premscis.handson4.ejb.CustomerEJB;
import static org.junit.Assert.*;

/**
 *
 * @author andreas.martin
 */
@Singleton
@Startup
public class BookLendingEJBStartupSingletonTest {

    @EJB
    private CustomerEJB customerEJB;
    @EJB
    private BookEJB bookEJB;
    @EJB
    private BookLendingEJB instance;
    private static Customer customer = null;
    private static Book book = null;
    
    @PostConstruct
    void init() {
        try {
            setUp();
            testLendBook();
            tearDown();
            setUp();
            testReturnBook();
            tearDown();
            setUp();
            testShowAllLendings();
            tearDown();
            setUp();
            testShowAllNotLendedBooks();
            tearDown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setUp() {
        Address address = new Address("Hauptstrasse", "Olten", "4600", "Switzerland", "Business");
        customer = new Customer();
        customer.setFirstName("Andreas");
        customer.setLastName("Martin");
        customer.setEmail("andreas.martin@fhnw.ch");
        customer.setDateOfBirth(new GregorianCalendar(1983, 04, 21).getTime());
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        customer.setAddress(addresses);
        book = new Book();
        book.setTitle("The Java Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        customerEJB.createCustomer(customer);
        bookEJB.createBook(book);
    }

    public void tearDown() {
        bookEJB.deleteBook(book);
        customerEJB.deleteCustomer(customer);
    }

    /**
     * Test of lendBook method, of class BookLendingEJB.
     */
    public void testLendBook() throws Exception {
        BookLending bookLending = instance.lendBook(book, customer);
        assertNotNull("ID should not be null", bookLending.getId());
        assertNotNull("ID should not be null", bookLending.getCustomer().getAge());
        instance.deleteBookLending(bookLending);
    }

    /**
     * Test of returnBook method, of class BookLendingEJB.
     */
    public void testReturnBook() throws Exception {
        BookLending bookLendingInit = instance.lendBook(book, customer);
        BookLending bookLending = instance.returnBook(book, customer);
        assertNotNull("ID should not be null", bookLending.getId());
        instance.deleteBookLending(bookLendingInit);
    }

    /**
     * Test of showAllLendings method, of class BookLendingEJB.
     */
    public void testShowAllLendings() throws Exception {
        BookLending bookLendingInit = instance.lendBook(book, customer);
        List<BookLending> bookLendings = instance.showAllLendings(customer);
        assertNotSame(0, bookLendings.size());
        instance.deleteBookLending(bookLendingInit);
    }

    /**
     * Test of showAllLendings method, of class BookLendingEJB.
     */
    public void testShowAllNotLendedBooks() throws Exception {
        List<Book> bookList = instance.showAllNotLendedBooks();
        assertNotSame(0, bookList.size());
    }
}
