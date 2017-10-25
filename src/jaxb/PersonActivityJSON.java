package jaxb;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fasterxml.jackson.databind.SerializationFeature;



public class PersonActivityJSON {
	public static PersonActivityAnnotation people = new PersonActivityAnnotation();

	public static void main(String[] args) throws Exception {
		
		MyDatabase.initializeDB(people);
		
		// JSON Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Add the JSON Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// mapper configurations
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	//getting the output in the string variable
        String result = mapper.writeValueAsString(people);
	//print the output in the console
        System.out.println(result);
	//print the output in the "people.json" file
        mapper.writeValue(new File("people.json"), people);
    }
}
