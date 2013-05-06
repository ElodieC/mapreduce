package window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Classe qui Met en place le log de ce que souhaite le programmer
 * @author Renou Clarisse
 * @version 1.0
 *
 */
public class Logger {	
	private static String logFileName;	
	private static BufferedWriter bLog;
	private static String pathLog;
	
	/**
	 * CreateLogger permet d'initialiser le logger en indiquant le chemin de création du fichier
	 * pathLog et son nom logFileName.
	 * @exception IOException en cas de probleme de création du fichier de log
	 */
	public static void createLogger (){
		String pathClarisse = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/" +
				"mapreduce/SearchEngine/src/window/";
		String pathElodie = "/home/hduser/hadoopMR/";
		String pathMickael = "C:/Users/Olivier Catherine/workspace/hadoopMR";
		//pathLog = pathClarisse;
		pathLog=pathElodie;
		//pathLog=pathMickael;
		logFileName = "ProgramLog.txt";
		try {
			bLog = new BufferedWriter(new FileWriter(pathLog+""+logFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode principale qui permet d'ajouter à la suite du fichier la phrase de log que l'on veut
	 * @param toLog String le commentaire de log
	 * @exception en cas de probleme d'ouverture ou d'écriture du fichier capture l'exception
	 */
	public static void addInLog(String toLog){
		try {
			bLog.write(toLog);
			bLog.write("\n");
			bLog.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
