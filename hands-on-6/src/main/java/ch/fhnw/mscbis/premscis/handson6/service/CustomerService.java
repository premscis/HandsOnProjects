package ch.fhnw.mscbis.premscis.handson6.service;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.annotation.ProcessVariable;
import ch.fhnw.mscbis.premscis.handson6.domain.Customer;
import ch.fhnw.mscbis.premscis.handson6.ejb.CustomerEJB;

@Named
public class CustomerService {

	@Inject
	@ProcessVariable
	public Object customerId;

	@Inject
	private BusinessProcess businessProcess;

	@EJB
	CustomerEJB customerEJB;

	public void loadCustomer() {
		Customer customer = customerEJB.findCustomerById(Long
				.valueOf((String) customerId));
		if (customer != null) {
			businessProcess.setVariable("customerFirstName", customer.getFirstName());
			businessProcess.setVariable("customerLastName", customer.getLastName());
		}
	}

}
