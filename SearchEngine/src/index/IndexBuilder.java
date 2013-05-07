package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe s'occupe de construire l'index avec la creation d'une hashmap 
 * @author Olivier Mickaël
 *
 */
public class IndexBuilder {
	/**
	 * index Map hashmap qui contiendra toutes les informations venant de l'indexation
	 * sa cle est un identifiant unique
	 * les valeurs sont une HashMap ayant pour cle le mot et pour informations le nomdufichier et la liste des offsets
	 * @see Informations
	 */
	private static Map<Integer, HashMap<String, Informations > > index;
	/**
	 * currentWord String est le mot courant memorisé pour a la fin de la lecture de la ligne de l'index lui associer
	 * ses numeros de ligne
	 */
	private String currentWord;
	/**
	 * currentFile String est le fichier courant dans lequel se trouve le mot courant
	 */
	private String currentFile;
	/**
	 * lines ArrayList<Long> liste des numeros de ligne pour un mot dans un fichier
	 */
	private ArrayList<Long> lines;
	/**
	 * currentID Integer Identifiant unique pour chaque combinaison (mot,nomFichier)
	 */
	private Integer currentID;
	
	public IndexBuilder() throws IOException{
		index = new HashMap<Integer,HashMap<String, Informations > >();
		lines = new ArrayList<Long>();
		currentID = 0;
	}
	/**
	 * Ajoute le numero de ligne lu a la liste courant des numeros de ligne
	 * @param entierLu le numero de ligne lu dans le fichier construit par hadoop
	 */
	public void addLine(long entierLu) {
		lines.add(entierLu);
	}
	/**
	 * Memorise le nom de fichier lu afin de pouvoir l'utiliser à la fin de la lecture des informations concernant le mot
	 * @param ident String l'identifiant lu dans le fichier construit par hadoop
	 */
	public void addFile(String ident) {
		currentFile = ident;
	}
	/**
	 * Mémorise le mot lu afin de pouvoir l'utiliser à la fin de la lecture des informatinos concernant le mot
	 * incrémente l'identifiant courant unique
	 * @param ident String l'identifiant lu dans le fichier construit par hadoop
	 */
	public void addWord(String ident) {
		currentID++;
		currentWord = ident;
	}
	/**
	 * Ajoute à la hashmap les informations pour un mot donné et un fichier donné
	 * à effectuer à chaque fin de ligne du fichier construit par hadoop
	 */
	public void buildSet(){
			HashMap<String, Informations> mapped = new HashMap<String, Informations >();
			mapped.put(currentWord, new Informations(lines, currentFile));
			getIndex().put(currentID, mapped);
			lines.clear();
	}
	/**
	 * @return index Map<Integer, HashMap<String, Informations > > l'index construit à partir du fichier construit par hadoop
	 */
	public static Map<Integer, HashMap<String, Informations > >  getIndex() {
		return index;
	}
}
