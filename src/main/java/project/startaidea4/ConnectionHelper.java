package project.startaidea4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	
	private String url;
	private static ConnectionHelper instance;

	private ConnectionHelper()
	{
    	String driver = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			url = "jdbc:mysql://localhost/tools?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&"
					+ "user=root&password=admin";
            driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//user=root&password=admin
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(instance.url);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
