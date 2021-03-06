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
 * Recherche dans la hashmap pour un mot donné
 * @author Olivier Mickaël
 *
 */
public class Seeker {
	/**
	 * Résultat rendu à la fin à afficher ensuite
	 */
	private StringBuilder message;
	/**
	 * Liste des informations sur la recherche
	 * @see FoundInfos
	 */
	private List<FoundInfos> info;
	/**
	 * Liste des fichiers à ignorer (prédicat NOT)
	 * @see FoundInfos
	 */
	private List<FoundInfos> intox;

	public Seeker() {
		this.message = new StringBuilder("<html>");	
		this.info = new ArrayList<FoundInfos>();
		this.intox = new ArrayList<FoundInfos>();
	}
	
	public void addMessage(Object obj) {
		message.append(obj);
	}

	/**
	 * Vérifie si le mot est présent dans un des fichiers txt
	 * si oui il récupère son contexte
	 * @param word seeked
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seek(String word){
		if(isPresent(word)) {
			System.out.println("Mot trouvé !");
			//message.append("Mot "+word+" trouvé<br>");
			Logger.addInLog("Mot "+word+" trouvé");
			info.add(new FoundInfos(word, this.getFichiers(word)));
			//message.append(this.getLinesText(word));
		}
		else {
			Logger.addInLog("Mot "+word+" non trouvé");
			System.out.println("Mot non trouvé");
			message.append("Mot "+word+" non trouvé<br>");
			this.message.append("<br>");
		}
	}
	
	/**
	 * Vérifie si les deux mots sont présents dans un même fichier txt
	 * si oui il récupère son contexte
	 * @param word1 String premier mot du prédicat AND
	 * @param word2 String deuxième mot du prédicat AND
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seekAnd(String word1, String word2){
		boolean andWorks = false;
		List<String> files = new ArrayList<String>();
		if(isPresent(word1)) {
			if(isPresent(word2)) {
				for(String word1fic : getFichiers(word1)){
					for(String word2fic : getFichiers(word2)){
						if(word1fic.equals(word2fic))
						{
							System.out.println("Mots trouvés !");
							Logger.addInLog("Mots "+word1+", "+word2+" trouvés");
							files.add(word1fic);
							andWorks = true;
						}
					}
				}
				if(andWorks)
				{
					info.add(new FoundInfos(word1, files));
					info.add(new FoundInfos(word2, files));
				}
			}
			else {
				Logger.addInLog("Mot "+word2+" non trouvé");
				System.out.println("Mot non trouvé");
				message.append("Mot "+word2+" non trouvé<br>");
			}
		}
		else {
			Logger.addInLog("Mot "+word1+" non trouvé");
			System.out.println("Mot non trouvé");
			message.append("Mot "+word1+" non trouvé<br>");
		}
		
		if(!andWorks)
			this.message.append("Mots "+word1+" et "+word2+" non trouvés dans le même fichier");
		
		else
			this.message.append("Mots "+word1+" et "+word2+" trouvés dans le même fichier");
		
		this.message.append("<br>");
	}
	
	/**
	 * Vérifie si les deux mots sont présents dans des fichiers différents
	 * si oui il récupère son contexte
	 * @param word1 String premier mot du prédicat OR
	 * @param word2 String deuxième mot du prédicat OR
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seekOr(String word1, String word2){
		boolean orWorks=false;
		List<String> filesWord1 = new ArrayList<String>();
		List<String> filesWord2 = new ArrayList<String>();
		if(isPresent(word1)) {
			if(isPresent(word2)) {
				for(String word1fic : getFichiers(word1)){
					for(String word2fic : getFichiers(word2)){
						if(!word1fic.equals(word2fic))
						{
							System.out.println("Mots trouvés !");
							Logger.addInLog("Mots "+word1+", "+word2+" trouvés");
							filesWord1.add(word1fic);
							filesWord2.add(word2fic);
							orWorks=true;
						}
					}
				}
				if(orWorks)
				{
					info.add(new FoundInfos(word1, filesWord1));
					info.add(new FoundInfos(word2, filesWord2));
				}
			}
			else {
				Logger.addInLog("Mot "+word2+" non trouvé");
				System.out.println("Mot non trouvé");
				message.append("Mot "+word2+" non trouvé<br>");
			}
		}
		else {
			Logger.addInLog("Mot "+word1+" non trouvé");
			System.out.println("Mot non trouvé");
			message.append("Mot "+word1+" non trouvé<br>");
		}
		if(!orWorks){
			this.message.append("Mots "+word1+" et "+word2+" non trouvés dans un fichier différent");
		}
		else{
			this.message.append("Mots "+word1+" et "+word2+" trouvés dans un fichier différent");
		}
		this.message.append("<br>");
	}
	
	/**
	 * Vérifie si le mot est présent dans un des fichiers txt
	 * si oui il élimine son contexte du message de retour si présent
	 * @param word mot dont les fichiers dans lequel il se trouve à éliminer
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seekNot(String word){
		if(isPresent(word)) {
			System.out.println("Mot trouvé !");
			//message.append("Mot "+word+" trouvé<br>");
			Logger.addInLog("Mot "+word+" trouvé");
			intox.add(new FoundInfos(word, this.getFichiers(word)));
			//message.append(this.getLinesText(word));
		}
		else {
			Logger.addInLog("Mot "+word+" non trouvé");
			System.out.println("Mot non trouvé");
			message.append("Mot "+word+" non trouvé<br>");
			this.message.append("<br>");
		}
	}
	/**
	 * 
	 * @return la liste des mots et des fichiers dans lequels ils se trouvent
	 */
	public List<FoundInfos> getInfo() {
		return info;
	}
	/**
	 * 
	 * @param info liste des mots et des fichiers dans lequels ils se trouvent
	 */
	public void setInfo(List<FoundInfos> info) {
		this.info = info;
	}
	/**
	 * 
	 * @return la liste des mots et des fichiers à ignorer pour le résultat (prédicat NOT)
	 */
	public List<FoundInfos> getIntox() {
		return intox;
	}

