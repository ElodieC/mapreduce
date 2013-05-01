package search;

import index.IndexBuilder;
import index.Informations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import reader.FileRead;
/**
 * Recherche dans la hashmap pour un mot donne
 * @author 
 *
 */
public class Seeker {
	/**
	 * Resultat rendu a la fin a afficher ensuite
	 */
	private String message;
	
	private FileRead fichierALire;
	
	public Seeker() {
		super();
		this.message = "<html>";		
	}
	
	public void seek(String word){
		if(isPresent(word)) {
			System.out.println("Mot trouvé !");
			message+="Mot "+word+" trouvé<br>";
			message+=this.getLinesText(word);
		}
		else {
			System.out.println("Mot non trouvé");
			message+="Mot "+word+" non trouvé<br>";
		}
		this.message+="<br>";
	}
	
	public String getMessage(){
		return this.message+"</html>";
	}
	
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
	 * 
	 * @param word
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
	 * @return
	 */
	private String getLinesText(String word){
		String lineText="";
		Map<String,List<Long>> result  =this.getResult(word);
		for(Entry<String,List<Long>> occurence : result.entrySet()){
			this.fichierALire = new FileRead(occurence.getKey(), occurence.getValue(), word);
			lineText+=this.fichierALire.getLinesText();
		}
		lineText+="<br>";
		return lineText;
	}
}
