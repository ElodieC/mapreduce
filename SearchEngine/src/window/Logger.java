package window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {	
	private static String logFileName;	
	private static BufferedWriter bLog;
	private static String pathLog;
	
	public static void createLogger (){
		String pathClarisse = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/" +
				"mapreduce/SearchEngine/src/window/";
		pathLog = pathClarisse;
		logFileName = "ProgramLog.txt";
		try {
			bLog = new BufferedWriter(new FileWriter(pathLog+""+logFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addInLog(String toLog){
		try {
			bLog.write(toLog);
			bLog.write("\n");
			bLog.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problems adding in the log file");
		}
	}
}
