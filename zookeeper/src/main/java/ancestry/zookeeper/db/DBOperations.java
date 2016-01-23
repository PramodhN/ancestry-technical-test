package ancestry.zookeeper.db;

import ancestry.zookeeper.model.Animal;
import ancestry.zookeeper.model.Zoo;

public class DBOperations {
	private Zoo zoo;
	private Animal animal;

	public DBOperations() {
		zoo = new Zoo();
		animal = new Animal();
	}

}
