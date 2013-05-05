package grammar;

import index.IndexBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import search.Search;
import window.Logger;

public class Index2 {
	private static Scanner scanLine;
	public static IndexBuilder builder;
	public static Search recherche;
	
	
	public static void scanFile(BufferedReader input){
		Logger.addInLog("Analyse de l'index commencée");
		System.out.println("Analyse de l'index commencée");
		String scanFile = null;
		boolean EOF = false;
		
		while(!EOF){
			try {
				scanFile = input.readLine();
				if (scanFile == null) {
					EOF = true;
				}
				else {
					builder(scanFile);
				}
			} catch (IOException e) {
				Logger.addInLog("Erreur dans la lecture par ligne du fichier : ");
				System.out.println("catch");
				e.printStackTrace();
				Logger.addInLog(e.getMessage());
			}
		}
		Logger.addInLog("Analyse de l'index terminée");
		System.out.println("Analyse de l'index terminée");
		recherche.toDo();
	}
	
	public static void builder (String line) {
		String readWord;
		String title;
		int offset;
		boolean first = true;
		
		scanLine = new Scanner(line);
		scanLine.useDelimiter("\\s");// a whitespace character
		
		while (scanLine.hasNext()){
			if (first){//if it's the first token it's the word
				readWord = scanLine.next();
				builder.addWord(readWord);
				first = false;
			}
			else if (scanLine.hasNext(",")){
				//we pass the points
				scanLine.next(",");
			}
			else if (scanLine.hasNext("\\S+\\.txt")){//a series of letters, numbers and punctuation
				title = scanLine.next();			//with .txt that's the title
				builder.addFile(title);
			}
			else if (scanLine.hasNextInt()) {//remains numbers, if its aren't first, 
				offset = scanLine.nextInt();//then its are offsets
				builder.addOffset(offset);
			}
			else {
				Logger.addInLog("Problème dans la lecture de l'index, pattern non reconnu");
			}
		}
		builder.buildSet();
	}
	
	public static void build(String args, Search search) {
		BufferedReader input;
		try {
			builder = new IndexBuilder();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.addInLog(e.getMessage());
		}
		recherche = search;
	    	   
	    try {
	    	input = new BufferedReader(new FileReader(args+".txt"));
	    } catch (java.io.FileNotFoundException e) {
	    	System.out.println("Fichier index introuvable.");
	    	Logger.addInLog("Fichier index introuvable, nom donné : "+args+".txt");
	    	return;
	    }
	    
	    Logger.addInLog("Lecture de l'index reussie");
	    System.out.println("Lecture de l'index reussie");
	    scanFile(input);
	}
}
