package window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import path.Paths;
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
		
		pathLog=Paths.logFilePath;
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
			Date now = new Date();
			bLog.write(formatDate(now));
			bLog.write(" : ");
			bLog.write(toLog);
			bLog.write("\n");
			bLog.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 /**
	  * Met la date au format dd/MM/yyyy kk:mm:ss
	  * @param date Date la date à formatter
	  * @return la chaîne de caractères avec la date formattée
	  */
	 private static String formatDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		StringBuffer stringBuffer = new StringBuffer();
		Date mDate = date;
		StringBuffer dateString = formatter.format(mDate, stringBuffer,new FieldPosition(0));
		return dateString.toString();
	 }
}
