package search;

import java.util.List;
/**
 * Tri les mots en entree
 *
 */
public class RelevanceWord {

	/**
	 * Mots entres dans le moteur de recherche
	 */
	private List<String> motsEntres;

	public RelevanceWord(List<String> motsEntres){
		this.motsEntres = motsEntres;
	}
	/**
	 * @return la liste des mots en entrees en ordre decroissant par rapport 
	 * au nombre total d'occurences dans tous les fichiers 
	 */
	public List<String> getMotsSorted(){
		if (this.motsEntres.size() !=0 && this.motsEntres!=null){
			quicksort(0,this.motsEntres.size()-1);
		}
		return this.motsEntres;
	}
	/**
	 * Tri des mots en entree base sur l'algorithme du tri rapide 
	 * @param low indice inferieur non trie
	 * @param high indice superieur non trie
	 */
	private void quicksort(int low, int high) {
		int i = low, j = high;
		Seeker seeker=new Seeker();
		int pivot = seeker.getNbOccurences(this.motsEntres.get(low + (high-low)/2));

		while (i <= j) {
			while (seeker.getNbOccurences(this.motsEntres.get(i)) > pivot) {
				i++;
			}
			while (seeker.getNbOccurences(this.motsEntres.get(j)) < pivot) {
				j--;
			}

			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}
	/**
	 * Permute 2 elements de la liste des mots en entree
	 * @param i indice du premier element
	 * @param j indice du second element
	 */
	private void exchange(int i, int j) {
		String temp = this.motsEntres.get(i);
		this.motsEntres.set(i, this.motsEntres.get(j));
		this.motsEntres.set(j, temp);
	}


}
