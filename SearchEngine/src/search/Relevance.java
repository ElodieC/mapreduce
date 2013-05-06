package search;

import java.util.List;

public class Relevance {

	//private Seeker seeker;
	
	/**
	 * Mots entres dans le moteur de recherche
	 */
	private List<String> motsEntres;

	public Relevance(List<String> motsEntres){
		this.motsEntres = motsEntres;
	}

	public List<String> getMotsSorted(){
		quicksort(0,this.motsEntres.size()-1);
		return this.motsEntres;
	}

	private void quicksort(int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		Seeker seeker=new Seeker();
		int pivot = seeker.getNbOccurences(this.motsEntres.get(low + (high-low)/2));

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (seeker.getNbOccurences(this.motsEntres.get(i)) < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (seeker.getNbOccurences(this.motsEntres.get(j)) > pivot) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	private void exchange(int i, int j) {
		String temp = this.motsEntres.get(i);
		this.motsEntres.set(i, this.motsEntres.get(j));
		this.motsEntres.set(j, temp);
	}


}
