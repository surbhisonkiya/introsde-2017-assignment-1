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

			File file = new File("JAXBperson.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonActivityAnnotation.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(personActivity, file);
			jaxbMarshaller.marshal(personActivity, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }

		}
	
	
}
