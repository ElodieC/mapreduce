package search;

import java.util.List;
/**
 * Classe qui associe à un mot la liste de ses fichiers
 * @author Olivier Mickaël
 *
 */
public class FoundInfos {
	/**
	 * mot mot auquel on va associer des fichiers
	 */
	private String mot;
	/**
	 * fichiers liste des fichiers où se trouvent le mot
	 */
	private List<String> fichiers;
	
	/**
	 * 
	 * @param mot
	 * @param fichiers liste des noms de fichiers dans lesquels se trouvent le mot
	 */
	public FoundInfos(String mot, List<String> fichiers) {
		this.mot = mot;
		this.fichiers=fichiers;
	}
	/**
	 * 
	 * @return le mot
	 */
	public String getMot(){
		return this.mot;
	}
	/**
	 * 
	 * @return la liste des noms de fichiers
	 */
	public List<String> getFichiers(){
		return this.fichiers;
	}
	
	public String toString(){
		return mot+" : "+fichiers;
	}
}
