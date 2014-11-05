package BBDD2.trabajo.singleton;

import service.MasterService;

public class ServiceFactory {

	private static MasterService masterService;
	
	public static MasterService getMasterService(){
		if (masterService == null)
			masterService = new MasterService();
		return masterService;
	}
}
