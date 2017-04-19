package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fitec.dba.dao.DaoUser;
import fitec.dba.dao.IDao;
import fitec.dba.factory.HbnFactory;
import fitec.dba.factory.HbnFactory.DaoMetier;
import fitec.dba.hbn.HbnDaoAuteur;
import fitec.dba.metier.Auteur;
import fitec.dba.metier.User;
import junit.framework.TestCase;

public class TestSpring extends TestCase {

	private ApplicationContext context;
	HbnFactory hbn;
	@Before
	public void setUp() throws Exception {
		super.setUp();
//		ApplicationContext 
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		hbn = (HbnFactory) context.getBean("HbnFactory");
	}



	@Test
	public void test() {

		//ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

//		HbnFactory hbn = (HbnFactory) context.getBean("HbnFactory");
		IDao<User> daoUser = (IDao<User>) hbn.getDAO(DaoMetier.User);
		User user = hbn.authenticate("vendeuse@gmail.com", "mdp");

		if (user != null && !user.getId().equals(0)) {
			System.out.println("User " + user + " CONNECTED !!");
		} else {
			System.out.println("ERROR CONNECTION: " + user);
		}
		
		
		daoUser.selectAll().forEach(c -> System.out.println(c));

	}

	@Test
	public void testAuteur() {
        
		//HbnDaoAuteur daoAuteur = (HbnDaoAuteur) context.getBean("HbnDaoAuteur");
		IDao<Auteur> daoAuteur = (IDao<Auteur>) hbn.getDAO(DaoMetier.Auteur);
		List<Auteur> auteurList = daoAuteur.selectAll();

		if (!auteurList.isEmpty()) {

			for (Auteur auteur : auteurList) {
				System.out.println("auteur :" + auteur);

			}

			auteurList.forEach(c -> System.out.println(c));

		}

	}

}
