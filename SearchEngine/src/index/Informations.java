package index;

import java.util.ArrayList;

/**
 * Classe qui mémorise les Informations liées à un mot, à savoir le fichier
 * dans lequel il se trouve et les lignes où il a été repéré dans ce fichier
 * @author Olivier Mickael
 */
public class Informations {
	/**
	 * Liste des numeros de ligne dans lequel se trouve un mot donné
	 */
	private ArrayList<Long> lines;
	/**
	 * Nom du fichier dans lequel se trouve un mot donné
	 */
	private String file;
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructeur
	 * @param lines ArrayList<Long> liste des numeros de ligne dans lequel se trouve un mot donné
	 * @param file String nom du fichier dans lequel se trouve un mot donné
	 */
	public Informations(ArrayList<Long> lines, String file) {
		this.lines = (ArrayList<Long>) lines.clone();
		this.file = file;
	}
	/**
	 * @return lines ArrayList<Long> la liste des numéros de ligne 
	 */
	public ArrayList<Long> getLines() {
		return lines;
	}
	/**
	 * 
	 * @param lines ArrayList<Long> liste des numeros de ligne
	 */
	public void setLines(ArrayList<Long> lines) {
		this.lines = lines;
	}
	/**
	 * 
	 * @return file String le nom de fichier
	 */
	public String getFile() {
		return file;
	}
	/**
	 * Attribue le nom de fichier
	 * @param file String nom du fichier
	 */
	public void setFile(String file) {
		this.file = file;
	}
}
