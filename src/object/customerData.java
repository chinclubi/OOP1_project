package object;

public class customerData {
	private String customerID, username, password,customerType, firstname, surname, email,
			phonenumber;

	public customerData(String customerID, String user, String password,String customerType,
			String name, String lastname, String email, String tel) {
		this.username = user;
		this.password = password;
		this.customerType = customerType;
		this.firstname = name;
		this.surname = lastname;
		this.email = email;
		this.phonenumber = tel;
		this.customerID = customerID;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getName() {
		return this.firstname;

	}
	public String getUser() {
		return this.username;
	}

	public String getTel() {

		return this.phonenumber;
	}

	public String getLastName() {
		return this.surname;
	}

	public String getPassword() {

		return this.password;
	}

	public String getEmail() {

		return this.email;

	}
	public String getCustomerID(){
		return this.customerID;
	}

	public String toString() {

		String format = "%s %s %s %s %s %s %s %s";
		String studentStr = String.format(format, getCustomerID(), getUser(), getPassword(),getCustomerType(),
				getName(), getLastName(), getEmail(), getTel());
		return studentStr;
	}

}
