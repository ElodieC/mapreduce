package search;

import java.util.ArrayList;

import index.IndexBuilder;

public class Seeker {
	public String message;
	
	public Seeker() {
		super();
		this.message = "";
	}
	
	public void seek(String word){
		if(isPresent(word))
		{
			System.out.println("Mot trouvé !");
			message+="Mot "+word+" trouvé\n";
			int occurences = getFilesOccurences(word);
			if (occurences == 1)
			{
				System.out.println("Le mot "+word+" a été trouvé : "+occurences+" occurence dans le fichier "+getFichiers(word)+" lignes "+getLines(word));
				message+="\n\nLe mot "+word+" a été trouvé : "+occurences+" occurence dans le fichier "+getFichiers(word)+" lignes "+getLines(word);
			}
			else
			{
				System.out.println("Le mot "+word+" a été trouvé : "+occurences+" occurence dans les fichiers "+getFichiers(word)+" lignes "+getLines(word));
				message+="\n\nLe mot "+word+" a été trouvé : "+occurences+" occurence dans le fichier "+getFichiers(word)+" lignes "+getLines(word);
			}
		}
		else
		{
			System.out.println("Mot non trouvé");
			message+="Mot "+word+" non trouvé\n";
		}
	}
	
	public boolean isPresent(String word){
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				return true;
			}
		}
		return false;
	}
	
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
	
	public ArrayList<Long> getLines(String word){
		ArrayList<Long> lines = new ArrayList<Long>();
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				lines.addAll(IndexBuilder.getIndex().get(i).get(word).getOffsets());
			}
		}
		return lines;
	}
	
	public ArrayList<String> getFichiers(String word){
		ArrayList<String> files = new ArrayList<String>();
		for(int i=1 ; i<= IndexBuilder.getIndex().size(); i++){
			if(IndexBuilder.getIndex().get(i).keySet().contains(word))
			{
				files.add(IndexBuilder.getIndex().get(i).get(word).getfile());
			}
		}
		return files;
	}
}
