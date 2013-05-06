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
	
	private List<FoundInfos> info;
	
	private List<FoundInfos> intox;

	public Seeker() {
		super();
		this.message = new StringBuilder("<html>");	
		this.info = new ArrayList<FoundInfos>();
		this.intox = new ArrayList<FoundInfos>();
	}
	
	public void addMessage(Object obj) {
		message.append(obj);
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
	 * Vérifie si les deux mot sont présents dans un des fichiers txt
	 * si oui il reccupere son contexte
	 * @param word seeked
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seekAnd(String word1, String word2){
		boolean andWorks = false;
		if(isPresent(word1)) {
			if(isPresent(word2)) {
				for(String word1fic : getFichiers(word1)){
					for(String word2fic : getFichiers(word2)){
						if(word1fic.equals(word2fic))
						{
							System.out.println("Mots trouvés !");
							//message.append("Mots "+word1+", "+word2+" trouvés<br>");
							Logger.addInLog("Mots "+word1+", "+word2+" trouvés");
							//message.append(this.getLinesText(word1));
							//message.append(this.getLinesText(word2));
							info.add(new FoundInfos(word1, this.getFichiers(word1)));
							info.add(new FoundInfos(word2, this.getFichiers(word2)));//ne fonctionne pas
							
							andWorks = true;
						}
					}
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
	 * Vérifie si les deux mot sont présents dans un des fichiers txt
	 * si oui il reccupere son contexte
	 * @param word seeked
	 * @see #getLinesText(String)
	 * @see #isPresent(String)
	 */
	public void seekOr(String word1, String word2){
		boolean orWorks=false;
		if(isPresent(word1)) {
			if(isPresent(word2)) {
				for(String word1fic : getFichiers(word1)){
					for(String word2fic : getFichiers(word2)){
						if(!word1fic.equals(word2fic))
						{
							System.out.println("Mots trouvés !");
							//message.append("Mots "+word1+", "+word2+" trouvés<br>");
							Logger.addInLog("Mots "+word1+", "+word2+" trouvés");
							//message.append(this.getLinesText(word1));
							//message.append(this.getLinesText(word2));
							info.add(new FoundInfos(word1, this.getFichiers(word1)));
							info.add(new FoundInfos(word2, this.getFichiers(word2)));//pareil que seekAnd
							orWorks=true;
						}
					}
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
	 * si oui il elimine son contexte du message de retour si présent
	 * @param word seeked
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
	
	public List<FoundInfos> getInfo() {
		return info;
	}

	public void setInfo(List<FoundInfos> info) {
		this.info = info;
	}

	public List<FoundInfos> getIntox() {
		return intox;
	}

	/**
	 * Permet de donner des informations sur le formalisme du predicat utilisé en cas d'echec
	 * @param pred
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

	/**
	 * Donne les numeros de ligne du mot passe en parametre
	 * @param word
	 * @return Une liste des numeros de lignes où l'on trouve le mot
	 */
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
				files.add(IndexBuilder.getIndex().get(i).get(word).getfile());
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
	public StringBuilder getLinesText(String word, List<String> sortedFileName){
		StringBuilder lineText=new StringBuilder();
		Map<String,List<Long>> result  =this.getResult(word);

		//SortFiles sortFiles = new SortFiles(result);
		//String[] sortedFileName = sortFiles.getSortedFileNames();

		for (String nomFichier:sortedFileName){
			this.fichierALire = new FileRead(nomFichier, result.get(nomFichier), word);
			Long startTime = new Date().getTime();
			lineText.append(this.fichierALire.getLinesText());
			Long endTime = new Date().getTime();
			System.out.println("temps : "+(endTime-startTime)+" ms");
		}
		lineText.append("<br>");
		return lineText;
	}
}
