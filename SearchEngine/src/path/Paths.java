package path;
/**
 * Classe générale dans laquelle on doit mettre les différents chemins utilisés par le moteur de recherche
 * @author Corbel Elodie
 *
 */
public class Paths {
	private static String chemindeClarisseOutput = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/outputFiles/output";
	private static String cheminElodieOutput = "/home/hduser/workspace/mapreduce/hadoopMR/outputFiles/output";
	private static String cheminMickaelOutput = "C:/Users/Olivier Catherine/workspace/hadoopMR/outputFiles/output";
	/**
	 * outputIndexLocation endroit où se trouve le fichier output.txt sortant de l'index
	 */
	public static final String outputIndexLocation = cheminElodieOutput;
	
	private static String cheminElodieSplit = "/home/hduser/hadoopMR/inputFilesSplit/";
	private static String chemindeClarisseSplit = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/inputFilesSplit/";
	private static String cheminMickaelSplit = "C:/Users/Olivier Catherine/workspace/hadoopMR/inputFilesSplit/";
	/**
	 * inputFilesSplitDir dossier où se trouvent les fichiers découpés par le script découpant les fichiers 
	 * toutes les 100 lignes
	 */
	public static final String inputFilesSplitDir = cheminElodieSplit;
	
	private static String pathClarisseLog = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/" +
			"mapreduce/hadoopMR/";
	private static String pathElodieLog = "/home/hduser/hadoopMR/";
	private static String pathMickaelLog = "C:/Users/Olivier Catherine/workspace/hadoopMR/";
	/**
	 * logFilePath dossier où on enregistre le log du programme
	 */
	public static final String logFilePath = pathElodieLog;
	
	
	
}
