package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe s'occupe de construire l'index avec la création d'une hashmap 
 * @author Olivier Mickaël
 *
 */
public class IndexBuilder {
	/**
	 * index : Hashmap qui contiendra toutes les informations venant de l'indexation.
	 * Sa clé est un identifiant unique,
	 * les valeurs sont une HashMap ayant pour clé le mot et pour informations le nomdufichier et la liste des offsets
	 * @see Informations
	 */
	private static Map<Integer, HashMap<String, Informations > > index;
	/**
	 * currentWord mot courant mémorisé pour à la fin de la lecture de la ligne de l'index lui associer
	 * ses numéros de ligne
	 */
	private String currentWord;
	/**
	 * currentFile fichier courant dans lequel se trouve le mot courant
	 */
	private String currentFile;
	/**
	 * lines liste des numéros de ligne pour un mot dans un fichier
	 */
	private ArrayList<Long> lines;
	/**
	 * currentID identifiant unique pour chaque combinaison (mot,nomFichier)
	 */
	private Integer currentID;
	
	public IndexBuilder() throws IOException{
		index = new HashMap<Integer,HashMap<String, Informations > >();
		lines = new ArrayList<Long>();
		currentID = 0;
	}
	/**
	 * Ajoute le numéro de ligne lu à la liste courante des numéros de ligne
	 * @param entierLu le numéro de ligne lu dans le fichier construit par Hadoop
	 */
	public void addLine(long entierLu) {
		lines.add(entierLu);
	}
	/**
	 * Mémorise le nom de fichier lu afin de pouvoir l'utiliser à la fin de la lecture des informations concernant le mot
	 * @param ident identifiant lu dans le fichier construit par Hadoop
	 */
	public void addFile(String ident) {
		currentFile = ident;
	}
	/**
	 * Mémorise le mot lu afin de pouvoir l'utiliser à la fin de la lecture des informations concernant le mot
	 * Incrémente l'identifiant courant unique
	 * @param ident identifiant lu dans le fichier construit par Hadoop
	 */
	public void addWord(String ident) {
		currentID++;
		currentWord = ident;
	}
	/**
	 * Ajoute à la hashmap les informations pour un mot donné et un fichier donné
	 * à effectuer à chaque fin de ligne du fichier construit par Hadoop
	 */
	public void buildSet(){
			HashMap<String, Informations> mapped = new HashMap<String, Informations >();
			mapped.put(currentWord, new Informations(lines, currentFile));
			getIndex().put(currentID, mapped);
			lines.clear();
	}
	/**
	 * @return index construit à partir du fichier construit par Hadoop
	 */
	public static Map<Integer, HashMap<String, Informations > >  getIndex() {
		return index;
	}
}
