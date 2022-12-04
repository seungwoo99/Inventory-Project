package Inventory;

public class customer {
	String ssn, fullName, email, password;
	
	public customer(String ssn, String fullName, String email, String password) {
		this.ssn = ssn;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
	
	//setter methods
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public void setFullname(String fullName) {
		this.fullName = fullName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//getter methods
	public String getSsn() {
		return ssn;
	}
	public String getFullName() {
		return fullName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
}
