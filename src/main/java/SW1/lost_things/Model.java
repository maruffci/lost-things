package SW1.lost_things;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Model {
	private Connection connection;
	
	public Model() {
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root&password=");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	
	public String table_info() {
		PreparedStatement statement = null;
		ResultSet result = null;
		String resp = "";
		ResultSetMetaData rsmd;
		try {
			statement = this.connection.prepareStatement("SHOW TABLES;");
			result = statement.executeQuery();
			rsmd = result.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (result.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = result.getString(i);
			        resp += columnValue + " " + rsmd.getColumnName(i);
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
}
