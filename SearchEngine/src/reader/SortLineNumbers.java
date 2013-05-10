package reader;

import java.util.List;
/**
 * Classe qui applique l'algorithme de tri rapide pour trier les numéros de ligne par ordre croissant
 * @author Corbel Elodie
 *
 */
public class SortLineNumbers {
	/**
	 * Liste des numéros de ligne
	 */
	private List<Long> listLignes;
	/**
	 * Constructeur
	 * @param listLignes liste des lignes a trier
	 */
	public SortLineNumbers(List<Long> listLignes){
		this.listLignes = listLignes;
	}
	/**
	 * Tri les lignes par ordre croissant du numéro de ligne
	 * @return la liste des lignes triées par ordre croissant du numéro de ligne
	 */
	public List<Long> getLinesSorted(){
		quicksort(0,this.listLignes.size()-1);
		return this.listLignes;
	}
	/**
	 * Algorithme de tri rapide
	 * @param low indice inferieur non trié
	 * @param high indice superieur non trié
	 */
	private void quicksort(int low, int high) {
		int i = low, j = high;
		Long pivot = this.listLignes.get(low + (high-low)/2);

		while (i <= j) {
			while (this.listLignes.get(i) < pivot) {
				i++;
			}
			while (this.listLignes.get(j) > pivot) {
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
	 * Permute 2 éléments de la liste des lignes
	 * @param i indice du premier element
	 * @param j indice du second element
	 */
	private void exchange(int i, int j) {
		Long temp = this.listLignes.get(i);
		this.listLignes.set(i, this.listLignes.get(j));
		this.listLignes.set(j, temp);
	}
}
