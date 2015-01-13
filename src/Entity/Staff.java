package Entity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class Staff {
	
	
	String staffName, gender, jobTitle, staffID;
	Menu m;
	Reservation r;
	
	public Staff(String staffName,String staffID,String gender,String jobTitle){
		
		this.staffName = staffName;
		this.staffID = staffID;
		this.gender = gender;
		this.jobTitle = jobTitle;
		
		/*
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("D:/myFiles/NTU/Java_Workspace/RestaurantManagement/file/staff.txt")));
			s.useDelimiter(",");
			s.nextLine();
			while (s.hasNext()) {
				//System.out.println(s.next());
				staffName = s.next();
				staffID = s.next();
				gender = s.next();
				jobTitle = s.next();
			}
			
			Scanner s = new Scanner(new BufferedReader(new FileReader("D:/myFiles/NTU/Java_Workspace/RestaurantManagement/file/staff.txt")));
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
				if(r.getDate().equals(Date))
					reservList.add(r);
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		*/
	}
	public Staff(){
		
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getStaffID(){
		return staffID;
	}
	
	public String getStaffName(){
		return staffName;
	}
	
	
	
	
	

}
