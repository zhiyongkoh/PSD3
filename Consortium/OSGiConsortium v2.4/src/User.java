public abstract class User {

	int user_id;
	String username;
	USERTYPE user_type;
	String name;
	
	public User(int user_id, String username, USERTYPE user_type, String name) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.user_type = user_type;
		this.name = name;
	}
	
	public String displayUserDetail(){
		return user_id + "\t\t" + username + "\t\t\t" + name;
	}
	
}
