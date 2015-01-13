package Entity;

public class Item {
	
	private String itemID, name, type, price, desc, discountedPrice;
	
	public Item(){
		
	}
	
	public Item(String itemID,String type,String name,String price,String desc, String discountedPrice){
		this.itemID = itemID;
		this.name = name;
		this.type = type;
		this.price = price;
		this.desc = desc;
		this.discountedPrice = discountedPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(String discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	
	
	

}
