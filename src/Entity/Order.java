package Entity;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import Util.DateFormat;


public class Order {
	
	Menu m;  
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
	private double orderBill=0;
	private double totalBill=0;//with tax
	
	public Order(){
		
	}
	/**
	 * This function takes order
	 */
	public void takeOrder(){
		m = new Menu();
		Object object = m.displayMenu();
		if(object!=null){
			if(object instanceof Item)
				itemList.add((Item)object);
		    if(object instanceof Promotion)
		    	promotionList.add((Promotion)object);
	    }
	       
	}
	
	public void takeOrder(Promotion p){
		promotionList.add(p);
	}
	
	public void viewOrder(){
		for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
			Item item = i.next();
			System.out.println("Item: " + item.getName() + " " + "$"+ item.getPrice());
		}
		
		for(Iterator<Promotion> i = promotionList.iterator(); i.hasNext();){
			Promotion promo = i.next();
			System.out.println("Promotion: " + promo.getName() + " " + "$"+ promo.calPromoPrice());
		}
	}
	
	public double calOrderBill(){
		
		for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
			Item item = i.next();
			orderBill += Double.parseDouble(item.getPrice());
		}
		
		for(Iterator<Promotion> i = promotionList.iterator(); i.hasNext();){
			Promotion promo = i.next();
			orderBill += promo.calPromoPrice();
		}
		
		System.out.println("Order Bill: " + orderBill);
		return orderBill;
	}
	
	public void removeOrder(){
		Scanner sc = new Scanner(System.in);
		String input;
		
		for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
			Item item = i.next();
			System.out.println("Item: " + item.getName() + " " + "$"+ item.getPrice());
		}
		System.out.println("Enter itemID to remove from order, press 0 if there is no item to remove");
		input = sc.nextLine();
		if(!input.equals(0)){
			for(Iterator<Item> i = itemList.iterator(); i.hasNext();){
				Item item = i.next();
				if(item.getItemID().equals(input)){
					i.remove();
				}
			}
		}
		
		for(Iterator<Promotion> i = promotionList.iterator(); i.hasNext();){
			Promotion promo = i.next();
			System.out.println("Promotion: " + promo.getName() + " " + "$"+ promo.calPromoPrice());
		}
		System.out.println("Enter promotionID to remove from order, press 0 if there is no promotion to remove");
		input = sc.nextLine();
		if(!input.equals(0)){
			for(Iterator<Promotion> p = promotionList.iterator(); p.hasNext();){
				Promotion promo = p.next();
				if(promo.getPromoID().equals(input)){
					p.remove();
				}
			}
		}
		//sc.close();
	}
	
	
	public void printInvoice(Staff staff, Reservation r) throws ParseException{
		
		//Scanner sc = new Scanner(System.in);
		DateFormat dateFormat = new DateFormat();
		//System.out.print("Enter Customer Name : ");
		//String customerName = sc.next();
		//System.out.print("Enter Order ID : ");
		//orderID = sc.nextInt();
		//Order order = mOrderController.find(customerName, orderID) ;
		r.setEndTime();
		Date d1 = r.getEndTime();
		
		
		DecimalFormat df = new DecimalFormat ("#.##");
		
		System.out.println("================= CZ2002 Restaurant =================");
		System.out.println("Staff Name : " + staff.getStaffName() );
		System.out.println("Table ID : " + r.getTableNum() );
		System.out.println("Date(DD/MM/YYYY): " + dateFormat.getDate(d1));
		System.out.println("---------------------------------------------------------");
		
		if(itemList.size() > 0){
		System.out.println("Menu Items : ");
		
			for(int i = 0; i < itemList.size(); i++ )
			{
			System.out.println(" " + (itemList.get(i)).getName() + " " + (itemList.get(i)).getPrice() );
			}
		}
		
		if(promotionList.size() > 0){
		System.out.println("---------------------------------------------------------");
		System.out.println("Promotional Set Packages : ");
		
			for(int i = 0; i < promotionList.size(); i++ )
			{
			System.out.println(" " + (promotionList.get(i)).getName() + " " + (promotionList.get(i)).calPromoPrice() );
			}
		}
		calOrderBill();
		totalBill = orderBill*1.07;

		System.out.println("---------------------------------------------------------");
		System.out.println("SubTotal : " + df.format(( orderBill )));
		System.out.println("Taxes : " + df.format(( orderBill * 0.07)));
		System.out.println("--------------------------------------------------------- ");
		System.out.println("TOTAL : " + df.format((orderBill ) * 1.07 ));
		System.out.println("============= Thank you! Please come again! =============");
		
		
		}
	
	/*
	 public void DailyReport(){

		Scanner scan = new Scanner(System.in);

		int d, m, y;
		
		System.out.println("please enter the Date in the following format DD MM YYYY:");

		d = scan.nextInt();

		m = scan.nextInt();

		y = scan.nextInt();

		PrintSaleController mPrintSaleController = new PrintSaleController();

		ArrayList<Order> order = mPrintSaleController.findOrderByDate(d, m, y);

		mPrintSaleController.RevenueByDate(order,d,m,y);

		scan.close();

		return;
			
	  }
	 
		public void RevenueByDate(ArrayList<Order> order,int d, int m, int y){
			int TotalPrice= 0;
			
			if(order == null){
				System.out.println("No order found on that date!");
				return;
			}
			else
			{
				for(int i = 0 ; i < order.size(); ++i)
				{
					System.out.println("Customer ID:" + order.get(i).getCustomerId());
					ArrayList<MenuItem> menuItems = (order.get(i)).getMenuItemsList();
					ArrayList<Integer> quantityMenuItems = (order.get(i)).getQuantityMenuItems();
					//quantityMenuItems.get(j)+
					//quantityPackage.get(k) +
					System.out.println("Menu items: ");
					for(int j = 0; j < menuItems.size(); ++j)
					{
						System.out.println((j+1) + ". " + " x "+ (menuItems.get(j)).getName() + "       Price:" + (menuItems.get(j)).getPrice());
					}
					
					ArrayList<PromotionalPackage> packages = (order.get(i)).getPromotionalPackagesList();
					ArrayList<Integer> quantityPackage = (order.get(i)).getQuantityPackages();
					System.out.println("Promotional packages: ");
					for(int k = 0; k < packages.size(); ++k)
					{
						System.out.println((k+1) + ". " +  (packages.get(k)).getName() + "       Price:" + (packages.get(k)).getPrice());
					}
					TotalPrice += (order.get(i)).getTotalPrice();
				}
			}
			System.out.println("Overall revenue for " + d +" " + m +" "+ y + " : " + TotalPrice);  
	 
	}
	*/

	  


	
		

}
