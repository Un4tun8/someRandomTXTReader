package fileExtractor;

import javax.swing.JButton;
import javax.swing.JFileChooser;
/***********************
 * 
 * @author Justin Jhern
 *
 * File chooser pops up a window that lets you choose files
 * 
 *@param
 *@return
 *
 **********************/


public class fileChooser {
	
	String filePath; //just a string for the file path. Hence the name.
	//Its a contructor 
	fileChooser(){
		selectFile();
	}		
	
	/*//THIS METHOD IS FOR TESTING ONLY
	 * public static void main(String[] args) {
		fileChooser fc= new fileChooser();		
	}*/
	
	/*
	 * creates a pop up GUI that allows user to select file. Takes that file and sets filePath variable to the path
	 * 
	 * @param 
	 * @return
	 */	
	public void selectFile() {
		JButton open = new JButton(); //creates open button
		JFileChooser fc = new JFileChooser(); //creates a file chooser
		fc.setCurrentDirectory(new java.io.File("C:\\")); //sets the default directory (its the c drive lmao)"
		fc.setDialogTitle("SUPER DUPER FILE CHOOSER!"); //SUPER DUPER FILE CHOOSER :D  sets the title
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //Shows only files. Doesnt work for some reason. Maybe im dumb D:
		if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION) {//does nothing			
		}
		setFilePath(fc.getSelectedFile().getAbsolutePath());
		System.out.println(getFilePath()); //just a line for testing 
	}
	/*
	 * Sets filePath variable
	 * 
	 * @param File path (string)
	 * @return
	 */
	public void setFilePath(String str) {
		filePath = str;
	}
	
	/*
	 * Returns filePath variable
	 * 
	 * @param 
	 * @return Returns filePath variable
	 */
	public String getFilePath() {
		return filePath;
	}
}
