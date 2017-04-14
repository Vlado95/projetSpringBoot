package Tests;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fitec.dba.hbn.HbnDaoAuteur;
import fitec.dba.metier.Auteur;
import fitec.dba.metier.Livre;

public class TestHbnAuteur {

	private static Integer counter = 0 ;
	
	private HbnDaoAuteur hbn ;
	
	@Before
	public void setUp() throws Exception {
		counter++;
		hbn = new HbnDaoAuteur();
	}

	@Test
	public void testAuteur1() {

		System.out.println("TEST No:"+counter);
		/**
		 * Instanciation correct de la DAO
		 */
		assertNotNull("DAO non créée",hbn);
		
		
		/********* SelectAll ************/
		System.out.println("SelectAll");
		List<Auteur> auteurs = hbn.selectAll();

		for (Auteur a : auteurs) {

			System.out.println("Auteur"+a);
			System.out.println("********************");
			for (Livre livres : a.getLivres()) {
				System.out.println("---->"+a.getLivres());
			}

		}
		
		/***** Insert ********/
		System.out.println("Insert");
		Auteur a1 = new Auteur(null, "Prénom", "XXX TEST Saint-honore");
		
		Integer id = hbn.insert(a1);
		
		assertTrue("Id non récupéré, aprés Insert ",id != 0);
		
		/****** Update Sur ID *******/
		System.out.println("Update id("+id+") ");
		a1.setId(id);
		a1.setNom("XXX UPDATE Saint-honore");
		hbn.update(a1);
		
		/****** Select ID Sur ID *******/
		
		Auteur auteur = hbn.selectById(a1);
		System.out.println("selectById : Auteur trouvé :"+auteur);

		/************ Delete **********/
		System.out.println("Delete I");
		Auteur e  = null ;
		e = hbn.selectById(auteur);
		try {
			 hbn.delete(e);
		} catch (Exception e4) {
			 System.out.println("Suppression impossible!"+e4.getLocalizedMessage());
		}

		/************ Delete **********/
		System.out.println("Delete II");

		try {
			 hbn.delete(e);
		} catch (Exception e4) {
			 System.out.println("Suppression impossible !"+e4.getLocalizedMessage());
		}
		/******** SearchLike *********/
		System.out.println("SearchLike");
		List<Auteur> edits = hbn.searchLike("T");
		System.out.println(edits.size());
		for (Auteur a : edits) {
			System.out.println(a);
		}

	}
	
	@Test
	public void testAuteur2() {
		System.out.println("DEBUT TES No:"+counter);
		
	}

}

// auteurs.forEach(e->(System.out.println());