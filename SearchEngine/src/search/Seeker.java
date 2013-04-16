package search;

import index.IndexBuilder;

public class Seeker {
	
	public static void seek(String word){
		if(isPresent(word))
		{
			System.out.println("Mot trouvé !");
			int occurences = getOccurences(word);
			if (occurences == 1)
			{
				System.out.println("Le mot a été trouvé : "+occurences+" occurence dans le fichier "+getFichiers(word));
			}
			else
			{
				System.out.println("Le mot a été trouvé : "+occurences+" occurences dans les fichiers "+getFichiers(word));
			}
		}
		else
		{
			System.out.println("Mot non trouvé");
		}
	}
	
	public static boolean isPresent(String word){
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				return true;
			}
		}
		return false;
	}
	
	public static int getOccurences(String word){
		int num = 0;
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				num++;
			}
		}
		return num;
	}
	
	public static String getFichiers(String word){
		String files = "";
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				files = files + IndexBuilder.getIndex().get(i).get(word).getfile() + " ";
			}
		}
		return files;
	}
}
