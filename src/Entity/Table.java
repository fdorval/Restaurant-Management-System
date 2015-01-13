package Entity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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


public class Table {
	
	public int size, tableNum, occupied;
	ArrayList<Reservation> reservList;
	Scanner sc;
	DateFormat df = new DateFormat();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public Order order = new Order();
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Table(){
		
	}

	public Table(int size, int tableNum, int occupied){
		this.size = size;
		this.tableNum = tableNum;
		this.occupied = occupied;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getTableNum(){
		return tableNum;
	}
	
	public int getIsReserved(){
		return occupied;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public void setIsReserved(int isReserved) {
		this.occupied = isReserved;
	}
	
	public ArrayList<Reservation> getTableReservation(){
		try {
			reservList = new ArrayList<Reservation>();
			Scanner s = new Scanner(new BufferedReader(new FileReader("reservation.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Reservation r = new Reservation();
				r.setDate(temp[0]);
				r.setStartTime(temp[1]);
				r.setCustomerName(temp[2]);
				r.setPax(temp[3]);
				r.setContact(temp[4]);
				r.setTableNum(temp[5]);
				occupied = Integer.parseInt(temp[6]);
				reservList.add(r);
			}
			s.close();
			System.out.println("reservList: " + reservList.size());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reservList;
	}
	
	public void removeTableReservation() throws ParseException{
		int choice;
		sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();
		
		do{
			int indexCount=1;
			reservList = getTableReservation(); //read data from txt and store in reservation object
			System.out.println("Reservation Date" + "    " + "Reservation Time" + "     " + "Table No." + "    " + 
					"Customer Name" + "    " + "Table Pax" + "    " + "Contact No.");
			for(Iterator<Reservation> r = reservList.iterator(); r.hasNext();){ // display the reservation objects
				Reservation reservation = r.next();
				reservation.setEndTime();
				System.out.println( indexCount +" " +reservation.getDate()+ "          " + sdf.format(reservation.getStartTime())+ "-" 
						+ sdf.format(reservation.getEndTime()) + "          " + reservation.getTableNum()	+ "             " + reservation.getCustomerName()
						+"            " + reservation.getPax() + "            " + reservation.getContact());
				indexCount++;
			}
			System.out.println("Select reservation to remove by its index number. Press 0 to exit");
			choice = sc.nextInt();
			//writing to file
			if(choice>0 && choice < reservList.size() || choice == reservList.size()){ //check user input within range
				reservList.remove(choice-1); //remove reservation from arraylist
				//overwrite the file with remaining objects in the list
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reservation.txt", false)))) {
					out.println("date,time,name,pax,contact,tableNo,occupied");
					for(Iterator<Reservation> r = reservList.iterator(); r.hasNext();){
						Reservation reservation = r.next();
					out.print(reservation.getDate()+","+sdf.format(reservation.getStartTime())+","+reservation.getCustomerName()+","+reservation.getPax()+","+
				    			reservation.getContact()+","+ reservation.getTableNum());
						if(currentTime.after(reservation.getStartTime())&&currentTime.before(reservation.getEndTime())){
							out.print(",1\n");
						}
						else
							out.print(",0\n");
					}
					System.out.println("Reservation Successful removed");
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
			}
			else
				if(choice==0)
					break;
			else
				System.out.println("Invalid input");
		}while(choice!=0);
		
			
	}

	public ArrayList<Reservation> getTableReservation(String Date){
		reservList = new ArrayList<Reservation>();
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("reservation.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Reservation r = new Reservation();
				r.setDate(temp[0]);
				r.setStartTime(temp[1]);
				r.setCustomerName(temp[2]);
				r.setPax(temp[3]);
				r.setContact(temp[4]);
				r.setTableNum(temp[5]);
				occupied = Integer.parseInt(temp[6]);
				if(r.getDate().equals(Date))
					reservList.add(r);
			}
			s.close();
			System.out.println("reservList: " + reservList.size());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reservList;
		
	}
	
	public ArrayList<Reservation> currentHourTableReservation(){
		reservList = new ArrayList<Reservation>();
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("reservation.txt")));
	
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Reservation r = new Reservation();
				//Date startTime = sdf.parse(temp[0]+" "+temp[1]);
				if(temp[6].equals("1")){
					r.setDate(temp[0]);
					r.setStartTime(temp[1]);
					r.setCustomerName(temp[2]);
					r.setPax(temp[3]);
					r.setContact(temp[4]);
					r.setTableNum(temp[5]);
					occupied = Integer.parseInt(temp[6]);
					reservList.add(r);
				}
				
			}
			s.close(); 
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reservList;
		
	}
	
	
	public boolean reserveTable(String date,Date startTime, String pax, String customerName, String contact, String tableNum)throws ParseException{
		
		boolean reserved = false;
		getTableReservation(date);
		String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        //if size 0,  means reservation has been made on specified date in getTableReservation, go else to create new reservation
        if(reservList.size()>0){
	        for(Iterator<Reservation> i = reservList.iterator(); i.hasNext(); ) {
	        	Reservation r = i.next();
	        	if(r.getPax().equals(pax)){
	        			r.setEndTime();
	        			cal.setTime(startTime);
	        			cal.add(Calendar.HOUR_OF_DAY, 1);
	        			Date endTime = cal.getTime();
	        			//below condition: both the start and end time of the potential reservation must not be within the
	        			//start and end time of any reservation object
	        			if(!(startTime.after(r.getStartTime()) && startTime.before(r.getEndTime()))
	        					|| !(endTime.after(r.getStartTime()) && endTime.before(r.getEndTime()))){
	        				//check if both reservations has same start time or same end time	
	        				if(!((startTime.getTime() == r.getStartTime().getTime()) || (endTime.getTime() == r.getEndTime().getTime()))){
	        					//check if reservation duration is within 60 to avoid a senario where start time is early and end time is later
	        					//which might overlap another reservation.
		        				if((startTime.getTime() - endTime.getTime())/(60 * 1000) % 60 <= 60*60*1000){
		        					try
		        					{
		        						//add to 1 more field occupied = 0
		        					    String filename= "reservation.txt";
		        					    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		        					    fw.write("\r"+date + "," + sdf.format(startTime) + "," + customerName + "," + pax + "," + contact + "," + tableNum +","
		        					    		+ "0");//appends the string to the file
		        					    fw.close();
		        					    reserved = true;
		        					}
		        					catch(IOException ioe)
		        					{
		        					    System.err.println("IOException: " + ioe.getMessage());
		        					}
		        					
		        				}
	        				}
	        				
	        			}
	        	}
        			
	        }
        }else{
        	try
			{
			    String filename= "reservation.txt";
			    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			    fw.write("\n"+ date + "," + sdf.format(startTime) + "," + customerName + "," + pax + "," + contact + "," + tableNum+","
			    		+ "0");//appends the string to the file
			    fw.close();
			    reserved = true;
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
        }
        return reserved;
	}
	
	
public Reservation reserveAllocatedTable(String date,Date startTime, String pax, String tableNum)throws ParseException, IOException{
	sc = new Scanner(System.in);
	reservList = new ArrayList<Reservation>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Date endTime = df.addAnHour(startTime);
	reservList = getTableReservation(date);
	//this method check reservation base on tableNo, first iteration checks tableNo1, and so at second iteration the tableNo 1 is removed
	//it has to be remove so the checking can work.
	for(Iterator<Reservation> tempReserv = reservList.iterator(); tempReserv.hasNext();){
		Reservation r1 = tempReserv.next();
		if(Integer.parseInt(r1.getTableNum())<Integer.parseInt(tableNum)){
			tempReserv.remove();
		}
	}
	if(reservList.size()>0){
		
		for(Reservation r: reservList){
			if(size == Integer.parseInt(r.getPax())){
				Date rStartTime = sdf.parse(r.getDate()+ " " + df.getTime(r.getStartTime()));
				Date rEndDate = df.addAnHour(rStartTime);
				r.setEndTime();
				//below condition: both the start and end time of the potential reservation must not be within the
				//start and end time of any reservation object
				//does not overlap and tableNo is not the same then reserve
				if(!((startTime.after(rStartTime) && startTime.before(rEndDate) && tableNum.toString().equals(r.getTableNum()))
						|| endTime.after(rStartTime) && endTime.before(rEndDate) && tableNum.toString().equals(r.getTableNum()) )){
					//check if both reservations has same start time or same end time	
					if(!((startTime.equals(rStartTime)) || endTime.equals(rEndDate))){
						//check if reservation duration is within 60 to avoid a senario where start time is early and end time is later
						//which might overlap another reservation.
	    				if((startTime.getTime() - endTime.getTime())/(60 * 1000) % 60 <= 60*60*1000){
	    					System.out.println("Enter Customer Name");
	    					String customerName = sc.nextLine();
	    					System.out.println("Enter Customer Contact");
	    					String contact = sc.nextLine();
							String filename= "reservation.txt";
							FileWriter fw = new FileWriter(filename,true); //the true will append the new data
							fw.write("\r"+date + "," + df.getTime(startTime) + "," + customerName + "," + pax + "," + contact + "," + tableNum+","
    					    		+ "1");//appends the string to the file
							fw.close();
							this.occupied = 1;
							return r;
	    				}else{
	    					System.out.println("Creation of reservation fail");
	    					return null;
	    				}
					}else{
						System.out.println("This time overlaps the time of a reservation");
						return null;
					}
				}else{ 
					System.out.println("This time overlaps the time of another reservation");
					return null;
				}
			}
		}
	}else{
		System.out.println("Enter Customer Name");
		String customerName = sc.nextLine();
		System.out.println("Enter Customer Contact");
		String contact = sc.nextLine();
		String filename= "reservation.txt";
		FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		fw.write("\r"+date + "," + df.getTime(startTime) + "," + customerName + "," + pax + "," + contact + "," + tableNum+","
	    		+ "1");//appends the string to the file
		fw.close();
		this.occupied = 1;
		return new Reservation(date, df.getTime(startTime),pax,customerName,contact,tableNum);
	}
	return null;
	

	}

public void removeReservationOnPayment(int tableNum) throws ParseException{
	//tableNum, occupied bit=1
	//writing to file
	Calendar cal = Calendar.getInstance();
	Date currentTime = cal.getTime();
	reservList = getTableReservation();
	for(Iterator <Reservation> r = reservList.iterator(); r.hasNext(); ){
		Reservation reservation = r.next(); 
		if(Integer.parseInt(reservation.getTableNum())==tableNum){
			r.remove();
		}
	}
	//tableNum, occupied bit=1
	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reservation.txt", false)))) {
	out.println("date,time,name,pax,contact,tableNo,occupied");
	for(Iterator<Reservation> r = reservList.iterator(); r.hasNext();){
		Reservation reservation = r.next();
		reservation.setEndTime();
	out.print(reservation.getDate()+","+df.getTime(reservation.getStartTime())+","+reservation.getCustomerName()+","+reservation.getPax()+","+
    			reservation.getContact()+","+ reservation.getTableNum());
		if(reservation.getStartTime().before(currentTime)&&currentTime.before(reservation.getEndTime())){
			out.print(",1\n");
		}
		else
			out.print(",0\n");
	}
	System.out.println("Reservation Successfully removed");
	}catch (IOException e) {
    //exception handling left as an exercise for the reader
	}
	
}





}
