import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservation {
	private String date, customerName, pax, contact, tableNum;
	private Date startTime, endTime;
	String pattern = "HH:mm";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Calendar cal = Calendar.getInstance();
	
	//Calendar aDate = new GregorianCalendar(2013, 3, 5, 13, 30);
	Table table[] = new Table[10];
	
	public Reservation(){
		
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
		for(int i=0; i<10; i++){
			if(table[i].size== Integer.parseInt(pax)){
				System.out.println("i: " + i);
				tableNum = Integer.toString(table[i].getTableNum()+1);
				return table[i].reserveTable(date, startTime, pax, customerName, contact, tableNum);
			}
		}
		return false;
		
	}
	
	
		
}
	

