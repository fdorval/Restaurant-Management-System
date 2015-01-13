package Entity;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import Util.DateFormat;

public class Reservation {
	private String date, customerName, pax, contact, tableNum;
	private Date startTime, endTime;
	String pattern = "HH:mm";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    DateFormat df = new DateFormat();
    Calendar cal = Calendar.getInstance();
    Scanner sc;
    ArrayList<Reservation> tableAllocationList = new ArrayList<Reservation>();
	
	//Calendar aDate = new GregorianCalendar(2013, 3, 5, 13, 30);
	public Table table[] = new Table[10];
	Table t1;
	public Reservation(){
		Order order = new Order();
		int tableSize = 2;
		for(int i=0; i<10; i++){
			if(i==4)
				tableSize = 4;
			if(i==7)
				tableSize = 6;
			if(i==8)
				tableSize = 8;
			if(i==9)
				tableSize = 10;
			table[i] = new Table(tableSize, i, 0);
		}
	}
	
	public Reservation(String date, String startTime,String pax,String customerName,String contact,String tableNum) throws ParseException{
		
		this.date = date;
		this.startTime = sdf.parse(startTime);
		this.pax = pax;
		this.customerName = customerName;
		this.contact = contact;
		this.tableNum = tableNum;
		
		int tableSize = 2;
		for(int i=0; i<10; i++){
			table[i] = new Table(tableSize, i, 0);
			if(i==4)
				tableSize = 4;
			if(i==7)
				tableSize = 6;
			if(i==8)
				tableSize = 8;
			if(i==9)
				tableSize = 10;
		}
	}
	
	
	/*
	public void getReservation(){
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("D:/myFiles/NTU/Java_Workspace/RestaurantManagement/file/reservation.txt")));
			s.useDelimiter(",");
			s.nextLine();
			while (s.hasNext()) {
				//System.out.println(s.next());
				startTime = s.next();
				date = s.next();
				customerName = s.next();
				pax = s.next();
				contact = s.next();
				tableNum = s.next();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	*/
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime){
		try{
			this.startTime = sdf.parse(startTime);
		}catch (ParseException e){
		        // Exception handling goes here
		}
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime() throws ParseException {
		cal.setTime(startTime);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		this.endTime = cal.getTime();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPax() {
		return pax;
	}

	public void setPax(String pax) {
		this.pax = pax;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}

	public Table[] getTable() {
		return table;
	}

	public void setTable(Table[] table) {
		this.table = table;
	}

	public boolean makeReservation()throws ParseException{
		
		//if(checkDate())
			for(int i=0; i<10; i++){
				if(table[i].getSize()== Integer.parseInt(pax)){
					System.out.println("i: " + i);
					tableNum = Integer.toString(table[i].getTableNum()+1);
					return table[i].reserveTable(date, startTime, pax, customerName, contact, tableNum);
				}
			}
		return false;
		
	}
	
	//ensure reservation date is on the day itself or in advance
	public boolean checkDate() throws ParseException{
		Date today = new Date();
		//sdf.applyPattern("yyyy/MM/dd");
		//Date reservationDate =  sdf.parse(date);
		cal.setTime(startTime);
		Date reservationDate =  cal.getTime();
		System.out.println(reservationDate);
		System.out.println("Today: " + today + "   reservationDate: " + reservationDate);
		System.out.println(today.compareTo(reservationDate)>0);
		if(today.compareTo(reservationDate)>=0)
			return false;
		else
			return true;
		
		
	}
	
	public ArrayList<Reservation> getTableReservation(){
		t1 = new Table();
		return t1.getTableReservation();
	}
	
	public ArrayList<Reservation> getTableReservation(String date){
		t1 = new Table();
		return t1.getTableReservation(date);
	}

	public ArrayList<Reservation> getCurrentHourReservation(){
		
		t1 = new Table();
		return t1.currentHourTableReservation();
		
		/*
		for(Table t: table){
			 Table t1 = t1.getCurrentHourReservation(date);
			if(t.getTableNum()== t1.getTableNum()){
				t.setIsReserved(t1.getIsReserved());
			}
		}
		*/
	}

	
	public void getRemoveReservation() throws ParseException{
		t1 = new Table();
		t1.removeTableReservation();
	}
	
	//handles walk in
	public void allocateTable(String date, Date startTime, String Pax) throws ParseException, IOException{
		
			//for every table, if tableNo is same as reservList r.tableNo, check startTime and endTime, if tableNo timing not the same, check other tableNo with
			//same pax size
			for(int i=0; i<10; i++){
				if(table[i].getSize()== Integer.parseInt(Pax)){
					tableNum = Integer.toString(table[i].getTableNum()+1);
					 Reservation r = table[i].reserveAllocatedTable(date, startTime, Pax , tableNum);
					 if(r!=null){
						 this.setContact(r.getContact());
						 this.setCustomerName(r.getCustomerName());
						 this.setDate(r.getDate());
						 this.setPax(r.getPax());
						 this.setStartTime(df.getTime(r.getStartTime()));
						 this.setEndTime();
						 break;
					}
				}
			}			
	}
	
	
	
		
}
	

