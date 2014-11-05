package BBDD2.trabajo.singleton;

import BBDD2.trabajo.service.MasterService;

public class ServiceFactory {

	private static MasterService masterService;

	public static MasterService getMasterService() {
		if (masterService == null)
			masterService = new MasterService();
		return masterService;
	}
}