package entity;

public class User {
	private int userId;
	private String username;
	private String password;
	private String userType;
	private String name;

	public User(int userId, String username, String password, String userType,String name) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.name = name;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String displayUserDetail(){
		return userId + "\t\t" + username + "\t\t\t" + name;
	}

}
