package fileExtractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/***********************
 * 
 * @author Justin Jhern
 * 
 * Its supposed to sort the data like my other class, but i overcomplicated it and now i dont udnerstand what i was trying to do. Whatever. Dont use this.
 * 
 * @param
 * @return
 *
 **********************/
public class brokenFileExtractor {
	static ArrayList<String> keyWordList = new ArrayList<String>(
			Arrays.asList("GLOBAL", "POGOCABLING", "IOCHANNEL", "ANALOGBOARD", "BOARDCONF", "INTERFACE_SECTION",
					"SOC_CABLING", "SOC_INSTRUMENTS", "AMC", "ETC", "DPS", "OSCILLOSCOPE"));// list of keywords
	static ArrayList<String> keyWordListTrue = new ArrayList<String>(
			Arrays.asList("GLOBAL", "IOCHANNEL", "ANALOGBOARD", "DPS"));// list of specific keywords that will be
																		// searched, doesn't include global because
																		// global is special case
	static ArrayList<String> output = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		// fileChooser fc = new fileChooser();// creates a fileChooser
		// File file = new File(fc.getFilePath()); // gets the file using the path to
		// the file
		String path = "C:\\Users\\derpy\\Desktop\\model.txt";
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file)); // creating a buffered reader
		String st; // temp string
		boolean isKeyWord = false;// if true, start searching thru lines below, if false, dont.
		while ((st = br.readLine()) != null) {// while loop to read line by line - removes all spaces
		/*
			 * if (st.equals("GLOBAL")){// checks specifically if the line is "global"
			 * System.out.println("Global"); output.add(searchGlobal(st));// runs special
			 * case global }
			 */
			st = br.readLine().trim().replaceAll("\\s+", "");// removes all spaces
			if (!(st.isEmpty()) && st.charAt(0) != '#') {// ignores the line if it starts with pound or ignores if null
				if (isKeyWord)
					output.add(search(st)); // if it is in the keyword list and keyword true list, search the values
				for (int i = 0; i < keyWordList.size(); i++) { // for loop for going through keyWordList
					if (st.equals(keyWordList.get(i))) {// checks if the line is in keyWordList
						isKeyWord = false;// if its one of the keywords, set the isKeyWordList to false
						for (int j = 0; j < keyWordListTrue.size(); j++) {// loop for traversing through the true
																			// keyword list
							if (st.equals(keyWordListTrue.get(j))) {// checks if it is one of the keywords that wants to
																	// be searched
								isKeyWord = true;// sets to search all lines below
								keyWordListTrue.remove(j);// removes from true keywords list
								break;
							}
						}

					}
				}
			}
		}
		for (int i = 0; i < output.size(); i++) {
			System.out.println("Ouput: " + output.get(i));
		}
	}

	public static String searchGlobal(String str) {
		if (str.contains("testhead")) {
			System.out.println("Testhead: " + str.substring(str.indexOf('='), str.length() - 1));
			return "Testhead: " + str.substring(str.indexOf('='), str.length() - 1);
		}
		return null;
	}

	public static String search(String str) {
		if (str.contains("testhead")) {
			System.out.println("Testhead: " + str.substring(str.indexOf('='), str.length() - 1));
			return "Testhead: " + str.substring(str.indexOf('='), str.length() - 1);
		}
		System.out.println("Search str: " + str);
		String numberLong = "0";
		if (str.contains(":"))
			numberLong = str.substring(0, str.indexOf(":")); // takes all values before ":" (usually number and dash and
																// number)
		String type;
		if (str.contains(","))
			type = str.substring(str.indexOf("=") + 1, str.indexOf(",")); // values after "HW=" and before ","
		else
			type = str.substring(str.indexOf("=") + 1, str.length() - 1);
		System.out.println("Numbers: " + numberLong + " Type: " + type);
		return "Numbers: " + numberLong + " Type: " + type;

	}

}
/*
 * Test head - DONE
 * 
 * IO Channel -3 starting numbers, HW = _______ not always a dash
 * 
 * Analog board 3 number and type
 * 
 * DPS number before type and type always comma first 3 digits and type -"DCS-"
 * and "HC"
 * 
 * ignore pound sign as first char
 */