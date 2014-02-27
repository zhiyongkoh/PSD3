public abstract class User {

	int user_id;
	String username;
	String password;
	USERTYPE user_type;
	String name;
	
	public User(int user_id, String username, String password, USERTYPE user_type, USERTYPE admin, String name) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
		this.name = name;
	}
	
}
