package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe qui recupere les lignes du fichier pour un mot donne
 * @author hduser
 *
 */
public class FileRead {
	/**
	 * Repertoire ou se trouvent les fichiers splitter
	 */
	private final String fileInputDir = "/home/hduser/hadoopMR/inputFilesSplit/";
	/**
	 * Pour parcourir plus vite les fichiers on a decide de les couper toutes les 100 lignes
	 * avec un script bash
	 * Nbre de lignes par fichier
	 */
	private final int nbLinesPerFile = 100;
	/**
	 * Nom du fichier a lire
	 */
	private String fileName;
	/**
	 * Lignes a afficher
	 */
	private List<Long> lines;

	private String wordToSearch;

	public FileRead(String file, List<Long> lines, String word){
		this.fileName = file;
		this.lines = lines;
		this.wordToSearch = word;
	}
	/**
	 * @return the context of the lines where is the word in the file
	 */
	public String getLinesText(){
		String lines="Fichier : "+this.fileName+"<br>";
		for (Long line : this.lines){
			lines+="Ligne "+line+": <br>";
			lines+=getContextLine(line);
		}

		return lines;
	}
	/**
	 * @param line number
	 * @return the context for the given line = 3 lignes autour
	 */
	private String getContextLine(Long line) {
		String lines="";
		try {
			Scanner file =  new Scanner(new File (this.getFilePart(line)));
			int i=0;
			int lineInFilePart = (int) (line % nbLinesPerFile);//numero de la ligne a l'interieur du fichier splitte
			if (lineInFilePart == 1){
				// si on est a la premiere ligne cas particulier
				while(file.hasNextLine()&& i<lineInFilePart+2){
					if (i==0)
						lines += formatStringStrong(file.nextLine())+"<br>";
					else 
						lines += file.nextLine()+"<br>";
					i++;
				}
				file.close();
			}
			else if (lineInFilePart == 0){
				//si on est a la derniere ligne cas particulier
				while(file.hasNextLine() && i<nbLinesPerFile){
					String currentLine = file.nextLine();
					i++;
					if (i == nbLinesPerFile - 2 || i == nbLinesPerFile-1 ){
						lines+=currentLine+"<br>";
					}
					else if (i==nbLinesPerFile)
						lines+=formatStringStrong(currentLine)+"<br>";
				}
				file.close();
			}
			else {
				while(file.hasNextLine() && i<lineInFilePart+1){
					String currentLine = file.nextLine();

					if (i == lineInFilePart-2 || i == lineInFilePart)
						lines+=currentLine+"<br>";
					else if (i == lineInFilePart - 1)
						lines+=formatStringStrong(currentLine)+"<br>";
					i++;
				}
				file.close();
			}

		} catch (FileNotFoundException e) {
			lines="Fichier Non Trouve Mauvais decoupage du fichier d'entree <br>";
		}
		return lines;
	}

	/**
	 * 
	 * @param line number
	 * @return the path of the file part where is the word
	 */
	private String getFilePart(Long line){
		String fileNamePart=fileInputDir+this.fileName+".txt-";
		int numPart = (int) ((line -1 )/nbLinesPerFile);
		//cas particulier par rapport au nom du fichier = split avec 2 digits
		if (numPart>=0 && numPart <10)
			fileNamePart+="0"+numPart;
		else 
			fileNamePart+=numPart;
		return fileNamePart;
	}
	/**
	 * 
	 * @param line text where is the word
	 * @return the line text with the word that is seeked in strong
	 */
	private String formatStringStrong(String line){
		String result="";
		//We must find the place of the searched word to replace it in the JTextPane with 
		//the good case (in the example apache is in fact Apache)
		int offset = line.toLowerCase().indexOf(this.wordToSearch);
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
		//insert the word with right case in bold
		/*resultat.setText("<html>"+split[0]+"<strong>"+word+
				"</strong>"+split[1]+"</html>");*/
		return result;
	}

}