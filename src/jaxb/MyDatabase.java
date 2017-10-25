package jaxb;


import java.util.Random;

import xmlProfile.ActivityProfile;
import xmlProfile.Person;


public class MyDatabase {
	private static String fNames[] = new String[] {"Rohit","Surbhi", "Pooja","Parul","HariOm"};
	private static String lNames[] = new String[] {"Prakash","Sonkiya", "Pandey","Tank","Sharma"};
	private static String bDates[] = new String[] {"13-06-1992","26-09-1991","14-03-1994","14-08-1987", "17-11-2000"};
	static Random rnd = new Random();
	static ActivityProfile tableTennis = new ActivityProfile(rnd.nextInt(100),"Table Tennis", "Smash the ball on the table", "Stuttgart", "13-10-2017");
	static ActivityProfile yoga = new ActivityProfile(rnd.nextInt(100),"Yoga", "Yoga for health", "Trento", "11-09-2016");
	static ActivityProfile chess = new ActivityProfile(rnd.nextInt(100),"Chess", "Play the mind of other person", "Bangalore", "18-08-2017");
	static ActivityProfile running = new ActivityProfile(rnd.nextInt(100),"Running", "Running on tracks", "Mumbai", "02-05-2017");
	static ActivityProfile badminton = new ActivityProfile(rnd.nextInt(100),"Badminton", "National tournaments", "Jaipur","01-03-2017");
	
	private static ActivityProfile pActivities[] = new ActivityProfile[] {tableTennis,yoga,chess,running,badminton};
	
	public static void initializeDB(PersonActivityAnnotation people) {
		Random random = new Random();
		int max = 5;

		for (int i = 0; i<5;i++) {
			//Person person = new Person((long) i+1,fNames[random.nextInt(max)], lNames[random.nextInt(max)], bDates[random.nextInt(max)], pActivities[random.nextInt(max)]);
			Person person = new Person((long) i+1,fNames[i], lNames[i], bDates[i], pActivities[i]);
			people.getpersonData().add(person);
		}
	}

}
