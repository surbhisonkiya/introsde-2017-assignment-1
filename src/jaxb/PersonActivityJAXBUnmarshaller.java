package jaxb;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import generatedClass.*;
import generatedClass.People.Person;


public class PersonActivityJAXBUnmarshaller {

	public void unMarshall(File xmlDocument) {
		try {
			//creating JAXBContext with an instance using the auto generated package
			JAXBContext jaxbContext = JAXBContext.newInstance("generatedClass");
			
			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(new File("people.xsd")); // "people.xsd" is the XML schema
			unMarshaller.setSchema(schema);
			CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
			unMarshaller.setEventHandler(validationEventHandler);

		@SuppressWarnings("unchecked")
			People people = (People) unMarshaller
					.unmarshal(xmlDocument);
			List<generatedClass.People.Person> personList = (List<Person>) people.getPerson();
			//print the output of the unmarshall in the console in below format
			for (int i=0; i < personList.size();i++) {
				int j = i+1;
				System.out.println("Person: "+j+" ");
				System.out.println("Firstname: " + personList.get(i).getFirstname());
				System.out.println("Lastname: " + personList.get(i).getLastname());
				System.out.println("Birthdate: " + personList.get(i).getBirthdate());
				System.out.println("Activity preference: ");
				System.out.println("Name: "+ personList.get(i).getActivitypreference().getName() + ", Description: "+ personList.get(i).getActivitypreference().getDescription()+", Start date: "+ personList.get(i).getActivitypreference().getStartdate()+", Place: "+ personList.get(i).getActivitypreference().getPlace());
				System.out.println("");
			}
			
			
		} catch (JAXBException e) {
			System.out.println(e.toString());
		} catch (SAXException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] argv) {
		//input file for unmarshalling is "people.xml"
		File xmlDocument = new File("people.xml");		
		PersonActivityJAXBUnmarshaller jaxbUnmarshaller = new PersonActivityJAXBUnmarshaller();
		jaxbUnmarshaller.unMarshall(xmlDocument);

	}

	class CustomValidationEventHandler implements ValidationEventHandler {
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() == ValidationEvent.WARNING) {
				return true;
			}
			if ((event.getSeverity() == ValidationEvent.ERROR)
					|| (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

				System.out.println("Validation Error:" + event.getMessage());

				ValidationEventLocator locator = event.getLocator();
				System.out.println("at line number:" + locator.getLineNumber());
				System.out.println("Unmarshalling Terminated");
				return false;
			}
			return true;
		}

	}
}
