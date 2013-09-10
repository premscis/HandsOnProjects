/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.fhnw.mscbis.premscis.handson6.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import ch.fhnw.mscbis.premscis.handson6.domain.Address;
import ch.fhnw.mscbis.premscis.handson6.domain.Customer;


/**
 *
 * @author andreas.martin
 */
@Singleton
@Startup
public class CustomerDemoDataGenerator {
    
	@EJB
	CustomerEJB customerEJB;
	
	@PostConstruct
	public void init()
	{
		Customer customer = customerEJB.findCustomerById(3L);
		if(customer == null){
			Address address = new Address();
			address.setCity("Basel");
			List<Address> addresses = new ArrayList<Address>();
			addresses.add(address);
			
			customer = new Customer();
			customer.setFirstName("Andreas");
			customer.setLastName("Martin");
			customer.setAddress(addresses);
			
			customerEJB.createCustomer(customer);
		}
	}
    
}
