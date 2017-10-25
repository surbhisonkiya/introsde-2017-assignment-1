package xmlProfile;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// XmlRootElement to define the root node of the XML tree to which this class will be serialized.
@XmlRootElement(name = "person")	
// XmlType to define the order in which the nodes of person are to be written.
@XmlType(propOrder = { "firstname", "lastname", "birthdate", "pActivity" })
// XmlAccessorType to indicate to use FIELD property to create the mapping.
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	private String firstname;		
	private String lastname;		
	// XmlElement indicates the element to use for this field.
	@XmlElement(name="activitypreference")
	private ActivityProfile pActivity;	
	private String birthdate;
	// XmlAttribute to indicate that this field will be serialized as an attribute.
	@XmlAttribute(name="id")
	private Long personId;
	
	public Person(Long personId, String fname, String lname, String birthdate, ActivityProfile pa) {
		this.setPersonId(personId); 	
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 	
		this.pActivity=pa;
	}
	
	public Person(Long personId, String fname, String lname, String birthdate) {
		this.setPersonId(personId); 	
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 
		this.pActivity=new ActivityProfile();
	}
	
	public Person() {
		this.firstname="Surbhi";
		this.lastname="Sonkiya";
		this.pActivity=new ActivityProfile();

		// setting personId to a random number between 1 and 9999
		this.personId = Math.round(Math.floor(Math.random()*9998)+1); 
		this.birthdate = this.getRandomDate();
	}
	//getters and setters for class variables
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public ActivityProfile getAProfile() {
		return pActivity;
	}
	public void setAProfile(ActivityProfile aProfile) {
		this.pActivity = aProfile;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/* 
	 * creating a random date between now and 1950
	 */
	private String getRandomDate() {
		//get the 1.random year and 2.random month
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); 		
		int year = (int) Math.round(Math.random()*(currentYear-1950)+1950); 
																			//    between 1950 and currentYear
		int month = (int) Math.round(Math.floor(Math.random()*11)+1);		
		// 3. select a random day in the selected month
		// 3.1 prepare a months array to store how many days in each month
		int[] months = new int[]{31,28,30,30,31,30,31,31,30,31,30,31};
		// 3.2 if it is a leap year, feb (months[1]) should be 29
		if ((currentYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0))) {
			months[1] = 29;
		}
		long day = Math.round(Math.floor(Math.random()*(months[month-1]-1)+1));
		return ""+year+"-"+month+"-"+day;
	}
}

