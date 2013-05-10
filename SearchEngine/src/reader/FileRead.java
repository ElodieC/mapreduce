package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import path.Paths;

import window.Logger;

/**
 * Classe qui récupère les lignes du fichier pour un mot donné
 * @author Corbel Elodie, Renou Clarisse
 *
 */
public class FileRead {
	/**
	 * fileInputdir Répertoire où se trouvent les fichiers découpés
	 */
	private final String fileInputDir = Paths.inputFilesSplitDir;
	/**
	 * Pour parcourir plus vite les fichiers on a décidé de les couper toutes les 100 lignes
	 * avec un script bash
	 * nbLinesPerFile Nombre de lignes par fichier
	 */
	private final int nbLinesPerFile = 100;
	/**
	 * fileName Nom du fichier à lire
	 */
	private String fileName;
	/**
	 * lines lignes à afficher
	 */
	private List<Long> lines;
	/**
	 * wordToSearch mot que l'on cherche
	 */
	private String wordToSearch;

	/**
	 * Récupère la liste des lignes et le nom du fichier pour le mot recherché
	 * @param file nom du fichier où sont faites les recherches
	 * @param lines liste des numéros de lignes où les mots sont trouvés
	 * @param word mot recherché
	 */
	public FileRead(String file, List<Long> lines, String word){
		this.fileName = file;
		this.lines = new SortLineNumbers(lines).getLinesSorted();
		this.wordToSearch = word;
		System.out.println("Fichier : "+file+" / Lignes : "+lines+" / Mot cherché : "+word);
		Logger.addInLog("Fichier : "+file+" / Lignes : "+lines+" / Mot cherché : "+word);
	}
	/**
	 * Trouve les lignes qui entourent le mot une fois trouvé.
	 * @return le contexte des lignes où se trouve le mot dans le fichier
	 * @see #getContextLine(Long)
	 */
	public StringBuilder getLinesText(){
		StringBuilder lines=new StringBuilder("Fichier : "+this.fileName+"<br>");
		for (Long line : this.lines){
			lines.append("Ligne "+line+": <br>");
			lines.append(getContextLine(line));
		}
		return lines;
	}
	/**
	 * On décide de prendre 3 lignes autours du Mot recherché
	 * @param line numéro de la ligne qui contient le mot
	 * @return le contexte pour la ligne donnée = 3 lignes autours
	 * @exception Attrape l'exception si les fichiers splites n'ont pas ete trouves ou
	 * s'il sont mal decoupes
	 */
	private StringBuilder getContextLine(Long line) {
		StringBuilder lines=new StringBuilder();
		try {
			BufferedReader file =  new BufferedReader(new FileReader (this.getFilePart(line)));
			int i=0;
			int lineInFilePart = (int) (line % nbLinesPerFile);//numero de la ligne a l'interieur du fichier splitte
			String currentLine;
			if (lineInFilePart == 1){
				// si on est a la premiere ligne cas particulier
				while( (currentLine=file.readLine()) != null && i<lineInFilePart+2){
					if (i==0){
						lines.append(formatStringStrong(currentLine));
						lines.append("<br>");
					}
					else {
						lines.append(currentLine);
						lines.append("<br>");
					}
					i++;
				}
				file.close();
			}
			else if (lineInFilePart == 0){
				//si on est a la derniere ligne cas particulier
				while((currentLine=file.readLine()) != null && i<nbLinesPerFile){
					i++;
					if (i == nbLinesPerFile - 2 || i == nbLinesPerFile-1 ){
						lines.append(currentLine);
						lines.append("<br>");
					}
					else if (i==nbLinesPerFile){
						lines.append(formatStringStrong(currentLine));
						lines.append("<br>");
					}
				}
				file.close();
			}
			else {
				while((currentLine=file.readLine()) != null && i<lineInFilePart+1){

					if (i == lineInFilePart-2 || i == lineInFilePart){
						lines.append(currentLine);
						lines.append("<br>");
					}
					else if (i == lineInFilePart - 1){
						lines.append(formatStringStrong(currentLine));
						lines.append("<br>");
					}
					i++;
				}
				file.close();
			}

		} catch (FileNotFoundException e) {
			lines.append("Fichier Non Trouve Mauvais decoupage du fichier d'entree <br>");
			Logger.addInLog("Fichier Non Trouve Mauvais decoupage du fichier d'entree");
		} catch (IOException e) {

			e.printStackTrace();
			Logger.addInLog(e.getMessage());
		}
		return lines;
	}

	/**
	 * Récupère le chemin où sont stockés les fichiers découpés
	 * @param line ligne qui contient le mot
	 * @return le chemin du fichier découpé
	 * @see #fileInputDir
	 */
	private String getFilePart(Long line){
		StringBuilder fileNamePart = new StringBuilder();
		fileNamePart.append(fileInputDir+this.fileName+"-");
		Logger.addInLog("Chemin des fichiers splité : "+fileInputDir);
		int numPart = (int) ((line -1 )/nbLinesPerFile);
		//cas particulier par rapport au nom du fichier = split avec 2 digits
		
		if (numPart>=0 && numPart <10)
			fileNamePart.append("00"+numPart);
		
		else if(numPart>=10 && numPart<100)
			fileNamePart.append("0"+numPart);
		
		else 
			fileNamePart.append(numPart);

		Logger.addInLog(fileNamePart.toString());
		return fileNamePart.toString();
	}
	/**
	 * Met en forme le résultat avant l'affichage : Mise en avant (gras) du mot recherché
	 * @param line texte de la ligne où se trouve le mot
	 * @return le texte de la ligne où se trouve le mot avec le mot en gras
	 */
	private String formatStringStrong(String line){
		String result="";
		//We must find the place of the seeked word to replace it in the JTextPane with 
		//the good case (in the example apache is in fact Apache)
		int offset = line.toLowerCase().indexOf(this.wordToSearch);
		Logger.addInLog("Offset de "+this.wordToSearch+" : "+offset);
		//We find the good written word word = Apache
		String wordWithCase = line.substring(offset, offset+wordToSearch.length());
		//we split the string test in 2 to put html for the searched word
		//that why we have to search the word apache with the right case,
		//else we have a beautiful exception
		String split[] = line.split("(?i)"+wordWithCase);
		if (split.length == 1){
			//si le mot est en fin de ligne
			result=split[0]+"<strong>"+wordWithCase+"</strong>";
		}
		else {
			for (int i=0; i<split.length;i++){
				if (i == split.length -1 && line.toLowerCase().lastIndexOf(this.wordToSearch)!=line.length()-this.wordToSearch.length())
					result+=split[i];
				else 
					result+=split[i]+"<strong>"+wordWithCase+"</strong>";

			}
		}
		return result;
	}

}