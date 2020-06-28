package fileExtractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/***********************
 * 
 * @author Justin Jhern
 * 
 * Does stuff to take the data from the txt and sort it out into an arraylist before printing it out. Read the code and figure it out. A 17 year old wrote this, you can probably understand what its trying to do.
 * 
 * @param
 * @return
 *
 **********************/
public class fileExtractor {
	static ArrayList<String> output = new ArrayList<String>();//the stuff that will be displayed
	static String whatPart = "IOCHANNEL";//its just labeling. what part refers to whats being referenced. IOChannel, dps, etc

	public static void main(String[] args) throws Exception {
		// pass the path to the file as a parameter
		File file;//Initializing file up here so java doesnt have an anuerysm
		try{
			if(args!=null) {
				 file = new File(args.toString()); // uses args to get the file path. Probably wont work				
				}
			}catch(Exception e) {			
		}
		fileChooser fc = new fileChooser();// creates a fileChooser
		file = new File(fc.getFilePath()); // gets the file using the path to the file using the GUI thingy
		//creates buffered reader
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;//the string that will pretty much be iterated through by the code. The code runs line by line through the txt document
		int counter = 0;//this counter is used for the labeling. Pretty much if its 0, the label is IOCHANNEl, if its 1 its DPS
		Boolean isKeyword = false;//to know when to search or not. it searches when the line hits IOCHANNEL or DPS, otherwise it doesnt

		while ((st = br.readLine()) != null) {//while loop, iterates through all the lines
			String str = st.replaceAll("\\s+", "");// removes all spaces
			if (!(str.isEmpty()) && str.charAt(0) != '#') {// ignores the line if it starts with pound or ignores if null
				// System.out.println(str);//only for testing
				if (str.contains("testhead"))output.add("Global: Testhead: " + str.substring(str.indexOf("=") + 1, str.length() - 1));//searches for "testhead" and outputs the value + some stuff to make it easier to read (labeling)
				if (str.contains("IOCHANNEL") || str.contains("DPS")) {//Just a test to know when to start "searching"
					if (counter == 1)whatPart = "DPS";//counter, read above
					isKeyword = true;//Read above
					counter++;//read above
				}

				if (isKeyword) {//read above
					String temp = (search(str));//runs the search method on the string to extact values
					if (temp != (null))output.add(whatPart + ": " + temp);//if its not null, it will label all the values to make it easier to look at

				}
				if (str.contains("BOARDCONF"))isKeyword = false;//turns off searching ,saves time instead of searching through every line. Also doesnt give useless data
			}
		}
		for (int i = 0; i < output.size(); i++)System.out.println(output.get(i));//prints out everything in the array 
	}
/*
 * Extracts values from a string and labels it
 * 
 * @param string
 * @return a nicely labeled string with all the values that are organized properly
 */
	public static String search(String str) {
		String number = null;//default string
		String type = null;//default string
		if (str.contains(":"))number = str.substring(0, str.indexOf(":"));//tests for the case of there being a colon
		if (str.contains("=") && str.contains(","))type = str.substring(str.indexOf("=") + 1, str.indexOf(","));//test for the case of there being a comma 
		else if (str.contains("=") && !str.contains(","))type = str.substring(str.indexOf("=") + 1, str.length());//tests for comma. if there isnt a comma, it usually ends there
		if (number == null || type == null)return null;//returns null of one of the values are null. We dont want it
		try {//try because if the code screws up it doesn't crash the program
			String numberOne = number.substring(0, number.indexOf("-"));//splits the numbers in two, first part of numbers
			String numberTwo = number.substring(number.indexOf("-") + 1, number.length() - 2);//splits the numbers in two, second part of numbers
			if (numberOne.length() > 3)numberOne = numberOne.substring(0, 3);//Checks to see if its larger than 3 so it doesnt break - extracts first 3 values
			if (numberTwo.length() > 3)numberOne = numberOne.substring(0, 3);//Checks to see if it larger than 3 so it doesnt break - extracts first 3 values
			number = numberOne + "-" + numberTwo;//combines both numbers
			String lastTwo=type.substring(type.length()-2,type.length());//takes the last 2 chars of type
			 if(lastTwo.contains("HC")||type.contains("HV"))type=type.substring(0, type.length()-3);//checks to see if there is HC or HV, removes them if there is
		} catch (Exception e) {//who cares about catching exceptions LOL
		}
		return "Number: " + number + " Type: " + type;//returns everyting all nice and labeled

	}
}
