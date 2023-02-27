/**
 * 卒業制作
 */
package model.entity;

public class Login {
	
	private int userId;
	private String userName;
	private String password;
	
	public Login() {
		
	}
	
	public int getUserId() {
		return userId;
	}

	public Login(String useName, String password){
		this.userName = useName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
