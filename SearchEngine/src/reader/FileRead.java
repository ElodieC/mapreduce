package reader;

import java.io.*;
import java.util.ArrayList;
public class FileRead 
{
	//Classe qui écrit les lignes qui nous intéressent
	
	public static String readFromFile(String file, ArrayList<Long> lines)
	{
		String result = "";
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream("/home/hduser/Téléchargements/"+file+".txt");
			// FileInputStream fstream = new FileInputStream("/home/hduser/Téléchargements/hugo_claude_gueux--utf8.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				// Print the content on the console
				result += strLine;
			}
			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		return result;
	}
}