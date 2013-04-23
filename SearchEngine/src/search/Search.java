package search;

import grammar.*;
import index.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Search {
	private ArrayList<String> toSeek;
	public static IndexBuilder builder;
	public String expression; 
	public String result;

	public Search() {
		this.toSeek = new ArrayList<String>();
		this.expression="";
		this.result="";
	}
	
	public Search(String exp) {
		this.toSeek = new ArrayList<String>();
		this.expression=exp;
	}
	
	public void renderSearch(String word)
	{
		if(Seeker.isPresent(word)){
			toSeek.add(Seeker.getFichiers(word));
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
		Seeker.seek(word);
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
			System.out.println(Seeker.getFichiers(arguments[i]));
			System.out.println(this.getToSeek().contains(Seeker.getFichiers(arguments[i])));
			this.result+=Seeker.getFichiers(arguments[i])+"\n";
			//this.result="coucou \n blatte attack !";
		}
	}
}
