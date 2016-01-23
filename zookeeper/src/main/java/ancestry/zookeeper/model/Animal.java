package ancestry.zookeeper.model;

public class Animal {
	private int animalId;
	private int zooId;
	private String species;
	private int feedingTime;
	private double quantity;

	public Animal(int animalId, int zooId, String species, int feedTime, double quantity) {
		this.animalId = animalId;
		this.zooId = zooId;
		this.species = species;
		this.setFeedingTime(feedTime);
		this.quantity = quantity;
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public int getZooId() {
		return zooId;
	}

	public void setZooId(int zooId) {
		this.zooId = zooId;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public int getFeedingTime() {
		return feedingTime;
	}

	public void setFeedingTime(int feedingTime) {
		this.feedingTime = feedingTime;
	}
}
