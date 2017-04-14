package Tests;
import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import fitec.dba.factory.HbnFactory;
import fitec.dba.hbn.HbnDaoEditeur;
import fitec.dba.metier.Editeur;
import fitec.dba.metier.Livre;

public class TestHbnEditeur {

	private static Integer counter = 0 ;
	
	private HbnDaoEditeur hbn ;
	
	@Before
	public void setUp() throws Exception {
		counter++;
		hbn = new HbnDaoEditeur();
	}

	@Test
	public void test1() {

		/**
		 * Instanciation correct de la DAO
		 */
		assertNotNull("DAO non créée",hbn);
		
		
		/********* SelectAll ************/
		System.out.println("SelectAll");
		List<Editeur> editeurs = hbn.selectAll();

		for (Editeur editeur : editeurs) {

			System.out.println(editeur.getNom());
			System.out.println("********************");
			for (Livre livres : editeur.getLivres()) {
				System.out.println(editeur.getLivres());
			}

		}
		
		/***** Insert ********/
		System.out.println("Insert");
		Editeur e1 = new Editeur(null, "XXX TEST Saint-honore");
		
		Integer id = hbn.insert(e1);
		
		assertNotNull("DAO non créée",id != 0);
		/****** Update Sur ID *******/
		System.out.println("Update id("+id+") ");
		e1.setId(id);
		e1.setNom("XXX UPDATE Saint-honore");
		hbn.update(e1);
		
		/****** Select ID Sur ID *******/
		
		Editeur editeur = hbn.selectById(e1);
		System.out.println("Editeur trouvé :"+editeur);

		/************ Delete **********/
		System.out.println("Delete I");
		Editeur e  = null ;
		e = hbn.selectById(editeur);
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
		List<Editeur> edits = hbn.searchLike("T");
		System.out.println(edits.size());
		for (Editeur editeur2 : edits) {
			System.out.println(editeur2.getNom());
		}

	}
}

// editeurs.forEach(e->(System.out.println());