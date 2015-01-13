

import java.io.File;
import java.io.IOException;



public class Membership {
	
	private String name;
	private String email;
	private String memberId;
	private long phoneNumber;
	private static final File membersFile = new File("members.txt");
	
	
	
	private Membership(String name, String email, String memberId,long phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.memberId = memberId;
		this.phoneNumber = phoneNumber;
	}

	
	private static void addMembership() throws IOException{
		//TODO: Method that writes from the members to the file 
	}
	
	private static void readMembership(){
		//TODO: Method that reads from the file to the members h
		
	}
	
	public static void initialize() throws IOException{
		if(!membersFile.isFile()){
			membersFile.createNewFile();
		}
		readMembership();
	}
	
	public static boolean addMember(String memberId, String name, String email, long phoneNumber) throws IOException{
		if(membersFile.get(memberId) != null){
			return false;
		}
		
		membersFile.put(memberId, new Member(name, email, memberId, phoneNumber));
		addMembership();
		return true;
	}
	// Add delete Member



	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getMemberId() {
		return memberId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	

}