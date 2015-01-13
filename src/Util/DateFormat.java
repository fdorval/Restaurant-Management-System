package Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateFormat {
	SimpleDateFormat sdf;
	Calendar cal;
	
	public Date setYear(Date date){
		cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.YEAR, 2004);
	    return date = cal.getTime();
	}
	
	public Date addAnHour(Date date){
		cal = Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.HOUR_OF_DAY, 1);
	    return date = cal.getTime();
		
	}
	
	public String getTime(Date date){
		 sdf = new SimpleDateFormat("HH:mm");
		 return sdf.format(date);
	}
	
	public Date formatStringTimetoDate(String time) throws ParseException{
		sdf = new SimpleDateFormat("HH:mm");
		Date date = sdf.parse(time);
		return date;
	}
	
	public String getDate(Date date){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = sdf.format(date);
		return s;
	}
	
	public String formatStringDate(String date) throws ParseException{
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date date1 = sdf.parse(date);
		return sdf.format(date1);
	}
	
	public Date formatStringToDate(String date) throws ParseException{
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(date);
	}
	
	
}
