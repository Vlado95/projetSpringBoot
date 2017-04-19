package fitec.dba.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fitec.dba.dao.IDao;
import fitec.dba.hbn.HbnDaoAuteur;
import fitec.dba.hbn.HbnDaoEditeur;
import fitec.dba.hbn.HbnDaoLivre;
import fitec.dba.hbn.HbnDaoUser;
import fitec.dba.metier.User;

public class HbnFactory {

	    private static SessionFactory sessionFactory ;

		private static Session session ;

		public static Session getSession() {
			if ( session == null ){
				session = sessionFactory.openSession();
			}
			return session;
		}
		
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    
	    
	    
	    public static void setSessionFactory(SessionFactory sessionFactory) {
			HbnFactory.sessionFactory = sessionFactory;
		}

		public static SessionFactory getSessionFactory() {   	
	        return sessionFactory;
	    }

	    
	    /**
	     * Check Authentification for a Application User
	     * @param email
	     * @param password
	     * @return : User if LOGIN/PASSWORD Checked
	     */
	    public static User authenticate(String email, String password){
	    	Session session = getSession();    	
	    	String sql = "From User u where u.email = :email and u.password = :password";    	
	    	User user = null;
			try {
				user = (User) session.createQuery(sql).setParameter("email", email)
							.setParameter("password", password).getSingleResult();
			} catch (Exception e) {
				System.err.println("Error LOGIN/PASSWORD ..."+e.getLocalizedMessage());
			}
	    	
	    	return user;
	    }
	    
	    
	    
	    
	    public enum DaoMetier  {Livre, Auteur, Editeur, User};
	    
	    
	    
	    private static HbnDaoUser hbnDaoUser;
	    
	    private static HbnDaoLivre hbnDaoLivre;
	    
	    private static HbnDaoEditeur hbnDaoEditeur;
	    
	    private static HbnDaoAuteur hbnDaoAuteur;
	    
	    

		public static HbnDaoUser getHbnDaoUser() {
			return hbnDaoUser;
		}

		public static void setHbnDaoUser(HbnDaoUser hbnDaoUser) {
			HbnFactory.hbnDaoUser = hbnDaoUser;
		}

		
		
		
		public static HbnDaoLivre getHbnDaoLivre() {
			return hbnDaoLivre;
		}

		public static void setHbnDaoLivre(HbnDaoLivre hbnDaoLivre) {
			HbnFactory.hbnDaoLivre = hbnDaoLivre;
		}

		public static HbnDaoEditeur getHbnDaoEditeur() {
			return hbnDaoEditeur;
		}

		public static void setHbnDaoEditeur(HbnDaoEditeur hbnDaoEditeur) {
			HbnFactory.hbnDaoEditeur = hbnDaoEditeur;
		}

		public static HbnDaoAuteur getHbnDaoAuteur() {
			return hbnDaoAuteur;
		}

		public static void setHbnDaoAuteur(HbnDaoAuteur hbnDaoAuteur) {
			HbnFactory.hbnDaoAuteur = hbnDaoAuteur;
		}

		public static IDao<?> getDAO(DaoMetier deoMetier) {

			//enum dao = DaoMetier;
			IDao<?> dao = null;
			
			//deoMetier.L
			
			switch(deoMetier)
	    	{
		    	case User:
		    		dao = hbnDaoUser;
		    		break;
		    		
		    	case Editeur:
		    		dao = hbnDaoEditeur;
		    		break;
		    	
		    	case Auteur:
		    		dao = hbnDaoAuteur;
		    		break;
		    		
		    	case Livre:
		    		dao = hbnDaoLivre;
		    		break;
	    		
	    	}
			return dao;
		}
	}
