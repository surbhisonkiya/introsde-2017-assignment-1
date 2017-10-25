import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XPATHActivityProfileReader {


	public static void main(String[] args) throws ParserConfigurationException, SAXException,
	IOException, XPathExpressionException {
		
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();

		try{
			Document doc = builder.parse("people.xml");

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

			//prints the person's description of activity
			getActivityDescription(doc, xpath, "0005");		        
			
			//prints the person's place of activity
			getActivityPlace(doc, xpath, "0005");		

			//prints all the persons with their details from the list
			getPersonWithId(doc, xpath);

			String activityDetails = getActivityPreference(doc, xpath, "0005");
			System.out.println();
			System.out.println(activityDetails);

			//prints all the person and their details where activity start date satisfies the operator condition passed in the argument
			getPersonByDate(doc, xpath, "2017-13-10T11:50:00.0", ">");

		}
		catch  (  SAXException | IOException e) {
			e.printStackTrace(); 
		}

	}



	private static void getActivityDescription(Document doc, XPath xpath, String id) {
		String description = null;

		try {

			XPathExpression expr =xpath.compile("/people/person[@id='" + id + "']/activitypreference/description/text()");
			description = (String) expr.evaluate(doc, XPathConstants.STRING);    

			System.out.println("Person activity description with person id "+id +" is - "+ description);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}


	}


	private static void getActivityPlace(Document doc, XPath xpath, String id) {
		String name = null;

		try {
			XPathExpression expr =xpath.compile("/people/person[@id='" + id + "']/activitypreference/place/text()");
			name = (String) expr.evaluate(doc, XPathConstants.STRING);
			System.out.println();
			System.out.println("Person activity place with person id "+id+" is: " + name);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}


	}


	private static List<String> getPersonWithId(Document doc, XPath xpath) {
		List<String> list = new ArrayList<>();
		try {
			//create XPathExpression object

			XPathExpression expr = xpath.compile("/people/person[@id]/firstname/text()");
			Element docEle = doc.getDocumentElement();

			NodeList nl = docEle.getChildNodes();
			System.out.println();
			System.out.println("All persons in the list along with their details are as following:");
			if (nl != null) {
				int length = nl.getLength();
				for (int i = 0; i < length; i++) {
					if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) nl.item(i);
						if (el.getNodeName().contains("person")) {
							String firstname = el.getElementsByTagName("firstname").item(0).getTextContent();
							String lastname = el.getElementsByTagName("lastname").item(0).getTextContent();
							String birthdate = el.getElementsByTagName("birthdate").item(0).getTextContent();
							String activityName = el.getElementsByTagName("name").item(0).getTextContent();
							String description = el.getElementsByTagName("description").item(0).getTextContent();
							String place = el.getElementsByTagName("place").item(0).getTextContent();
							String startdate = el.getElementsByTagName("startdate").item(0).getTextContent();

							System.out.println("Full Name:  "+firstname+" "+lastname+", Birthdate: "+birthdate+",  Activity name: "+activityName+", description: "+description+", place: "+place+ ", start date:  "+startdate);
						}
					}
				}
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static String getActivityPreference(Document doc, XPath xpath, String id) {

		String activityDetails =null;
		String name,description,place, startDate = null;

		try {
			//create XPathExpression object
			XPathExpression expr =xpath.compile("/people/person[@id='" + id + "']/activitypreference/name/text()");
			name = (String) expr.evaluate(doc, XPathConstants.STRING);

			XPathExpression expr1 =xpath.compile("/people/person[@id='" + id + "']/activitypreference/description/text()");
			description = (String) expr1.evaluate(doc, XPathConstants.STRING);

			XPathExpression expr2 =xpath.compile("/people/person[@id='" + id + "']/activitypreference/place/text()");
			place = (String) expr2.evaluate(doc, XPathConstants.STRING);

			XPathExpression expr3 =xpath.compile("/people/person[@id='" + id + "']/activitypreference/startdate/text()");
			startDate = (String) expr3.evaluate(doc, XPathConstants.STRING);

			activityDetails = "Actvity preference details of person with id "+id+" is -  Activty name: "+name+ ", description: "+ description+", place: "+ place+", start date: "+startDate;

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return activityDetails;
	}


	public static void getPersonByDate(Document doc, XPath xpath,String date, String condition) throws XPathExpressionException {
		String startDate = null;
		String date3 = null;
		String date6=null;
		NodeList nodes1=null;


		try {    		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (date!=null) {        	
				String newDate[]= date.split("T");
				String date7 = newDate[0];
				String date8 = newDate[1];
				date3 = date7+" "+date8;

			}

			XPathExpression expr =xpath.compile("//activitypreference/startdate/text()");
			startDate = (String) expr.evaluate(doc, XPathConstants.STRING);
			Date date1 = sdf.parse(date3);
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET); 
			String date4=null;
			String date5=null;
			Date date2=null;

			for (int i =0; i<nodes.getLength();i++) {    
				if (nodes.item(i).getTextContent()!=null) {         	
					String newDate[]= nodes.item(i).getTextContent().split("T");
					date4 = newDate[0];
					date5 = newDate[1];
					date6 = date4+" "+date5;
					date2 = sdf.parse(date6);
					
					if (condition==">"){
						if (date1.compareTo(date2) > 0) {
							String useThis = date4+"T"+date5;
							XPathExpression expr1 =xpath.compile("//person/activitypreference[startdate='" + useThis + "']");          				       				 
							nodes1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
						}
					}           		
					else if(condition=="<"){
						if (date1.compareTo(date2) < 0) {
							String useThis = date4+"T"+date5;
							XPathExpression expr1 =xpath.compile("//person/activitypreference[startdate='" + useThis + "']");          				       				 
							nodes1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
						}
					}
					else if (condition=="=") {
						if (date1.compareTo(date2) == 0) {
							String useThis = date4+"T"+date5;
							XPathExpression expr1 =xpath.compile("//person/activitypreference[startdate='" + useThis + "']");          				       				 
							nodes1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
						}
					}
					else {
						System.out.println("How to get here?");
					}

				}
			}
			System.out.println();
			System.out.println("Start date comparision result");
			for (int j=0;j<nodes1.getLength();j++) {
				System.out.println("Person detail: " + nodes1.item(j).getParentNode().getTextContent());

			}

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}






}
