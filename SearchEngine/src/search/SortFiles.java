package search;

import java.util.List;
import java.util.Map;
/**
 * Tri des noms de fichiers
 *
 */
public class SortFiles {
	private String[] fileNames={};
	private Map<String,List<Long>> fileOccurence;
	/**
	 * 
	 * @param fileNames liste des noms de fichier associes a un mot
	 * @param fileOccurence hashmap ayant pour cle les noms de fichiers et pour valeur la liste des lignes dans lesquels 
	 * apparait un mot donne
	 */
	public SortFiles(Map<String,List<Long>> fileOccurence){
		this.fileNames = fileOccurence.keySet().toArray(this.fileNames);
		this.fileOccurence = fileOccurence;
	}
	/**
	 * @return liste des noms de fichiers trie par ordre decroissant d'occurences du mot
	 */
	public String[] getSortedFileNames() {
		if (fileNames !=null && fileNames.length!=0){
			quicksort(0, fileNames.length - 1);
			return fileNames;
		}
		else 
			return null;
	}
	/**
	 * Tri des noms de fichiers base sur l'algorithme du tri rapide 
	 * @param low indice inferieur non trie
	 * @param high indice superieur non trie
	 */
	private void quicksort(int low, int high) {
		int i = low, j = high;
		int pivot = fileOccurence.get(fileNames[low + (high-low)/2]).size();

		while (i <= j) {
			while (fileOccurence.get(fileNames[i]).size() > pivot) {
				i++;
			}
			while (fileOccurence.get(fileNames[j]).size() < pivot) {
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
		String temp = fileNames[i];
		fileNames[i] = fileNames[j];
		fileNames[j] = temp;
	}

}
