package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Entity.Item;
import Entity.Promotion;
import Entity.Staff;

//Manage CRUD of item and promotion
//get staffID
public class SystemManagement {
	
	private Scanner s;
	private Item tempItem;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Promotion> promoList = new ArrayList<Promotion>();
	Staff staff;
	
	public SystemManagement(){
		getItems();
		getPromotion();
	}
	
	public void getItems(){
		
		try {
			s = new Scanner(new BufferedReader(new FileReader("item.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Item item = new Item(temp[0],temp[1],temp[2], temp[3], temp[4], temp[5]);
				itemList.add(item);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void getPromotion(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("promotion.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Promotion p = new Promotion();
				ArrayList<Item> tempPromoList = new ArrayList<Item>();
				p.setPromoID(temp[0]);
				p.setName(temp[1]);
				//loop base on the number of items in a single promotion
				for(int i=2; i<temp.length; i++){
					//compare the itemID to itemList which contain all the itemIDs to retrieve the correct item object
					for(Iterator<Item> it = itemList.iterator(); it.hasNext();){
						Item itemInItemList = it.next();
						if(temp[i].equals(itemInItemList.getItemID())){
							tempPromoList.add(itemInItemList);
						}
					}
				}
				//System.out.println("tempPromoList.size(): "+tempPromoList.size());
				p.setItemList(tempPromoList);
				promoList.add(p);
			}
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean createItem(){
		boolean result=false;
		s = new Scanner(System.in);
		tempItem = new Item();
		boolean itemExist=false;
		System.out.println("Please enter name of item:");
		tempItem.setName(s.nextLine());
		System.out.println("Please enter type of item:");
		tempItem.setType(s.nextLine());
		System.out.println("Please enter price of item:");
		tempItem.setPrice(s.nextLine());
		System.out.println("Please enter description of item:");
		tempItem.setDesc(s.nextLine());
		System.out.println("Please enter discounted price of item:");
		tempItem.setDiscountedPrice(s.nextLine());
		//check by name if similar item exist
		for(Item i: itemList){
			if(i.getName().equals(tempItem.getName()))
				itemExist = true;
		}
			
		if(!itemExist){
			//generate ItemID
			int itemID=0;
			for(Item i: itemList){
				if(itemID < Integer.parseInt(i.getItemID()))
					itemID = Integer.parseInt(i.getItemID());
			}
			itemID++;
			tempItem.setItemID((Integer.toString(itemID)));
				
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("item.txt", true)))) {
				out.print("\n"+tempItem.getItemID()+","+tempItem.getType()+","+tempItem.getName()+","+tempItem.getPrice()+","+tempItem.getDesc()
						+","+tempItem.getDiscountedPrice());
				result=true;
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		return result;
	}
	
	public void updateItem(){
		s = new Scanner(System.in);
		int choice;
		String itemID;
		String temp;
		System.out.print("ItemID"+ "       " + "Item Name" + "       " + "Item Price" + "       " + "Item Description" + "                  " + 
				"Item Type" + "       " + "Discounted Price");
		for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
			Item item = i.next();
			System.out.print("\n"+item.getItemID() + "            "+ item.getName() + "            "+ item.getPrice() + "            "+ item.getDesc()
					+ "            "+ item.getType() + "            "+ item.getDiscountedPrice());
		}
		System.out.println();
		System.out.println("Please enter itemID of item to be updated");
		itemID = s.nextLine();
		System.out.println("1. Change Name");
		System.out.println("2. Change Price");
		System.out.println("3. Change Description");
		System.out.println("4. Change Type");
		System.out.println("5. Change Discounted Price");
		choice = s.nextInt();
		switch(choice){
			case 1:
				s.nextLine();
				System.out.println("Enter new name of item");
				temp = s.nextLine();
				for(Item i: itemList){
					if(i.getItemID().equals(itemID))
						i.setName(temp);
				}
				writeArrayToItemFile();
				break;
			case 2:
				s.nextLine();
				System.out.println("Enter new price of item");
				temp = s.nextLine();
				for(Item i: itemList){
					if(i.getItemID().equals(itemID))
						i.setPrice(temp);
				}
				writeArrayToItemFile();
				break;
			case 3:
				s.nextLine();
				System.out.println("Enter new description of item");
				temp = s.nextLine();
				for(Item i: itemList){
					if(i.getItemID().equals(itemID))
						i.setDesc(temp);
				}
				writeArrayToItemFile();
				break;
			case 4:
				s.nextLine();
				System.out.println("Enter new type of item");
				temp = s.nextLine();
				for(Item i: itemList){
					if(i.getItemID().equals(itemID))
						i.setType(temp);
				}
				writeArrayToItemFile();
				break;
			case 5:
				s.nextLine();
				System.out.println("Enter new discounted price of item");
				temp = s.nextLine();
				for(Item i: itemList){
					if(i.getItemID().equals(itemID))
						i.setDiscountedPrice(temp);
				}
				writeArrayToItemFile();
				break;
			}
		}
	
	public void viewItem(){
		
			System.out.println("=========== Main Course ===========");
			 
			for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
				Item item = i.next();
				if(item.getType().equals("main course"))
					{System.out.println(item.getItemID() + "   "+ item.getName() + "   " + item.getPrice() + "$   " + item.getDesc());
					System.out.println();}
			}
			System.out.println();
			System.out.println("=========== Drink ===========");
			for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
				Item item = i.next();
				if(item.getType().equals("drink"))
					{System.out.println(item.getItemID() + "   "+ item.getName() + "   " + item.getPrice() + "$   " + item.getDesc());
					System.out.println();}
			}
			System.out.println();
			System.out.println("=========== Dessert ============");
			for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
				Item item = i.next();
				if(item.getType().equals("dessert"))
					{System.out.println(item.getItemID() + "   "+ item.getName() + "   " + item.getPrice() + "$   " + item.getDesc());
					System.out.println();}
			}
			
			System.out.println();
			System.out.println("=========== Promotional Set ============");
			for(Iterator<Promotion> p = promoList.iterator(); p.hasNext(); ) {
				Promotion promo = p.next();
				System.out.print(promo.getPromoID() + "   ");
				for(Iterator<Item> i = promo.getItemList().iterator(); i.hasNext(); ){
					Item item = i.next();
					System.out.print(item.getName()+ "   ");
				}
				System.out.println("   "+ promo.calPromoPrice()+"$");
				System.out.println();
			}
			System.out.println();
	}
	
	public void deleteItem(){
		s = new Scanner(System.in);
		String itemID;
		tempItem = new Item();
		System.out.print("ItemID"+ "       " + "Item Name" + "       " + "Item Price" + "       " + "Item Description" + "                  " + 
				"Item Type" + "       " + "Discounted Price");
		for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
			Item item = i.next();
			System.out.print("\n"+item.getItemID() + "            "+ item.getName() + "            "+ item.getPrice() + "            "+ item.getDesc()
					+ "            "+ item.getType() + "            "+ item.getDiscountedPrice());
		}
		System.out.println("Please enter itemID of item to be deleted");
		itemID = s.nextLine();
		System.out.println("itemList: "+ itemList.size());
		System.out.println("itemID: "+ itemID);
		for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
			Item item = i.next();
			
			if(item.getItemID().equals(itemID)){
				tempItem = item;
			}
		}
		itemList.remove(tempItem);
		System.out.println("itemList: "+ itemList.size());
		writeArrayToItemFile();
	}
	
	public boolean createPromotion(){
		
		boolean result=false;
		s = new Scanner(System.in);
		String itemID, promoName;
		ArrayList<String> tempList = new ArrayList<String>();
		//show item in item.txt, user can select item by itemID to add into promotion txt
		System.out.print("ItemID"+ "       " + "Item Name" + "       " + "Item Price" + "       " + "Item Description" + "                  " + 
				"Item Type" + "       " + "Discounted Price");
		for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
			Item item = i.next();
			System.out.print("\n"+item.getItemID() + "            "+ item.getName() + "            "+ item.getPrice() + "            "+ item.getDesc()
					+ "            "+ item.getType() + "            "+ item.getDiscountedPrice());
		}
		
		do{
			System.out.println("Please enter itemID of item to add to package, press 0 to exit");
			itemID = s.nextLine();
			if(!itemID.equals("0"))
				tempList.add(itemID);
		}
		while(!itemID.equals("0"));
		System.out.println("Please enter name of package:");
		promoName = s.nextLine();
			
			//generate promoID
		int promoID=0;
		for(Promotion promo: promoList){
			//remove the P to get the number
			if(promoID < Integer.parseInt(promo.getPromoID().substring(1, promo.getPromoID().length())))
				promoID = Integer.parseInt(promo.getPromoID().substring(1, promo.getPromoID().length()));
		}
		promoID++;
		//to be work on ===	
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promotion.txt", true)))) {
			
			out.print("\n"+ "P"+ Integer.toString(promoID)+","+promoName);
			for(int i=0; i<tempList.size(); i++){
				out.print(","+tempList.get(i));
			}
			result=true;
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void viewPromotion(){
		int count=1;
		System.out.print("=============Promotion===============");
		for(Iterator<Promotion> promo = promoList.iterator(); promo.hasNext(); ) {
			Promotion p = promo.next();
			System.out.print("\n"+ count+": "+p.getName()+":");
			for(Item item: p.getItemList()){
				System.out.print(" |"+item.getName());
			}
			count++;
		}
		System.out.println();
		System.out.println();
	}
	
	private void writeArrayToItemFile(){	
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("item.txt", false)))) {
			out.print("itemID, type, name, price, desc, discounted price");
			for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
				Item item = i.next();
				out.print("\n"+item.getItemID()+","+item.getType()+","+item.getName()+","+item.getPrice()+","+item.getDesc()
						+","+item.getDiscountedPrice());
			}
			System.out.println("Update Successful!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Staff> getStaffID() throws FileNotFoundException{
		ArrayList<Staff> staffIDList = new ArrayList<Staff>();
		Scanner s = new Scanner(new BufferedReader(new FileReader("staff.txt")));
		s.nextLine();
		while (s.hasNext()) {
			
			String temp[] = s.nextLine().split(",");
			Staff staff = new Staff();
			staff.setStaffID(temp[0]);
			staff.setStaffName(temp[1]);
			staff.setGender(temp[2]);
			staff.setJobTitle(temp[3]);;
			staffIDList.add(staff);
			
		}
		return staffIDList;
		
	}
	

	
	
	
}
