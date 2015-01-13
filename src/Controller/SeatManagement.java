package Controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Util.DateFormat;
import Entity.Reservation;
import Entity.Table;


public class SeatManagement {
	//keep track of current capacity of restaurant and to for order to get tableID for order invoice
	Table table[];
	Reservation r = new Reservation();
	public ArrayList<Reservation> tableOccupiedList = new ArrayList<Reservation>();
	DateFormat df = new DateFormat();
	public Table[] getTable() {
		return table;
	}

	public void setTable(Table[] table) {
		this.table = table;
	}

	public ArrayList<Reservation> getTableOccupiedList() {
		return tableOccupiedList;
	}

	public void setTableOccupiedList(ArrayList<Reservation> tableOccupiedList) {
		this.tableOccupiedList = tableOccupiedList;
	}

	
	public SeatManagement(){
		
	}
	
	public void clearOverDueReservation(){
		
	}
	
	public void getOccupiedTable(){
		r = new Reservation();
		tableOccupiedList = r.getCurrentHourReservation();		
	}
	
	
	public void displayAvailableTable(){
			
			
		/*
		r = new Reservation();
		Calendar cal = Calendar.getInstance();
		Date d1 = cal.getTime();
		r.getCurrentHourReservation(date);
		*/
		/*
		r = new Reservation();
		 String date;
		 Calendar cal = Calendar.getInstance();
		 Date d1 = cal.getTime();
		 date = df.getDate(d1);
		tableOccupiedList = r.getTableReservation(date);
		
			for(Table t: r.getTable()){
				if(t.getIsReserved()==1){
					table[(t.getTableNum()-1)].setIsReserved(1);
				}
			}
		
		for(int i=0; i<10; i++){
			if(table[i].getIsReserved()==1){
				System.out.println("Table "+table[i].getTableNum()+1+ ": Occupied");
			}else
				System.out.println("Table "+(table[i].getTableNum()+1)+ ": Free");
			
		}
		*/
		
		/*
		if(tableOccupiedList!=null && tableOccupiedList.size()>0){
			
			for(Reservation r: tableOccupiedList){
				int tableNum = Integer.parseInt(r.getTableNum());
				for(Table t: table){
					if((t.getTableNum()+1)==tableNum){
						t.occupied = 1;
					}
				}
			}
			
			
			
		}
		for(Table t: table){
			if(t.getIsReserved()!=1){
				System.out.println("Table No." + (t.getTableNum()+1));
			}
		}
		*/
	}
	

	
	public void checkTableAvailability(String Pax) throws ParseException, IOException{
		// get system date and time
		 r = new Reservation();
		 Date d1, startTime;
		 String date;
		 Calendar cal = Calendar.getInstance();
		 d1 = cal.getTime();
		 date = df.getDate(d1);
		 startTime = cal.getTime();
		// startTime = df.getTime(d1);
		 //allocate table based on date, startTime and pax
		 r.allocateTable(date, startTime, Pax);
		 if(r.getCustomerName()!=null){
			tableOccupiedList.add(r);
			System.out.println("Table Num: " + r.getTableNum());
		}else{
			System.out.println("All table of size "+ Pax + " has been occupied");
		}
		
		/*
		boolean result = false;
		for(int i=0; i<10; i++){
			if(table[i].getTableNum() == Integer.parseInt(tableNum)){
				if(table[i].getIsReserved()==0){
					table[i].setIsReserved(1);
					result = true;
				}
			}
		}
		return result;
		*/
	}
	
	public boolean unassignTable(String tableNum){
		boolean result = false;
		for(int i=0; i<10; i++){
			if(table[i].getTableNum() == Integer.parseInt(tableNum)){
				if(table[i].getIsReserved()==1){
					table[i].setIsReserved(0);
					result = true;
				}
			}
		}
		return result;
	}
	

}
