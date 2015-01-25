package object;

import java.util.ArrayList;

public class Cart {
	private int itemID;
	private int numItemGet;
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getNumItemGet() {
		return numItemGet;
	}
	public void setNumItemGet(int numItemGet) {
		this.numItemGet = numItemGet;
	}
	public Cart(int ItemID,int num){
		this.itemID = ItemID;
		this.numItemGet = num;
	}
	public String toString() {
		return "Cart [itemID=" + itemID + ", numItemGet=" + numItemGet + "]";
	}
	public void addNumItem(){
		this.numItemGet++;
	}
	public void removeNumItem(){
		if(numItemGet!=0){
			this.numItemGet--;
		}
	}
}
