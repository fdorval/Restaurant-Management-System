import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class test {
	


	public static void main(String[] args)throws ParseException {
		// TODO Auto-generated method stub
		boolean result;
		Reservation r = new Reservation();
		r.setDate("2014/11/02");
		r.setContact("82235621");
		r.setCustomerName("derek");
		r.setStartTime("21:00");
		r.setPax("2");
		result = r.makeReservation();
		
		System.out.println(result);
		
	}

}
	

