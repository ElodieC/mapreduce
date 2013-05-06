package search;

import index.IndexBuilder;

import java.util.*;

public class Search {
	//Cette classe lance des seekers qui vont chacun chercher un mot dans le fichier texte et en font le bilan
	
	//Liste de mots 
	private List<String> toSeek;
	public static IndexBuilder builder;
	//Expression entree dans le champ recherche
	public String expression; 
	public Seeker seeker;
	public List<String> result;
	public List<Long> lineResults;
	
	/**
	 * Constructeur associe a l'objet Search qui opère la recherche
	 * @param expression entree dans le moteur de recherche
	 */
	public Search(String expression) {
		this.toSeek = new ArrayList<String>();
		this.expression=expression;
		this.seeker = new Seeker();
		this.result = new ArrayList<String>();
		lineResults = new ArrayList<Long>();
	}

	/**
	 * Permet d'obtenir la liste de mots sur laquelle on opere la recherche depuis l'exterieur
	 * @return la liste de mots sur laquelle operer la recherche
	 */
	public List<String> getToSeek() {
		return toSeek;
	}

	/**
	 * Permet de modifier la liste de mots a chercher depuis l'exterieur
	 * @param toSeek
	 */
	public void setToSeek(List<String> toSeek) {
		RelevanceWord sort = new RelevanceWord(supprNonIndexe(toSeek));
		this.toSeek = sort.getMotsSorted();
	}
	
	/**
	 * Supprime les mots de la liste des mots entres s'ils ne sont pas indexes
	 * @param motsEntres
	 * @return la liste des mots entres sans les mots non indexes
	 */
	private List<String> supprNonIndexe(List<String> motsEntres){
		List<String> supprNonIndexe = new ArrayList <String>(); 
		List<String> nonIndexe = new ArrayList<String>();
		String[] tab = {"et", "ou", "oÃ¹", "de", "des", "d", "le", "les","l","la","je","il","au","aux","du","un",
				"une","a","Ã Â ","or","ni","que","si","y","m","mon","ma","mes","me","ne",
				"nous","on","sa","ses","se","qui","s","t","ta","tes","te","il","lÃ Â ","qu","sans","sur"};
		nonIndexe=Arrays.asList(tab);
		for (String mot : motsEntres){
			if (!nonIndexe.contains(mot)){
				supprNonIndexe.add(mot);
			}
		}
		return supprNonIndexe;
	}
	
	/**
	 * Fonction qui permet d'analyser l'expression passee, de lancer le seeker mot par mot en tenant
	 * compte des predicats, et d'ecrire le resultat
	 */
	public void toDo(){
		String[] arguments = expression.split(" ");
		List<String> motsAChercher = new ArrayList<String>(Arrays.asList(arguments));
		
		this.setToSeek(motsAChercher);
		
		for(int i =0; i<arguments.length; i++){
			switch(arguments[i])
			{
				case "AND": 
					if(i+2<arguments.length){
						this.seeker.seekAnd(arguments[i+1], arguments[i+2]);
						i=i+2;
					}
					else{
						this.seeker.predicatInvalid("AND");
					}
				break;
				
				case "OR":
					if(i+2<arguments.length){
						this.seeker.seekOr(arguments[i+1], arguments[i+2]);
						i=i+2;
					}
					else{
						this.seeker.predicatInvalid("OR");
					}
				break;
				
				case "NOT":
					if(i+1<arguments.length){
						this.seeker.seekNot(arguments[i+1]);
						i=i+1;
					}
					else{
						this.seeker.predicatInvalid("NOT");
					}
				break;
				
				default: 
					this.seeker.seek(arguments[i]);
			}
		}
		
		List<FoundInfos> newInfo = new ArrayList<FoundInfos>();
		for(FoundInfos element : this.seeker.getInfo()){
			for(FoundInfos toEliminate : this.seeker.getIntox()){
				for(String fichierRebut : toEliminate.fichiers){
					System.out.println("FICHIER REBUT");
					System.out.println(fichierRebut);
					System.out.println("FICHIERS DE BASE");
					System.out.println(element.fichiers);
					element.fichiers.remove(fichierRebut);
					System.out.println("FICHIERS APRES REBUT");
					System.out.println(element.fichiers);
				}
			}
			newInfo.add(element);
		}
		this.seeker.setInfo(newInfo);
		
		List<FoundInfos> aLire = Collections.synchronizedList(this.seeker.getInfo());
		for(FoundInfos element : aLire){
			this.seeker.addMessage("Mot "+element.mot+" trouvé<br>");
			this.seeker.addMessage(this.seeker.getLinesText(element.mot, element.fichiers));
		}
	}
}
