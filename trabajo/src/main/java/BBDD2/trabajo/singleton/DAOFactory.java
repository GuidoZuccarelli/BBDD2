package BBDD2.trabajo.singleton;

import BBDD2.trabajo.dao.MasterDAO;

public class DAOFactory {

	private static MasterDAO masterDao;

	public static MasterDAO getMasterDAO() {
		if (masterDao == null)
			masterDao = new MasterDAO();
		return masterDao;
	}
}