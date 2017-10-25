package xmlProfile;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = { "name", "description", "place", "startDate" })

public class ActivityProfile {
	@XmlAttribute(name="id") 
	private int id;
	private String name;
	private String description;
	private String place;
	private String startDate;
	

	public ActivityProfile(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public ActivityProfile() {
		this.id = 1;		
		this.name = "Noname this time";
		this.description = "busy doing my favourite activity";
		this.setPlace("on the Earth");
		this.startDate = this.getRandomDate();
	}
	
	public ActivityProfile(int id, String name, String description, String place, String startDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.place = place;
		this.startDate = startDate;
	}
	// getters and setters for variables
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	private String getRandomDate() {
		//to generate 1. random year and 2.month.
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); 		
		int year = (int) Math.round(Math.random()*(currentYear-1950)+1950); 
																			//    between 1950 and currentYear
		int month = (int) Math.round(Math.floor(Math.random()*11)+1);		
		// 3. select a random day in the selected month
		// 3.1 prepare an array to store how many days in each month
		int[] months = new int[]{31,28,30,30,31,30,31,31,30,31,30,31};
		// 3.2 if it is a leap year, feb (months[1]) should be 29
		if ((currentYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0))) {
			months[1] = 29;
		}
		long day = Math.round(Math.floor(Math.random()*(months[month-1]-1)+1));
		return ""+year+"-"+month+"-"+day;
	}

	// Override toString.
	public String toString() {
		return "Name="+name+", Description="+description + "Place="+place+"StartDate="+startDate;
	}




}
