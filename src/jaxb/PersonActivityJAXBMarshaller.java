package jaxb;

import java.io.File;
import jaxb.PersonActivityAnnotation;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class PersonActivityJAXBMarshaller {
	public static void main(String[] args) {

		PersonActivityAnnotation personActivity = new PersonActivityAnnotation();
		MyDatabase.initializeDB(personActivity);

		  try {
			//create a new file to write the output
			File file = new File("JAXBperson.xml");
			//creating JAXBContext and marshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonActivityAnnotation.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//to print the output in the JAXBperson.xml
			jaxbMarshaller.marshal(personActivity, file);
			  //to print the output in the console
			jaxbMarshaller.marshal(personActivity, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }

		}
	
	
}
