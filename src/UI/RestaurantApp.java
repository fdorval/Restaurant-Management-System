package UI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import Controller.SeatManagement;
import Controller.SystemManagement;
import Entity.Reservation;
import Entity.Staff;

/**
 * 
 * Restaurant App is used to implement the user interface 
 *
 */
public class RestaurantApp {
	
	static Scanner sc = new Scanner(System.in);
	static Reservation r;
	static ArrayList<Reservation> reservList;
	static SimpleDateFormat sdf;
	static SystemManagement sm;
	static Staff staff;
	SeatManagement seatManager;
	
	/**
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 */
	
	
	public static void main(String[] args)throws ParseException, IOException {
		// TODO Auto-generated method stub
		//Order o = new Order();
		SeatManagement seatManager = new SeatManagement();
		seatManager.getOccupiedTable();
		staff = new Staff();
		boolean k; 
		//start program
		k=setStaffID();
		if(k)
		{
		int choice;
		do {
			System.out.println("Enter a number");
			System.out.println("1: Check Table Availability");
			System.out.println("2: Manage Order");
			System.out.println("3: Manage Reservation");
			System.out.println("4: Manage Promotion");
			System.out.println("5: Manage Item");
			System.out.println("6: Enter StaffID");
			System.out.println("7: quit");
			choice = sc.nextInt();
			switch(choice){
				case 1:
					System.out.println("Please enter table pax");
					int pax = sc.nextInt();
					sc.nextLine();
					if(pax>0 && (pax%2==0)){
						seatManager.checkTableAvailability(Integer.toString(pax));
					}else{
						seatManager.checkTableAvailability(Integer.toString(pax+1));
						}
					break;	
				case 2:
					int input_2;
					do{
						System.out.println("1: Create Order");
						System.out.println("2: Remove Order");
						System.out.println("3: View Order");
						System.out.println("4: Make Payment Order");
						System.out.println("5: Back to main menu:");
						System.out.println("Select an option:");
						input_2 = sc.nextInt();
						sc.nextLine();
						switch(input_2){
						case 1:
							boolean result = false;
							int tableNum;
							
							System.out.println("Enter Table No.");
							tableNum = sc.nextInt();
							
							for(Reservation r: seatManager.tableOccupiedList){
								if(Integer.parseInt(r.getTableNum())==tableNum){
									r.table[tableNum].order.takeOrder();
									result = true;
									break; 
								}
								
							}
							if(result)
								System.out.println("Item added!");
							else
								System.out.println("Cannot place order on empty table!");

							break;
						case 2:
							boolean result_2 = false;
							int tableNum_2;
							
							System.out.println("Enter Table No.");
							tableNum_2 = sc.nextInt();
							
							for(Reservation r: seatManager.tableOccupiedList){
								if(Integer.parseInt(r.getTableNum())==tableNum_2){
									r.table[tableNum_2].order.removeOrder();
									result_2 = true;
								}
							}
							if(result_2)
								System.out.println("Item/Promotion is remove");
							else
								System.out.println("Order wasn't remove!");
							
							break;
						case 3:
							int tableNum3;
							boolean result_3=false;
							System.out.println("Enter Table No.");
							tableNum3 = sc.nextInt();
							
							for(Reservation r: seatManager.tableOccupiedList){
								if(Integer.parseInt(r.getTableNum())==tableNum3){
									r.table[tableNum3].order.viewOrder();
									result_3 = true;
								}
								
							}
							if (!result_3)
								System.out.println("No order placed on table!");
						
							break;
						case 4:
							int tableNum_4;
							//boolean result_4=false;
							System.out.println("Enter Table No.");
							tableNum_4 = sc.nextInt();
							
							for(Reservation r: seatManager.tableOccupiedList){
								if(Integer.parseInt(r.getTableNum())==tableNum_4){
									//r.table[tableNum_4].order.calOrderBill();
									r.table[tableNum_4].order.printInvoice(staff, r);
									r.table[tableNum_4].removeReservationOnPayment(tableNum_4);
									break;
								}
							}
							//o.calOrderBill();
							break;
							
						case 5:
							input_2=5;
						}
					}while(input_2 < 5);
					
					
					break;
				case 3:
					int input_4;
					do{
						System.out.println("1: Create Reservation Booking");
						System.out.println("2: View Reservation");
						System.out.println("3: Check/Remove Reservation");
						System.out.println("4: Back to main menu:");
						System.out.println("Select an option:");
						input_4 = sc.nextInt();
						sc.nextLine();
						switch(input_4){
						case 1:
							if(getMakeReservation())
								System.out.println("Reservation Successful");
							else
								System.out.println("Reservation fail");
							break;
						case 2:
							getTableReservation();
							break;
						case 3:
							getRemoveTableReservation();
							break;
						case 4:
							input_4=4;
						}
					}while(input_4 < 4);
					break;
				case 4:
					int input_6;
					do{
						System.out.println("1: Create promotion");
						System.out.println("2: View promotion");
						System.out.println("3: Update promotion");
						System.out.println("4: Delete promotion");
						System.out.println("5: Back to main menu:");
						System.out.println("Select an option:");
						input_6 = sc.nextInt();
						switch(input_6){
						case 1:
							sm = new SystemManagement();
							if(sm.createPromotion())
								System.out.println("New promotion has been added!");								
							else
								System.out.println("New promotion has NOT been added!");
							break;
						case 2:
							getViewPromotion();
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							input_6=5;
						}
					}while(input_6<5);
					break;
					
				case 5:
					int input_7;
					do{
						System.out.println("1: Create Item");
						System.out.println("2: View Item");
						System.out.println("3: Update Item");
						System.out.println("4: Delete Item");
						System.out.println("5: Back to main menu:");
						System.out.println("Select an option:");
						input_7 = sc.nextInt();
						switch(input_7){
						case 1:
							if(getCreateItem())
								System.out.println("Item has been added");
							else
								System.out.println("Item has not been added");
							break;
						case 2:
							getViewItem();
							break;
						case 3:
							getUpdateItem();
							break;
						case 4:
							getDeleteItem();
							break;
						case 5:
							input_7=5;
						}
					}while(input_7<5);
					break;
				case 6:
					
					break;
				case 7:
					System.out.println("Program terminating �.");
			}
		}while (choice<7);
	}
		else
		{
			main(args); 
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static boolean getMakeReservation() throws ParseException{
		r = new Reservation();
		System.out.println("Please enter date of reservation");
		r.setDate(sc.nextLine());
		System.out.println("Please enter time of reservation");
		r.setStartTime(sc.nextLine());
		System.out.println("Please enter table pax");
		r.setPax(sc.nextLine());
		System.out.println("Please enter name of customer");
		r.setCustomerName(sc.nextLine());
		System.out.println("Please enter contact no. of customer");
		r.setContact(sc.nextLine());
		return r.makeReservation();
	}
	
	/**
	 * 
	 * @throws ParseException
	 */
	
	public static void getTableReservation() throws ParseException{
		r = new Reservation();
		reservList = r.getTableReservation();
		sdf = new SimpleDateFormat("HH:mm");
		System.out.println("Reservation Date" + "    " + "Reservation Time" + "     " + "Table No." + "    " + 
		"Customer Name" + "    " + "Table Pax" + "    " + "Contact No.");
		for(Iterator<Reservation> r = reservList.iterator(); r.hasNext();){
			Reservation reservation = r.next();
			reservation.setEndTime();
			System.out.println(reservation.getDate()+ "          " + sdf.format(reservation.getStartTime())+ "-" + sdf.format(reservation.getEndTime()) + 
			"          " + reservation.getTableNum()	+ "             " + reservation.getCustomerName() +
			"            " + reservation.getPax() + "            " + reservation.getContact());
		}
	}
	/**
	 * 
	 * @throws ParseException
	 */
	public static void getRemoveTableReservation() throws ParseException{
		r = new Reservation();
		r.getRemoveReservation();
	}
	/**
	 * 
	 * @return
	 */
	public static boolean getCreateItem(){
		sm = new SystemManagement();
		return sm.createItem();
	}
	/**
	 * 
	 */
	public static void getUpdateItem(){
		sm = new SystemManagement();
		sm.updateItem();
	}
	/**
	 * 
	 */
	public static void getDeleteItem(){
		sm = new SystemManagement();
		sm.deleteItem();
	}
	/**
	 * 
	 */
	public static void getViewPromotion(){
		sm = new SystemManagement();
		sm.viewPromotion();
	}
	
	/**
	 * 
	 */
	public static void getViewItem(){
		sm = new SystemManagement();
		sm.viewItem();
		
	}
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public static boolean setStaffID() throws FileNotFoundException{
		boolean result = false;
		sm = new SystemManagement();
		ArrayList<Staff> staffList = sm.getStaffID();
		System.out.println("Enter StaffID");
		staff.setStaffID(sc.next());
		for(Staff s: staffList){
			if(staff.getStaffID().equals(s.getStaffID())){
				staff = s;
				result = true;
			}
		}
		if(result){
			System.out.println("StaffID accepted, " + staff.getStaffName() + "!");
		}else{
			System.out.println("Invalid StaffID");
		}
		return result; 
	}
	

		
	
	
	
	

}
	

