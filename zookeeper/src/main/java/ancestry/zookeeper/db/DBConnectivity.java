package ancestry.zookeeper.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectivity {

	private static Connection con = null;

	protected DBConnectivity() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:MySQL://localhost:3306/orbitbus", "root", "qwerty");
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
