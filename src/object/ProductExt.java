package object;

//elab-source: ProductExt.java
//elab-mainclass: Product
public class ProductExt extends Product {
	private int numStocks;
	private double buyingPrice,totalProfit;
	private boolean reorder;
	private String description,urlIMG,Icon;
	public ProductExt(int ItemID,String name/*,String URL,String icon*/ ,String description, int numStock, double price, double buyingPrice, boolean reorder){
		super(ItemID,name,price);
		//this.urlIMG = URL;
		//this.Icon = icon;
		//this.itemID = ItemID;
		this.description = description;
		this.numStocks = numStock;
		this.buyingPrice = buyingPrice;
		this.reorder = reorder;
	}
	public String getUrlIMG() {
		return urlIMG;
	}
	public void setUrlIMG(String urlIMG) {
		this.urlIMG = urlIMG;
	}
	
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public int getNumStock() {
		return numStocks;
	}
	public void setNumStock(int numStocks) {
		this.numStocks = numStocks;
	}
	public double getTotalProfit() {
		return super.getPrice()-this.buyingPrice;
	}
	public void setTotalProfit(double totalProfit) {
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public boolean isReorder() {
		return reorder;
	}
	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}
	public void setURL(String url){
		this.urlIMG = url;
	}
	public String getURL(){
		return this.urlIMG;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void removeFromStock(int numItems){
		this.numStocks -= numItems;
		this.totalProfit += numItems*(super.getPrice()-this.getBuyingPrice());
	}
	public void addStock(int numItems){
		this.numStocks += numItems;
		//this.totalProfit += numItems*(super.getPrice()-this.getBuyingPrice());
	}
	public void refillStock(int numItems, double buyingPrice, double salePrice){
		this.numStocks += numItems;
		if(this.buyingPrice>=0&&super.getPrice()>=0){
			this.setBuyingPrice(((buyingPrice*numItems)+(this.buyingPrice*getNumStock()))/(getNumStock()+numItems));
			super.setPrice(((salePrice*numItems)+(super.getPrice()*getNumStock()))/(getNumStock()+numItems));
		}else{
			this.setBuyingPrice(((this.buyingPrice*getNumStock()))/(numItems));
			super.setPrice(((super.getPrice()*getNumStock()))/(numItems));
		}
		if(this.getNumStock()!=0)
			this.setReorder(false);
		else
			this.setReorder(true);
	}
	public String toString(){
		String format = "%d#%s#%s#%d#%f#%f#%s";
		String productInfo = String.format(format,super.getItemID(),super.getName(),this.getDescription(),this.getNumStock(),super.getPrice(),this.getBuyingPrice(),this.isReorder());
		return productInfo;	
	}
}
