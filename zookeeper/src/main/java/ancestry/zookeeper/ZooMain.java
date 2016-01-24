package ancestry.zookeeper;

import java.util.Scanner;

import ancestry.zookeeper.db.DBOperations;

/**
 * Hello world!
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
		zooId = in.nextInt();
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
				System.out.println("Options\n1. How much was each individual animal fed per day on average?\n2. How many times per day are animals fed on average?"
						+ " Group by species.\n3. How	much food is wasted per	zoo?\n4. Which species of animal at which zoos are being fed above/below "
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
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			System.out.println("Incorrect option chosen!");
		}
	}
}
