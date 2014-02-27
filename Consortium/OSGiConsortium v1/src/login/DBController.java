package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class DBController {
	private Connection connect = null;
	//private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	//private ResultSet resultSet = null;
	
	//private String sqlusername = "mympuco2_psd3usr";
	//private String sqlpassword = "situog2014";
	//private String database = "mympuco2_psd3sprint";
	
	private String sqlusername = "root";
	private String sqlpassword = "891636";
	private String database = "consortium";
	
	public DBController() throws SQLException{
		// try {
			//Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/"+database, sqlusername, sqlpassword);
		//} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
	}
	
	
	public User UserLogin(String username, String password) throws Exception {
		User user = null;
		String query = "SELECT * FROM user WHERE username=? AND password=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			user = new User(rs.getInt("userId"),rs.getString("username"),rs.getString("password"),rs.getString("usertype"),rs.getString("name"));
		}
		stmt.close();
		//close();
		return user;
	}
	
	public void close() {
		try {
			/*if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			*/
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
