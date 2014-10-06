package BBDD2.trabajo.singleton;

import org.hibernate.Session;
import org.hibernate.Transaction;
import BBDD2.trabajo.hibernate.Hbutil;
import BBDD2.trabajo.model.Master;

public class MasterContainer {

	private Master master;
	private static MasterContainer instance;

	public MasterContainer(Master master) {
		super();
		this.master = master;
	}

	public static MasterContainer getInstance(){
		if (instance == null){
			Session session = Hbutil.getSessionFactory().openSession();
			Transaction tx = null;
			try{
				tx = session.beginTransaction();
				long id = 1;
				instance = new MasterContainer((Master)session.get(Master.class, id));
				tx.commit();
			}catch (Exception e){
				e.printStackTrace();
				if (tx != null)
					tx.rollback();
				session.close();
			}
		}
		return instance;
	}
	
	public Master getMaster(){
		return this.master;
	}
}