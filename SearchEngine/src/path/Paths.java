package path;

public interface Paths {
	String chemindeClarisseOutput = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/outputFiles/output";
	String cheminElodieOutput = "/home/hduser/hadoopMR/outputFiles/output";
	String cheminMickaelOutput = "C:/Users/Olivier Catherine/workspace/hadoopMR/outputFiles/output";
	/**
	 * outputIndexLocation endroit où se trouve le fichier output.txt sortant de l'index
	 */
	static final String outputIndexLocation = cheminElodieOutput;
	
	String cheminElodieSplit = "/home/hduser/hadoopMR/inputFilesSplit/";
	String chemindeClarisseSplit = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/inputFilesSplit/";
	String cheminMickaelSplit = "C:/Users/Olivier Catherine/workspace/hadoopMR/inputFilesSplit/";
	/**
	 * inputFilesSplitDir dossier où se trouvent les fichiers découpés par le script découpant les fichiers 
	 * toutes les 100 lignes
	 */
	static final String inputFilesSplitDir = cheminElodieSplit;
	
	String pathClarisseLog = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/" +
			"mapreduce/SearchEngine/src/window/";
	String pathElodieLog = "/home/hduser/hadoopMR/";
	String pathMickaelLog = "C:/Users/Olivier Catherine/workspace/hadoopMR/";
	/**
	 * logFilePath dossier où on enregistre le log du programme
	 */
	static final String logFilePath = pathElodieLog;
	
	
	
}
