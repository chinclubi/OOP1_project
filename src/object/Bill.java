package object;
import java.util.ArrayList;
import java.util.Date;
public class Bill {
	private int billNumber,customerID;
	private double discountPercent,billTotal;
	private Date date;
	private ArrayList<Cart> Cart = new ArrayList<Cart>();
	public Bill(int billNumber,int customerID,ArrayList<Cart> Cart){
		this.billNumber = billNumber;
		this.customerID = customerID;
		this.Cart = Cart;
		this.discountPercent = 0;
		this.billTotal = 0;
		this.date = new Date();
	}
	public Bill(int billNumber,int customerID,Date date,double totalPrice,ArrayList<Cart> Cart){
		this.billNumber = billNumber;
		this.customerID = customerID;
		this.Cart = Cart;
		this.discountPercent = 0;
		this.billTotal = totalPrice;
		this.date = date;
	}
	public ArrayList<Cart> getCart(){
		return Cart;
	}
	public ArrayList<Cart> AddCart(){
		return Cart;
	}
	public int getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String customerTypr) {
		if(customerTypr.equals("1"))
			discountPercent = 10;
		else if(customerTypr.equals("2"))
			discountPercent = 5;
		else if(customerTypr.equals("isADMIN"))
			discountPercent = 90;
		else
			discountPercent = 0;
	}
	public double getBillTotal() {
		return billTotal;
	}
	public void setBillTotal(double billTotal) {
		this.billTotal = billTotal;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void addBillTotal(double price){
		this.billTotal += price;
	}
	public String toString(){
		String format = "%d#%d#%s#%.2f";
		for(int i=0;i<Cart.size();i++){
			format += String.format("#%d_%d",Cart.get(i).getItemID(),Cart.get(i).getNumItemGet());
		}
		String billStr = String.format(format, getBillNumber(), getCustomerID(), getDate(),
				getBillTotal());
		return billStr;
	}
}

