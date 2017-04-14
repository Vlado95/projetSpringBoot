package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fitec.dba.factory.HbnFactory;
import fitec.dba.hbn.HbnDaoAuteur;
import fitec.dba.metier.Auteur;
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
		
		HbnDaoAuteur daoAuteur = new HbnDaoAuteur();
		List<Auteur> auteurList = daoAuteur.selectAll();
		
		if (!auteurList.isEmpty()) {
			
			for (Auteur auteur : auteurList) {
				System.out.println("auteur :"+auteur);
				
			}
			
			auteurList.forEach(c->System.out.println(c));
			
		}
	}

}
