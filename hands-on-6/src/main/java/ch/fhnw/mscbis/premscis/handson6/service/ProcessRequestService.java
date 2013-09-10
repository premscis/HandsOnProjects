/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.fhnw.mscbis.premscis.handson6.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.annotation.ProcessVariable;

import ch.fhnw.mscbis.premscis.handson6.domain.Customer;
import ch.fhnw.mscbis.premscis.handson6.domain.Loan;
import ch.fhnw.mscbis.premscis.handson6.ejb.CustomerEJB;


@Named
public class ProcessRequestService {
  
    @Inject
	@ProcessVariable
	public Object customerId;
    
    @Inject
	@ProcessVariable
	public Object amount;

	@Inject
	private BusinessProcess businessProcess;

	@EJB
	CustomerEJB customerEJB;

    public boolean processRequest()
    {
    	Customer customer = customerEJB.findCustomerById(Long
				.valueOf((String) customerId));
    	
    	Loan loan = new Loan();
    	loan.setLoanType("default type");
    	loan.setTerm("2 years");
    	loan.setAmount(Long.parseLong(amount.toString()));
    	List<Loan> loans = new ArrayList<Loan>();
    	loans.add(loan);
    	customer.setLoan(loans);
    	customerEJB.updateCustomer(customer);
        return true;
    }

}

