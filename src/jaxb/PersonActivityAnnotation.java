package jaxb;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import xmlProfile.Person;

@XmlRootElement(name="people") //root element from the xml file
@XmlAccessorType(XmlAccessType.FIELD)

public class PersonActivityAnnotation {
	@XmlElement(name="person")
	private List<Person> personData = new ArrayList<Person>();
	
	public PersonActivityAnnotation() {
	}
	//getters and setters
	public List<Person> getpersonData() {
		return personData;
	}

	public void setpersonData(List<Person> pData) {
		this.personData = pData;
	}
}





