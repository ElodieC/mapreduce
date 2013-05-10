package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Classe qui récupère l'expression entrée dans le moteur de recherche 
 * l'analyse, supprime les mots non référencés,
 * lance des seekers qui vont récupérer les occurences dans les fichiers
 * @author Olivier Mickaël
 *
 */
public class Search {

	/**
	 * Liste des mots à chercher
	 */
	private List<String> toSeek;
	/**
	 * Expression entrée dans le moteur de recherche 
	 */
	private String expression; 
	/**
	 * Seeker qui va récupérer les informations sur les mots
	 * @see Seeker
	 */
	private Seeker seeker;

	/**
	 * Constructeur associé à l'objet Search qui opère la recherche
	 * @param expression entrée dans le moteur de recherche
	 */
	public Search(String expression) {
		this.toSeek = new ArrayList<String>();
		this.expression=expression;
		this.seeker = new Seeker();
	}

	/**
	 * Permet d'obtenir la liste de mots sur laquelle on opère la recherche
	 * @return la liste de mots sur laquelle opérer la recherche
	 */
	public List<String> getToSeek() {
		return toSeek;
	}

	/**
	 * Permet de modifier la liste de mots à chercher 
	 * @param toSeek liste des mots à chercher
	 */
	private void setToSeek(List<String> toSeek) {
		this.toSeek = supprNonIndexe(toSeek);
	}
	/**
	 * @return Seeker
	 */
	public Seeker getSeeker(){
		return this.seeker;
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
				"une","a","à ","ni","que","si","y","m","mon","ma","mes","me","ne",
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
	 * @see #setToSeek(List)
	 * @see Seeker
	 * @see FoundInfos
	 */
	public void toDo(){
		String[] arguments = expression.split(" ");
		List<String> motsAChercher = new ArrayList<String>(Arrays.asList(arguments));

		this.setToSeek(motsAChercher);

		for(int i =0; i<this.toSeek.size(); i++){
			System.out.println(toSeek.get(i));
			
			if(this.toSeek.get(i).equals("AND")){
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
		//Supprime les fichiers non pertinents
		List<FoundInfos> newInfo = new ArrayList<FoundInfos>();
		for(FoundInfos element : this.seeker.getInfo()){
			for(FoundInfos toEliminate : this.seeker.getIntox()){
				for(String fichierRebut : toEliminate.getFichiers()){
					System.out.println("FICHIER REBUT");
					System.out.println(fichierRebut);
					System.out.println("FICHIERS DE BASE");
					System.out.println(element.getFichiers());
					element.getFichiers().remove(fichierRebut);
					System.out.println("FICHIERS APRES REBUT");
					System.out.println(element.getFichiers());
				}
			}
			newInfo.add(element);
		}
		this.seeker.setInfo(newInfo);
		System.out.println(this.seeker.getInfo());
		//Recuperation des resultats en vue de l'affichage
		List<FoundInfos> aLire = Collections.synchronizedList(this.seeker.getInfo());
		for(FoundInfos element : aLire){
			this.seeker.addMessage("Mot "+element.getMot()+" trouvé<br>");
			this.seeker.addMessage(this.seeker.getLinesText(element.getMot(), element.getFichiers()));
		}
	}
}
