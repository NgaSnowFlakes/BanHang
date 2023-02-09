package JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;


public class XJdbcHelper {
	private final String serverName = "localhost";
	private final String dbName = "Wish";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "songlong";
	
	public Connection getConnection() throws Exception {
		
		
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName + "; encrypt=false" ;
		if(instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName+ ";encrypt=false ";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
				
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new XJdbcHelper().getConnection());
	}
}
