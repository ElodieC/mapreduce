package search;

import index.*;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Search {
	//Cette classe lance des seekers qui vont chacun chercher un mot dans le fichier texte et en font le bilan
	private ArrayList<String> toSeek;
	public static IndexBuilder builder;
	public String expression; 
	public Seeker seeker;
	public ArrayList<String> result;
	public ArrayList<Long> lineResults;

	public Search() {
		this.toSeek = new ArrayList<String>();
		this.expression="";
		this.seeker = new Seeker();
		this.result = new ArrayList<String>();
		lineResults = new ArrayList<Long>();
	}
	
	public void renderSearch(String word)
	{
		if(seeker.isPresent(word)){
			toSeek.addAll(seeker.getFichiers(word));
		}
	}
	
	public void evaluate()
	{
		StringTokenizer st = new StringTokenizer(expression, " ");
	    while (st.hasMoreTokens()) {
	        
	    }
	}
	
	public void seek(String word){
		//test
		seeker.seek(word);
	}

	public ArrayList<String> getToSeek() {
		return toSeek;
	}

	public void setToSeek(ArrayList<String> toSeek) {
		this.toSeek = toSeek;
	}
	
	public void toDo(){
		String[] arguments = expression.split(" ");
		for(int i=0; i<arguments.length;i++){
			this.seek(arguments[i]);
			this.renderSearch(arguments[i]);
			
			System.out.println(i);
			this.result.addAll(seeker.getFichiers(arguments[i]));
			
			this.lineResults.addAll(seeker.getLines(arguments[i]));
		}
	}
}
