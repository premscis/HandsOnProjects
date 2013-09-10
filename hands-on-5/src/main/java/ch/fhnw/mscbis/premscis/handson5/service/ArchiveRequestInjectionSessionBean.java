/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.fhnw.mscbis.premscis.handson5.service;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.annotation.ProcessVariable;


/**
 *
 * @author andreas.martin
 */
@Named
@Stateless
public class ArchiveRequestInjectionSessionBean {
	
	@Inject 
	@ProcessVariable
	private Object customerId;
	
	@Inject
	@ProcessVariable
	private Object amount;
	
    public void archiveRequest()
    {
    	System.out.println("ArchiveRequestInjectionSessionBean...archiveRequest() called! and customerId: " + customerId.toString() + " and amount: " + amount.toString());
    }
    
}
