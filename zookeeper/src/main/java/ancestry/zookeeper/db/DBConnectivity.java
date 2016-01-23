package ancestry.zookeeper.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectivity {

	private static Connection con = null;
	private final String DB_USER_NAME = "root";
	private final String DB_PASSWORD = "qwerty";

	protected DBConnectivity() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:MySQL://localhost:3306/orbitbus", DB_USER_NAME, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getInstance() {
		if (con == null) {
			new DBConnectivity();
		}
		return con;
	}
}
