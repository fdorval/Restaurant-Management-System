package Entity;
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


public class Menu {
	
	ArrayList<Item> itemList =  new ArrayList<Item>();
	ArrayList<Item> mainCourseList =  new ArrayList<Item>();
	ArrayList<Item> dessertList =  new ArrayList<Item>();
	ArrayList<Item> drinkList =  new ArrayList<Item>();
	ArrayList<Promotion> promoList =  new ArrayList<Promotion>();
	Scanner s;
	Item tempItem;
	
	public Menu(){
		getItems();
		sortItemList();
		getPromotion();
	}
	
	private void sortItemList(){
		for(Iterator<Item> i = itemList.iterator(); i.hasNext(); ) {
			Item item = i.next();
			
			if(item.getType().equals("main course")){
				mainCourseList.add(item);
			}
			else
				if(item.getType().equals("drink"))
					drinkList.add(item);
				else
					if(item.getType().equals("dessert"))
						dessertList.add(item);					
			
		}
	}
	
	private void getItems(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("item.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Item item = new Item(temp[0],temp[1],temp[2], temp[3], temp[4], temp[5]);
				itemList.add(item);
			}
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	//must run getItems() first before getPromotion() because it depends on items in itemList
	private void getPromotion(){
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
	
	
	public Object displayMenu(){
			s = new Scanner(System.in);
			System.out.println("================================================");
			System.out.println("               		Menu                 ");
			System.out.println("================================================");
			
			System.out.println("=========== Main Course ===========");
			for(Iterator<Item> i = mainCourseList.iterator(); i.hasNext(); ) {
				Item item = i.next();
				System.out.println(item.getItemID() + "   "+ item.getName() + "   " + item.getDesc() + "   "  + "$"+item.getPrice() );
			}
			System.out.println();
			System.out.println("=========== Drink ===========");
			for(Iterator<Item> i = drinkList.iterator(); i.hasNext(); ) {
				Item item = i.next();
				System.out.println(item.getItemID() + "   "+ item.getName() + "   " + item.getDesc()  + "   " + "$"+item.getPrice());
			}
			System.out.println();
			System.out.println("=========== Dessert ============");
			for(Iterator<Item> i = dessertList.iterator(); i.hasNext(); ) {
				Item item = i.next();
				System.out.println(item.getItemID() + "   "+ item.getName() + "   " + item.getDesc() + "   " + "$"+ item.getPrice());
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
				System.out.println("   " + "$"+ promo.calPromoPrice());
				
			}
			System.out.println();
			System.out.println("Enter item code to select item:");
			return selectItem(s.next());
	}
	
	public Object selectItem(String objectID){
		
		Object object =null;
		for(Item i : itemList){
	        if(i.getItemID() != null && i.getItemID().equals(objectID))
	           object = i;
	    }
		
		for(Promotion p : promoList){
	        if(p.getPromoID() != null && p.getPromoID().equals(objectID))
	           object = p;
	    }
		
		return object;
	}

}
