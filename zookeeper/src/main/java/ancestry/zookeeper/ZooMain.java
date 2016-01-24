package ancestry.zookeeper;

import java.util.Scanner;

import ancestry.zookeeper.db.DBOperations;

/**
 * Main class for implementation
 *
 */
public class ZooMain {

	private static int zooId;
	private static Scanner in;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		boolean isExit = false;
		int choice1, choice2;
		System.out.print("Enter Zoo ID: ");
		zooId = in.nextInt(); // Get the zoo id. Acts as logging in to this zoo.
		while (!isExit) {
			System.out.println("Menu\n1. Update feed inventory\n2. Get stats\n3. Exit\nEnter your choice");
			choice1 = in.nextInt();
			switch (choice1) {
			case 1:
				System.out.println("Options\n1. Add feed shipments to new inventory\n2. Record feed times for each animal\n3. Replace running with new inventory"
						+ "\nEnter your choice");
				choice2 = in.nextInt();
				performInventoryUpdate(choice2);
				break;
			case 2:
				System.out.println("Options\n1. How	much food is wasted per	zoo?\n2. Which species of animal at which zoos are being fed above/below "
						+ "average(by species) by some percentage?\nEnter your choice");
				choice2 = in.nextInt();
				getStats(choice2);
				break;
			case 3:
				isExit = true;
				System.out.println("Bye.");
				break;
			default:
				System.out.println("Enter a number from 1-3");
				break;
			}
		}
	}

	private static void performInventoryUpdate(int choice) {
		switch (choice) {
		case 1:
			System.out.print("Enter feed shipment quantity: ");
			double newQuantity = in.nextInt();
			DBOperations.addNewQuantity(zooId, newQuantity);
			break;
		case 2:
			DBOperations.recordFeedTime(zooId, in);
			break;
		case 3:
			DBOperations.replaceInventory(zooId);
			break;
		default:
			System.out.println("Incorrect option chosen!");

		}
	}

	private static void getStats(int choice) {
		switch (choice) {
		case 1:
			DBOperations.getFoodWastagePerZoo();
			break;
		case 2:
			System.out.println("Enter percentage value: ");
			double percent = in.nextDouble();
			System.out.println("Animals above (1) or below (2) this percent?:");
			int isAboveOrBelow = in.nextInt();
			DBOperations.getAnimalFeedStat(zooId, percent, isAboveOrBelow);
			break;
		default:
			System.out.println("Incorrect option chosen!");
		}
	}
}
