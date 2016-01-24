package ancestry.zookeeper.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import ancestry.zookeeper.model.Animal;
import ancestry.zookeeper.model.Zoo;

public class DBOperations {
	private Zoo zoo;
	private Animal animal;

	public DBOperations() {
		zoo = new Zoo();
		animal = new Animal();
	}

	public static void addNewQuantity(int zooId, double newQuantity) {
		String updateQuery = "UPDATE zoo SET new_inventory = new_inventory+" + newQuantity + " WHERE zooid = " + zooId;
		Connection con = (Connection) DBConnectivity.getInstance();
		try {
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(updateQuery);
			preparedStmt.execute();
			System.out.println("New shipment has been updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
				updateQueries.add(updateQuery);
			}
			for (String updateQuery : updateQueries) {
				PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(updateQuery);
				preparedStmt.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
