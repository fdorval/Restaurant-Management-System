

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class MenuItem {
	

	private String description;
	private String name;
	private double price;
	private static final File menuFile = new File("menu.txt");
	

	
	private MenuItem(String itemId, String name, double price, String description) {
		super();
		
		this.name = name;
		this.price = price;
		this.description = description;
		
	}


	private static void writeMenuItemToFile(){
		//TODO: this stores the menu to menu.txt
	}
	
	/**
	 * This method takes care of reading operations from a file 
	 */
	private static void readMenuItemFromFile() throws IOException{
		//TODO: this reads the menu from the menu.txt 
	}

	/**
	 * Creates a menu item and stores it in the file. 
	 * @param name
	 * @param price
	 * @param description
	 * @param itemType
	 * @throws IOException
	 */
	public static boolean createMenuItem(String id, String name, double price, String description, ItemType itemType) throws IOException{
		
		if(menu.get(id) != null){ //Id can't already exist
			return false;
		}
		
		menu.put(id, new MenuItem(id, name, price, description, itemType));
		
		writeMenuItemToFile();
		
		return true;
	}
	
	/**
	 * Deletes a menu item from the file. 
	 * @param id
	 * @throws IOException
	 */
	public static boolean deleteMenuItem(String id) throws IOException{
		menu.remove(id);
		writeMenuItemToFile();
		return true;
	}
	
	/**
	 * Retrieves a list of all menu items. 
	 * @return menu
	 */
	
	
	public boolean updateMenu(String updateField, String content){
		if(updateField.equalsIgnoreCase("Name")){
			this.name = content; //Could have a set method here instead if you want to. But maybe sort of unnecessary as it's not used anywhere else. 
			writeMenuItemToFile();
			return true;
		}
		if(updateField.equalsIgnoreCase("Description")){
			this.description = content;
			writeMenuItemToFile();
			return true;
		}
		if(updateField.equals("ItemType")){
			this.itemType = ItemType.valueOf(content);
			writeMenuItemToFile();
			return true;
		}
		if(updateField.equals("Price")){
			this.price = Double.parseDouble(content);
			writeMenuItemToFile();
			return true;
		}
		return false; // if not any of the above return false.
	}
	

	
	public static void initialize() throws IOException{
		if (!menuFile.isFile()){
			menuFile.createNewFile();
		}
		readMenuItemFromFile();
		
	}
	
	public static MenuItem getSingleMenuItem(String id){
		return menu.get(id);
	}


	
	public String getDescription() {
		return description;
	}



	public ItemType getItemType() {
		return itemType;
	}
	
	
	
}