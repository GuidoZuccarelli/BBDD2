package BBDD2.trabajo.singleton;

import BBDD2.trabajo.model.Master;

public class MasterContainer {

	private Master master;
	private static MasterContainer instance;
	
	public static MasterContainer getInstance(){
		if (instance == null){
			
		}
		return instance;
	}
}
