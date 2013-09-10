/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.fhnw.mscbis.premscis.handson5.service;


import javax.ejb.Stateless;
import javax.inject.Named;


/**
 *
 * @author andreas.martin
 */
@Named
@Stateless
public class ProcessRequestSessionBean {
  
    public boolean processRequest(String customerId, int amount)
    {
        System.out.println("ProcessRequestSessionBean...processRequest() called! and customerId: " + customerId + " and amount: " + Integer.toString(amount));
        if (amount<=0)
        	return false;
        return true;
    }

}
