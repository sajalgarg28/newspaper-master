package hawkerboard;

public class UserBean {

	String name,mobile1,mobile2,salary,address;
	public UserBean() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserBean(String name, String mobile1, String mobile2, String salary, String address) {
		super();
		this.name = name;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.salary = salary;
		this.address = address;
	}
	
	
}
