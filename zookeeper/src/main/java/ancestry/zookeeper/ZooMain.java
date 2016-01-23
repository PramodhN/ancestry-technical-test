package ancestry.zookeeper;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ZooMain {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean isExit = false;
		int choice1, choice2;
		while (!isExit) {
			System.out.println("Menu\n1. Update feed inventory\n2. Get stats\n3. Exit\nEnter your choice");
			choice1 = in.nextInt();
			switch (choice1) {
			case 1:
				System.out.println("Options\n1. Add feed shipments to new inventory\n2. Record feed times for each animal\n3. Replace running with new inventory \n4. Back "
						+ "to main menu");
				choice2 = in.nextInt();
				break;
			case 2:
				System.out.println("Options\n1. How much was each individual animal fed per day on average?\n2. How many times per day are animals fed on average?"
						+ " Group by species.\n3. How	much food is wasted per	zoo?\n4. Which species of animal at which zoos are being fed above/below "
						+ "average(by species) by some percentage?");
				choice2 = in.nextInt();
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
}
