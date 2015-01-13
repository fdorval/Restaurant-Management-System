import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;


public class Table {
	
	int size, tableNum, isReserved;
	ArrayList<Reservation> reservList = new ArrayList<Reservation>();

	public Table(int size, int tableNum, int isReserved){
		this.size = size;
		this.tableNum = tableNum;
		this.isReserved = isReserved;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getTableNum(){
		return tableNum;
	}
	
	public int getIsReserved(){
		return isReserved;
	}

	private void getTableReservation(String Date){
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("D:/myFiles/NTU/Java_Workspace/RestaurantManagement/file/reservation.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.next().split(",");
				Reservation r = new Reservation();
				r.setDate(temp[0]);
				r.setStartTime(temp[1]);
				r.setCustomerName(temp[2]);
				r.setPax(temp[3]);
				r.setContact(temp[4]);
				r.setTableNum(temp[5]);
				if(r.getDate().equals(Date))
					reservList.add(r);
			}
			s.close();
			System.out.println("reservList: " + reservList.size());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public boolean reserveTable(String date,Date startTime, String pax, String customerName, String contact, String tableNum)throws ParseException{
		
		boolean reserved = false;
		getTableReservation(date);
		String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
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
	        					    String filename= "D:/myFiles/NTU/Java_Workspace/RestaurantManagement/file/reservation.txt";
	        					    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	        					    fw.write("\r"+ date + "," + sdf.format(startTime) + "," + customerName + "," + pax + "," + contact + "," + tableNum);//appends the string to the file
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
        return reserved;
	}
	
}
