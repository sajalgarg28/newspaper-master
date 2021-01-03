package customerboard;

public class UserBean {

	String mobile,name,address,area,hawker,paper,date;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getHawker() {
		return hawker;
	}
	public void setHawker(String hawker) {
		this.hawker = hawker;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public UserBean(String mobile, String name, String address, String area, String hawker, String paper, String date) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.address = address;
		this.area = area;
		this.hawker = hawker;
		this.paper = paper;
		this.date = date;
	}
	UserBean(){}
	
}
