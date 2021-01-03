package paymentboard;

public class UserBean {

	String area,name,mobile,amount,billdate;
	public UserBean() {
		
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
	public UserBean(String area, String name, String mobile, String amount, String billdate) {
		super();
		this.area = area;
		this.name = name;
		this.mobile = mobile;
		this.amount = amount;
		this.billdate = billdate;
	}
}
