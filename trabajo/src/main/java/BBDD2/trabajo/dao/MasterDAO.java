package BBDD2.trabajo.dao;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import BBDD2.trabajo.model.Master;
import BBDD2.trabajo.singleton.Hbutil;

public class MasterDAO {

	public void save(Master master) {
		Session session = Hbutil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		try {
			session.replicate(master, ReplicationMode.EXCEPTION);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			session.close();
		}
	}

	public Master get(long id) {
		Session session = Hbutil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Master instance = null;
		tx = session.beginTransaction();
		try {
			instance = (Master) session.get(Master.class, id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			session.close();
		}
		return instance;
	}

	public void update(Master master) {
		Session session = Hbutil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		try {
			session.saveOrUpdate(master);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			session.close();
		}
	}
}