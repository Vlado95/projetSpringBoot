package fitec.dba.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fitec.dba.dao.IDao;
import fitec.dba.metier.Auteur;
import fitec.dba.metier.Editeur;
import fitec.dba.metier.Livre;
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
	    
	    
	    
	    //private static HbnDaoUser hbnDaoUser;
	    private static IDao<User> daoUser;
	    
	    private static IDao<Livre> daoLivre;
	    
	    private static IDao<Editeur> daoEditeur;
	    
	    private static IDao<Auteur> daoAuteur;
	    
		
	

		public static IDao<User> getDaoUser() {
			return daoUser;
		}

		public static void setDaoUser(IDao<User> daoUser) {
			HbnFactory.daoUser = daoUser;
		}

		public static IDao<Livre> getDaoLivre() {
			return daoLivre;
		}

		public static void setDaoLivre(IDao<Livre> daoLivre) {
			HbnFactory.daoLivre = daoLivre;
		}

		public static IDao<Editeur> getDaoEditeur() {
			return daoEditeur;
		}

		public static void setDaoEditeur(IDao<Editeur> daoEditeur) {
			HbnFactory.daoEditeur = daoEditeur;
		}

		public static IDao<Auteur> getDaoAuteur() {
			return daoAuteur;
		}

		public static void setDaoAuteur(IDao<Auteur> daoAuteur) {
			HbnFactory.daoAuteur = daoAuteur;
		}
		
		
		

		public static IDao<?> getDAO(DaoMetier deoMetier) {

			IDao<?> dao = null;
			
			//deoMetier.L
			
			switch(deoMetier)
	    	{
		    	case User:
		    		dao = daoUser;
		    		break;
		    		
		    	case Editeur:
		    		dao = daoEditeur;
		    		break;
		    	
		    	case Auteur:
		    		dao = daoAuteur;
		    		break;
		    		
		    	case Livre:
		    		dao = daoLivre;
		    		break;
	    		
	    	}
			return dao;
		}
	}
