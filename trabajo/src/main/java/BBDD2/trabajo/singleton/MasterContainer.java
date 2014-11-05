package BBDD2.trabajo.singleton;

import BBDD2.trabajo.dao.MasterDAO;
import BBDD2.trabajo.model.Master;

public class MasterContainer {

	private Master master;
	private static MasterContainer instance;

	public MasterContainer(Master master) {
		super();
		this.master = master;
	}

	public static MasterContainer getInstance() {
		if (instance == null) {
			MasterDAO masterDao = DAOFactory.getMasterDAO();
			instance = new MasterContainer(masterDao.get(1));
			if (instance.getMaster() == null) {
				Master master = new Master();
				master.setId(1);
				instance = new MasterContainer(master);
				masterDao.save(master);
			}
		}
		return instance;
	}

	public Master getMaster() {
		return this.master;
	}
}