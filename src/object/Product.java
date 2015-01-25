package object;

public class Product {
	private String name;
	private double price;
	private int itemID;
	public Product(int ItemID,String name,double price){
		this.setItemID(ItemID);
		this.setName(name);
		this.setPrice(price);
	}
	public int getItemID() {
		return this.itemID;
	}
	public void setItemID(int ID) {
		this.itemID = ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}


