package ancestry.zookeeper.model;

public class Inventory {
	private double runningInventory;
	private double newInventory;
	public double getRunningInventory() {
		return runningInventory;
	}
	public void setRunningInventory(double runningInventory) {
		this.runningInventory = runningInventory;
	}
	public double getNewInventory() {
		return newInventory;
	}
	public void setNewInventory(double newInventory) {
		this.newInventory = newInventory;
	}
}
