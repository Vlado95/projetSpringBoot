package Tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fitec.dba.hbn.HbnDaoEditeur;
import fitec.dba.hbn.HbnDaoUser;
import fitec.dba.metier.Editeur;
import fitec.dba.metier.Livre;
import fitec.dba.metier.User;

public class TestDaoHbn {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test1() {

		HbnDaoEditeur hbn = new HbnDaoEditeur();
		
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
		
		/****** Update *******/
		System.out.println("Update");
//		Editeur editeur = hbn.selectById(new Editeur(4, "Tito"));
//		System.out.println(editeur.getNom());
//		editeur.setNom("ssssssssss");
//		hbn.update(editeur);
//		editeur.getNom();

		/***** Insert ********/
//		System.out.println("Insert");
//		Editeur e1 = new Editeur(null, "Saint-honore");
//		Editeur e2 = new Editeur(null, "Talis");
//		Editeur e3 = new Editeur(null, "Boite à livre");
//		hbn.insert(e1);
//		hbn.insert(e2);
//		hbn.insert(e3);

		/************ Delete **********/
//		System.out.println("Delete");
//		Editeur e = hbn.selectById(new Editeur(4, "ssssssssss"));
//		try {
//			 hbn.delete(e);
//		} catch (Exception e4) {
//			 System.out.println("Suppression impossible!"+e4.getLocalizedMessage());
//		}

		/******** SearchLike *********/
//		System.out.println("SearchLike");
//		List<Editeur> edits = hbn.searchLike("H");
//		System.out.println(edits.size());
//		for (Editeur editeur2 : edits) {
//			System.out.println(editeur2.getNom());
//		}

		HbnDaoUser hbnu = new HbnDaoUser();
		
		List<User> users = hbnu.selectAll();
		
		users.forEach(u->System.out.println(u) );
		
	}
}