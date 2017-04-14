package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import fitec.dba.hbn.HbnDaoEditeur;
import fitec.dba.hbn.HbnDaoLivre;
import fitec.dba.metier.Auteur;
import fitec.dba.metier.Editeur;
import fitec.dba.metier.Livre;
import fitec.dba.metier.User;

public class TestHbnLivre {
private static Integer counter = 0 ;
	
	private HbnDaoLivre hbn ;
	
	@Before
	public void setUp() throws Exception {
		counter++;
		hbn = new HbnDaoLivre();
	}
	
	@Test
	public void testHbn1_Create() {
		System.out.println("test hbn1 : creation livre puis update livre puis delete livre");
		
		Livre l = new Livre(null, "livre 2", new Auteur(1, null, null), new Editeur(1, null), 50, (float)23.2);

		// insert
		assertNotNull("La DAO Livre n'est pas instanciée !",hbn);
		
		int insertedId = hbn.insert(l);
		
		assertTrue("creation livre KO", insertedId >= 1);
		
		// select by id
		Livre temp = new Livre();
		temp.setId(insertedId);
		Livre insertedLivre = hbn.selectById(temp);
		
		assertNotNull("Le livre tout juste créé n'a pas été trouvé", insertedLivre);
		assertTrue("Le livre trouvé n'est pas le bon", insertedLivre.getTitre().equals(l.getTitre()));
		
		System.out.println("Insert Livre OK");
		
		// update
		insertedLivre.setTitre("livre 3");
		boolean bUpdate = hbn.update(insertedLivre);
		
		assertTrue("update livre KO", bUpdate = true);
		
		Livre updatedLivre = hbn.selectById(temp);
		assertTrue("l'update du titre n'a pas fonctionné", updatedLivre.getTitre().equals("livre 3"));
		
		System.out.println("Update Livre OK");
		
// 		update bis (test d'update sur un id_editeur inexistant):
//		System.out.println("\nTest UPDATE-BIS - editeur non référencé :\n-------------");
//		Livre livreSansEditeur = new Livre(1, null, new Auteur(2, null, null), new Editeur(123, null), 50, (float)23.2);
//		boolean updateBis = hbn.update(livreSansEditeur);
//		assertTrue("update-bis KO", updateBis);		
		
		// search like
		List<Livre> listeLivres = hbn.searchLike("Potter");
		System.out.println("Résultat de search like : " + listeLivres.size());
		listeLivres.forEach(x -> System.out.println(x.getTitre()));
		
		// delete
		boolean bDelete = hbn.delete(insertedLivre);
		
		assertTrue("delete livre KO", bDelete = true);
		
		System.out.println("Delete Livre OK");
	}
	
	
	
}
