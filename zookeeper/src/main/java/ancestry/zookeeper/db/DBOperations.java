package ancestry.zookeeper.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to perform the operations on the database
 * 
 * @author Pramodh
 *
 */
public class DBOperations {

	/**
	 * This method adds the new feed quantity to the new inventory
	 * 
	 * @param zooId
	 *            the zoo in which new quantity arrived
	 * @param newQuantity
	 *            the weight of new quantity
	 */
	public static void addNewQuantity(int zooId, double newQuantity) {
		String updateQuery = "UPDATE zoo SET new_inventory = new_inventory+" + newQuantity + " WHERE zooid = " + zooId;
		performUpdate(updateQuery);
		System.out.println("New shipment has been updated!");
	}

	/**
	 * This method records feed time and quantity for each animal in a given zoo
	 * 
	 * @param zooId
	 *            the zoo in which feed time and quantity needs to be updated
	 * @param in
	 *            scanner object to read inputs
	 */
	public static void recordFeedTime(int zooId, Scanner in) {
		String selectAnimalsQuery = "SELECT * FROM animal WHERE zooid = " + zooId;
		Connection con = (Connection) DBConnectivity.getInstance();
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectAnimalsQuery);
			String animalId, species;
			ArrayList<String> updateQueries = new ArrayList<String>();
			while (rs.next()) {
				animalId = rs.getString("animalid");
				species = rs.getString("species");
				System.out.println("Enter feed time for " + species + "(id:" + animalId + "): ");
				int feedTime = in.nextInt();
				System.out.println("Enter feed quantity for " + species + "(id:" + animalId + "): ");
				double feedQty = in.nextDouble();
				String updateQuery = "UPDATE animal SET feedtime = " + feedTime + ", quantity = " + feedQty + " WHERE animalId = " + animalId;
				updateQueries.add(updateQuery); // Create set of update queries which will be executed after this loop
			}
			for (String updateQuery : updateQueries) {
				PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(updateQuery);
				preparedStmt.execute();
			}
			System.out.println("Feed times recorded");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method empties new inventory and adds it to running inventory
	 * 
	 * @param zooId
	 *            zoo in which inventory replacement needs to happen
	 */
	public static void replaceInventory(int zooId) {
		String updateQuery = "UPDATE zoo SET running_inventory = running_inventory + new_inventory, new_inventory = 0 WHERE zooid = " + zooId;
		performUpdate(updateQuery);
		System.out.println("Inventory Updated");
	}

	/**
	 * This method gets a string which basically is a query and executes it
	 * 
	 * @param updateQuery
	 *            the query to be executed
	 */
	private static void performUpdate(String updateQuery) {
		Connection con = (Connection) DBConnectivity.getInstance();
		try {
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(updateQuery);
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets food wastage amount per zoo
	 */
	public static void getFoodWastagePerZoo() {
		String selectZooQuery = "SELECT * FROM zoo";
		Connection con = (Connection) DBConnectivity.getInstance();
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectZooQuery);
			while (rs.next()) {
				double wastage = rs.getDouble(4) - rs.getDouble(3);
				System.out.println("Zoo: " + rs.getString(1) + ", Wastage: " + wastage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets animals whose feed quantity is above or below given percentage of the average in a zoo
	 * 
	 * @param zooId
	 *            zoo in which this stat is required
	 * @param percent
	 *            percent value
	 * @param isAboveOrBelow
	 *            above = 1 below = 2
	 */
	public static void getAnimalFeedStat(int zooId, double percent, int isAboveOrBelow) {
		String selectAnimalsQuery = "SELECT * FROM animal WHERE zooid = " + zooId;
		Connection con = (Connection) DBConnectivity.getInstance();
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectAnimalsQuery);
			double sum = 0.0;
			int total = 0;
			while (rs.next()) {
				sum += rs.getDouble(5);
				total++;
			}
			double average = sum / total;
			double comparisonValue = 0.0;
			String selectFinalAnimalsQuery = "";
			if (isAboveOrBelow == 1) {
				comparisonValue = average + (average * percent / 100);
				selectFinalAnimalsQuery = "SELECT species FROM animal WHERE quantity >= " + comparisonValue + " AND zooid = " + zooId;
				System.out.println("Species with feed above average and given percent:");
			} else if (isAboveOrBelow == 2) {
				comparisonValue = average - (average * percent / 100);
				selectFinalAnimalsQuery = "SELECT species FROM animal WHERE quantity <= " + comparisonValue + " AND zooid = " + zooId;
				System.out.println("Species with feed below average and given percent:");
			} else
				// if above or below value is neither, then stop execution
				return;
			rs = statement.executeQuery(selectFinalAnimalsQuery);

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
