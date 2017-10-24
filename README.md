IntroSDE-2017-Assignment-1
Name: Surbhi Sonkiya
Email id: Surbhi.sonkiya@studenti.unitn.it
Matricola: 196511
Master student Software and Service Architectures (EIT first year)

Project: Reading/Writing objects to and from XML and JSON.
1.	Code description:

i.	“(default package)”  –
1.	XPATHActivityProfileReader.java – to create the xpath object and has all the queries which prints the output of the tasks mentioned in the section i - iv under the next subheading “Task description”.

ii.	“generatedClass” package – It is an auto generated package which generates below mentioned two classes when the “generate” task is executed from the “build.xml” file.
1.	ObjectFactory.java
2.	People.java

iii.	“jaxb” package –
1.	MyDatabase.java – acts as database of 5 persons and their activity details. Used for JAXB Marshalling and JSON Marshalling.
2.	PersonActivityAnnotation.java – describes annotation for xml root and used for JAXB Marshalling.
3.	PersonActivityJAXBMarshaller.java – initialises database declared in “MyDatabase.java” class and contains commands to marshal from java to xml file named “JAXBperson.xml”.
4.	PersonActivityJAXBUnmarshaller.java – instantiates JAXBContext (generatedClass), XML schema (people.xsd) and converts input from XML file (people.xml) to java output. Prints output in the console.
5.	PersonActivityJSON.java - initialises database declared in “MyDatabase.java” class and contains commands to marshal from java to json file named “people.json”.

iv.	“xmlProfile” package –
1.	ActivityProfile.java – declares all the activity preferences variables.
2.	Person.java – Mapping defined from XML file in this java class.

v.	“people.xml” – XML file containing 25 persons and their activity preferences details.
vi.	“people.xsd” – corresponding XML schema document for “people.xml” file.
vii.	 “build.xml” – To build the project – contains clean, compile, generate and execute.evaluation tasks. It also contains tasks to manage dependencies (ivy).

2.  Task description:
i.	Use XPath to get activity description and activity place by providing person id as the parameter.
ii.	Make a function that prints all people in the list with details.
iii.	Make a function that accepts the person id as parameter and prints the details within the ActivityPreference of the person with that id.
iv.	Make a function which accepts a date and an operator (=, >, <) as parameters and prints people which preferred activity start_date fulfill that condition.
v.	Create the XML schema XSD file for the example XML document of people.
vi.	Write a java application that does the marshalling and un-marshalling using classes generated with JAXB XJC.
vii.	Make the java application to convert also JSON.

Execution: Below are the steps to execute the code.
1.	Clone my repository from github to your local machine.
2.	Import the project folder in your eclipse workspace.
3.	To Add Ivy libraries – right click on “ivy.xml” and click on “Add Ivy Library…” option.
4.	Right click on build.xml -> run as -> 2 ant built… -> select “compile”-> Click ‘Run’.
5.	Right click on build.xml -> run as -> 2 ant built… -> select “generate” -> Click ‘Run’.
6.	Right click on build.xml -> run as -> 2 ant built… -> select “execute.evaluation” -> Click ‘Run’.

