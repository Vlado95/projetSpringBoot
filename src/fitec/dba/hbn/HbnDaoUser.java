package fitec.dba.hbn;

import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;

import fitec.dba.dao.IDao;
import fitec.dba.factory.HbnFactory;
import fitec.dba.metier.User;

public class HbnDaoUser implements IDao<User> {

	private Session session ;
	
	public Session getSession() {
		if ( session == null ){
			session = HbnFactory.getSession();
		}
		return session;
	}
	
	@Override
	public List<User> selectAll() {
		Session session = getSession();
		String req = "From User";
		List<User> users = session.createQuery(req).getResultList();
		return users;
	}

	@Override
	public ResultSet selectAllByResultSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectById(User objet) {
		Session session = getSession();
		String req = "From User u where id = :id";
		objet = (User) session.createQuery(req).setParameter("id", objet.getId()).getSingleResult();
		return objet;
	}

	@Override
	public int insert(User objet) {
		Session session = getSession();
		session.getTransaction().begin();		
		session.saveOrUpdate(objet);
		session.getTransaction().commit();
		return objet.getId();
	}

	@Override
	public boolean update(User objet) {
		Session session = getSession();
		session.getTransaction().begin();
		session.saveOrUpdate(objet);
		session.flush();
		session.getTransaction().commit();
		return (objet != null?true:false);
	}

	@Override
	public boolean delete(User objet) {
		Session session = getSession();
		session.getTransaction().begin();
		objet = session.find(User.class, objet.getId());
		
		try {
			session.delete(objet);
		} catch (Exception e) {
			return false;
		}
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<User> searchLike(String str) {
		 Session session = getSession();
		 String req = "From User as u where u.nom like '%"+str+"%' ";
		 List<User> users = session.createQuery(req).getResultList();
		return users;
	}
}
