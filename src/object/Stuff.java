package object;

public class Stuff extends ProductExt {
	String Race,Species,Attribute,HP,Attack,Rcovery,Cost;
	public Stuff(int itemID,String name ,String description, int numStock, double price, double buyingPrice,boolean reorder,String Species ,String Attribute ,String Race,String HP,String Attack,String Rcovery,String Cost){
		super(itemID,name,description,numStock,price,buyingPrice,reorder);
		this.Race = Race;
		this.Species = Species;
		this.Attribute = Attribute;
		this.HP = HP;
		this.Attack = Attack;
		this.Rcovery = Rcovery;
		this.Cost = Cost;
	}
	public String getRace() {
		return Race;
	}
	public void setRace(String race) {
		Race = race;
	}
	public String getSpecies() {
		return Species;
	}
	public void setSpecies(String species) {
		Species = species;
	}
	public String getAttribute() {
		return Attribute;
	}
	public void setAttribute(String attribute) {
		Attribute = attribute;
	}
	public String getHP() {
		return HP;
	}
	public void setHP(String hP) {
		HP = hP;
	}
	public String getAttack() {
		return Attack;
	}
	public void setAttack(String attack) {
		Attack = attack;
	}
	public String getRcovery() {
		return Rcovery;
	}
	public void setRcovery(String rcovery) {
		Rcovery = rcovery;
	}
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	public Stuff(int ItemID,String name , String description, int numStock, double price, double buyingPrice,boolean reorder){
		super(ItemID,name,description,numStock,price,buyingPrice,reorder);
	}
	
	public String toString(){
		String format = "%s#%s#%s#%s#%s#%s#%s#%s";
		String stuffInfo = String.format(format, super.toString(),this.Species,this.Attribute,this.Race,this.HP,this.Attack,this.Rcovery,this.Cost);
		return stuffInfo;
	}
}