	/**
	 * Permet de donner des informations sur le formalisme du prédicat utilisé en cas d'echec
	 * @param pred le prédicat qui est invalide
	 */
	public void predicatInvalid(String pred){
		System.out.println("Prédicat "+pred+" invalide !");
		message.append("Prédicat "+pred+" invalide !");
		this.message.append("<br>");
		if (pred.equals("AND")){
			message.append("Le formalisme est : AND mot1 mot2");
			this.message.append("<br>");
			this.message.append("<br>");
		}
		else if (pred.equals("OR")){
				message.append("Le formalisme est : OR mot1 mot2");
				this.message.append("<br>");
				this.message.append("<br>");
		}
		else if (pred.equals("NOT")){
			message.append("Le formalisme est : NOT mot");
			this.message.append("<br>");
			this.message.append("<br>");
		}
		Logger.addInLog("Prédicat "+pred+" invalide !");
	}

	/**
	 * Met fin au rendu du résultat final
	 * @return le message
	 */
	public StringBuilder getMessage(){
		return this.message.append("</html>");
	}

	/**
	 * Vérifie si le mot est présent dans la hashmap
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
	 * Rend une liste de fichiers qui est celle associee au mappage d'un mot
	 * @param word
	 * @return les fichiers dans lequel le mot est présent
	 */
	public List<String> getFichiers(String word){
		ArrayList<String> files = new ArrayList<String>();
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				files.add(IndexBuilder.getIndex().get(i).get(word).getFile());
			}
		}
		return files;
	}
	
	/**
	 * Rend le nombre d'occurences totales d'un mot dans l'index
	 * @param word
	 * @return le nombre d'occurences d'un mot dans tous les fichiers
	 */
	public int getNbOccurences(String word){
		int nbOccurences = 0;
		Map<String,List<Long>> ocurrencesPerFile = getResult(word);
		for (Entry<String,List<Long>> occurence : ocurrencesPerFile.entrySet()){
			nbOccurences+=occurence.getValue().size();
		}
		return nbOccurences;
	}
	
	/**
	 * Rend les numéros de lignes pour un mot
	 * c'est-à-dire une Map dans laquelle on a pour clé le fichier dans lequel 
	 * le mot se trouve et pour valeur la liste du numéro des lignes
	 * @param word
	 * @return table de hachage dans laquelle on a pour clé le fichier dans lequel 
	 * le mot se trouve et pour valeur la liste du numéro des lignes
	 */
	private Map<String,List<Long>> getResult(String word){
		Map<String,List<Long>> result = new HashMap<String,List<Long>>();
		for (int i=1; i<=IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word)){
				Informations info = IndexBuilder.getIndex().get(i).get(word);
				result.put(info.getFile(), info.getLines());
			}
		}
		return result;
	}
	
	
	/**
	 * @param word String le mot à chercher
	 * @param sortedFileName la liste des fichiers dans lequel se trouve le mot
	 * @return StringBuilder texte des lignes pour un mot dans les fichiers dans lequel il se trouve
	 */
	public StringBuilder getLinesText(String word, List<String> sortedFileName){
		StringBuilder lineText=new StringBuilder();
		Map<String,List<Long>> result  =this.getResult(word);

		for (String nomFichier:sortedFileName){
			FileRead fichierALire = new FileRead(nomFichier, result.get(nomFichier), word);
			Long startTime = new Date().getTime();
			lineText.append(fichierALire.getLinesText());
			Long endTime = new Date().getTime();
			System.out.println("temps : "+(endTime-startTime)+" ms");
		}
		lineText.append("<br>");
		return lineText;
	}
}
