
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
	public static Connection connect = null;
	ResultSet temp = null;
	private String sqlusername = "root";
	private String sqlpassword = "123456";
	private String database = "consortium";

	public DBController() throws SQLException {
		connect = DriverManager.getConnection("jdbc:mysql://localhost/" + database , sqlusername, sqlpassword);
		if(connect == null){
			System.out.println("No Connection to "+database+" datebase.");
		}else{
			System.out.println("Connected to "+database+" datebase.");
		}
	}
	
	public ResultSet queryDB(String query) throws SQLException{
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	
	public int updateQueryDB(String query) throws SQLException{
		PreparedStatement stmt = connect.prepareStatement(query);
		int temp = stmt.executeUpdate();
		return temp;
	}
}
