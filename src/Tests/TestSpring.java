package Tests;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fitec.dba.factory.HbnFactory;
import fitec.dba.metier.User;
import junit.framework.TestCase;

public class TestSpring extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	ApplicationContext context;

	@Test
	public void test() {
		ApplicationContext  context = new ClassPathXmlApplicationContext("spring-config.xml");
		HbnFactory hbn =(HbnFactory) context.getBean("HbnFactory");
		User user = hbn.authenticate("vendeuse@gmail.com", "mdp");
		
		if(user != null && ! user.getId().equals(0)){
			System.out.println("User "+user+" CONNECTED !!");	
		}else{
			System.out.println("ERROR CONNECTION: "+user);
		}
	}

}
