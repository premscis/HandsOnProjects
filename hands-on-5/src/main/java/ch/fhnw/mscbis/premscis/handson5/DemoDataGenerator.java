package ch.fhnw.mscbis.premscis.handson5;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;

@Singleton
@Startup
public class DemoDataGenerator {
	
	@Inject
	ProcessEngine processEngine;
	
	@PostConstruct
	public void createUsers()
	{
		User singleResult = (User)processEngine.getIdentityService().createUserQuery().userId("demo").singleResult();
	    if (singleResult != null) {
	      return;
	    }

	    User user = processEngine.getIdentityService().newUser("demo");
	    user.setFirstName("Demo");
	    user.setLastName("Demo");
	    user.setPassword("demo");
	    user.setEmail("demo@camunda.org");
	    processEngine.getIdentityService().saveUser(user);

	    User user2 = processEngine.getIdentityService().newUser("john");
	    user2.setFirstName("John");
	    user2.setLastName("Doe");
	    user2.setPassword("john");
	    user2.setEmail("john@camunda.org");

	    processEngine.getIdentityService().saveUser(user2);

	    User user3 = processEngine.getIdentityService().newUser("mary");
	    user3.setFirstName("Mary");
	    user3.setLastName("Anne");
	    user3.setPassword("mary");
	    user3.setEmail("mary@camunda.org");

	    processEngine.getIdentityService().saveUser(user3);

	    User user4 = processEngine.getIdentityService().newUser("peter");
	    user4.setFirstName("Peter");
	    user4.setLastName("Meter");
	    user4.setPassword("peter");
	    user4.setEmail("peter@camunda.org");

	    processEngine.getIdentityService().saveUser(user4);

	    Group salesGroup = processEngine.getIdentityService().newGroup("sales");
	    salesGroup.setName("Sales");
	    salesGroup.setType("WORKFLOW");
	    processEngine.getIdentityService().saveGroup(salesGroup);

	    Group accountingGroup = processEngine.getIdentityService().newGroup("accounting");
	    accountingGroup.setName("Accounting");
	    accountingGroup.setType("WORKFLOW");
	    processEngine.getIdentityService().saveGroup(accountingGroup);

	    Group managementGroup = processEngine.getIdentityService().newGroup("management");
	    managementGroup.setName("Management");
	    managementGroup.setType("WORKFLOW");
	    processEngine.getIdentityService().saveGroup(managementGroup);

	    processEngine.getIdentityService().createMembership("demo", "sales");
	    processEngine.getIdentityService().createMembership("demo", "accounting");
	    processEngine.getIdentityService().createMembership("demo", "management");

	    processEngine.getIdentityService().createMembership("john", "sales");
	    processEngine.getIdentityService().createMembership("mary", "accounting");
	    processEngine.getIdentityService().createMembership("peter", "management");

	    AuthorizationService authorizationService = processEngine.getAuthorizationService();

	    Authorization salesTasklistAuth = authorizationService.createNewAuthorization(1);
	    salesTasklistAuth.setGroupId("sales");
	    salesTasklistAuth.addPermission(Permissions.ACCESS);
	    salesTasklistAuth.setResourceId("tasklist");
	    salesTasklistAuth.setResource(Resources.APPLICATION);
	    authorizationService.saveAuthorization(salesTasklistAuth);

	    Authorization accountingTasklistAuth = authorizationService.createNewAuthorization(1);
	    accountingTasklistAuth.setGroupId("accounting");
	    accountingTasklistAuth.addPermission(Permissions.ACCESS);
	    accountingTasklistAuth.setResourceId("tasklist");
	    accountingTasklistAuth.setResource(Resources.APPLICATION);
	    authorizationService.saveAuthorization(accountingTasklistAuth);

	    Authorization managementTasklistAuth = authorizationService.createNewAuthorization(1);
	    managementTasklistAuth.setGroupId("management");
	    managementTasklistAuth.addPermission(Permissions.ACCESS);
	    managementTasklistAuth.setResourceId("tasklist");
	    managementTasklistAuth.setResource(Resources.APPLICATION);
	    authorizationService.saveAuthorization(managementTasklistAuth);
	}
}
