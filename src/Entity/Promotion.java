package Entity;
import java.util.ArrayList;
import java.util.Iterator;


public class Promotion {
	
	private String name, promoID;
	private double totalPrice;
	private ArrayList<Item> itemList;
	
	public Promotion(){
		
	}
	
	public Promotion(String promoID,String name,double totalPrice, ArrayList<Item> itemList){
		this.promoID = promoID;
		this.name = name;
		this.totalPrice = totalPrice;
		this.itemList = itemList;
	}

	public String getPromoID() {
		return promoID;
	}

	public void setPromoID(String promoID) {
		this.promoID = promoID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return totalPrice;
	}

	public void setPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	public double calPromoPrice(){
		double tempPrice = 0;
		for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
			Item item = i.next();
			tempPrice += Double.parseDouble(item.getDiscountedPrice());
			
		}
		return tempPrice;
	}
	
	
}
