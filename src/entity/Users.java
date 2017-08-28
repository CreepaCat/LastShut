package entity;

public class Users {
	private String username;
	private String password;
	private int uid;
	
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
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Users(){
		
	}
	public Users(int uid, String username, String password) {
		//super();
		this.uid = uid;
		this.username = username;
		this.password = password;
	}


}
