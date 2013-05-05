package search;

import index.IndexBuilder;
import index.Informations;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import reader.FileRead;
import window.Logger;
/**
 * Recherche dans la hashmap pour un mot donne
 * @author Olivier Mickaël
 *
 */
public class Seeker {
	/**
	 * Resultat rendu a la fin a afficher ensuite
	 */
	private StringBuilder message;
	
	private FileRead fichierALire;
	
	public Seeker() {
		super();
		this.message = new StringBuilder("<html>");		
	}
	
	/**
	 * Vérifie si le mot est présent dans un des fichiers txt
	 * si oui il reccupere son contexte
	 * @param word seeked
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seek(String word){
		if(isPresent(word)) {
			System.out.println("Mot trouvé !");
			message.append("Mot "+word+" trouvé<br>");
			Logger.addInLog("Mot "+word+" trouvé");
			message.append(this.getLinesText(word));
		}
		else {
			Logger.addInLog("Mot "+word+" non trouvé");
			System.out.println("Mot non trouvé");
			message.append("Mot "+word+" non trouvé<br>");
		}
		this.message.append("<br>");
	}
	
	/**
	 * Met fin au rendu du resultat final
	 * @return le message
	 */
	public StringBuilder getMessage(){
		return this.message.append("</html>");
	}
	
	/**
	 * Verifie si le mot est présent dans la hashmap
	 * @param word
	 * @return true si présent false sinon
	 */
	public boolean isPresent(String word){
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Obtient l'occurence du mot dans les fichiers donnes
	 * @param word seeked
	 * @return Le nombre de fichiers dans lequel se trouve word
	 */
	public int getFilesOccurences(String word){
		int num = 0;
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				num++;
			}
		}
		return num;
	}
	
	public List<Long> getLines(String word){
		ArrayList<Long> lines = new ArrayList<Long>();
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				lines.addAll(IndexBuilder.getIndex().get(i).get(word).getOffsets());
			}
		}
		return lines;
	}
	
	public List<String> getFichiers(String word){
		ArrayList<String> files = new ArrayList<String>();
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				files.add(IndexBuilder.getIndex().get(i).get(word).getfile());
			}
		}
		return files;
	}
	/**
	 * Rend les numeros de lignes pour un mot
	 * c'est a dire une Map dans laquelle on a pour cle le fichier dans lequel 
	 * le mot se trouve et pour valeur la liste du numero des lignes
	 * @param word
	 * @return
	 */
	private Map<String,List<Long>> getResult(String word){
		Map<String,List<Long>> result = new HashMap<String,List<Long>>();
		for (int i=1; i<=IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word)){
				Informations info = IndexBuilder.getIndex().get(i).get(word);
				result.put(info.getfile(), info.getOffsets());
			}
		}
		return result;
	}
	/**
	 * Rend le texte des lignes pour un mot dans les fichiers dans lequel il se trouve
	 * @param word
	 * @return le texte
	 */
	private StringBuilder getLinesText(String word){
		StringBuilder lineText=new StringBuilder();
		Map<String,List<Long>> result  =this.getResult(word);
		for(Entry<String,List<Long>> occurence : result.entrySet()){
			this.fichierALire = new FileRead(occurence.getKey(), occurence.getValue(), word);
			Long startTime = new Date().getTime();
			lineText.append(this.fichierALire.getLinesText());
			Long endTime = new Date().getTime();
			System.out.println("temps : "+(endTime-startTime)+" ms");
			Logger.addInLog("temps : "+(endTime-startTime)+" ms");
		}
		lineText.append("<br>");
		return lineText;
	}
}
