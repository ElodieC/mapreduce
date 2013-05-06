package search;

import index.IndexBuilder;

import java.util.*;
/**
 * Classe qui récupère l'expression entrée dans le moteur de recherche 
 * l'analyse, supprime les mots non référencés
 * lance des seekers qui vont récupérer les occurences dans les fichiers
 * @author Olivier Mickaël
 *
 */
public class Search {

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
		//RelevanceWord sort = new RelevanceWord(supprNonIndexe(toSeek)); je pense que l'on ne va pas garder ca - Elodie
		this.toSeek = supprNonIndexe(toSeek);
	}

	/**
	 * Supprime les mots de la liste des mots entrés s'ils ne sont pas indexés
	 * @param motsEntres
	 * @return la liste des mots entrés sans les mots non indexés
	 */
	private List<String> supprNonIndexe(List<String> motsEntres){
		List<String> supprNonIndexe = new ArrayList <String>(); 
		List<String> nonIndexe = new ArrayList<String>();
		String[] tab = {"et", "ou", "où", "de", "des", "d", "le", "les","l","la","je","il","au","aux","du","un",
				"une","a","à ","or","ni","que","si","y","m","mon","ma","mes","me","ne",
				"nous","on","sa","ses","se","qui","s","t","ta","tes","te","il","là ","qu","sans","sur"};
		nonIndexe=Arrays.asList(tab);
		for (String mot : motsEntres){
			if (!nonIndexe.contains(mot.toLowerCase())){
				supprNonIndexe.add(mot);
			}
		}
		return supprNonIndexe;
	}

	/**
	 * Fonction qui permet d'analyser l'expression passée, de lancer le seeker mot par mot en tenant
	 * compte des prédicats, et d'écrire le resultat
	 */
	public void toDo(){
		String[] arguments = expression.split(" ");
		List<String> motsAChercher = new ArrayList<String>(Arrays.asList(arguments));

		this.setToSeek(motsAChercher);

		for(int i =0; i<this.toSeek.size(); i++){
			if(this.toSeek.get(i).equals("AND")){//desolee pour le switch je n'ai pas java 7
				if(i+2<this.toSeek.size()){
					this.seeker.seekAnd(this.toSeek.get(i+1).toLowerCase(), this.toSeek.get(i+2).toLowerCase());
					i=i+2;
				}
				else
					this.seeker.predicatInvalid("AND");
				
			}
			else if(this.toSeek.get(i).equals("OR")){
				if(i+2<this.toSeek.size()){
					this.seeker.seekOr(this.toSeek.get(i+1).toLowerCase(), this.toSeek.get(i+2).toLowerCase());
					i=i+2;
				}
				else
					this.seeker.predicatInvalid("OR");
				
			}
			else if(this.toSeek.get(i).equals("NOT")){
				if(i+1<this.toSeek.size()){
					this.seeker.seekNot(this.toSeek.get(i+1).toLowerCase());
					i=i+1;
				}
				else
					this.seeker.predicatInvalid("NOT");
					
			}
			else 
				this.seeker.seek(this.toSeek.get(i).toLowerCase());
			

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
		System.out.println(this.seeker.getInfo());
		List<FoundInfos> aLire = Collections.synchronizedList(this.seeker.getInfo());
		for(FoundInfos element : aLire){
			this.seeker.addMessage("Mot "+element.mot+" trouvé<br>");
			this.seeker.addMessage(this.seeker.getLinesText(element.mot, element.fichiers));
		}
	}
}
