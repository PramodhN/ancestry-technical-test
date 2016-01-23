package ancestry.zookeeper.model;

public class Zoo {

	private int zooId;
	private String location;
	private Inventory inventory;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getZooId() {
		return zooId;
	}

	public void setZooId(int zooId) {
		this.zooId = zooId;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
