package grammar;

import index.IndexBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import search.Search;

public class Index2 {
	private static Scanner scanFile;
	private static Scanner scanLine;
	public static IndexBuilder builder=null;
	public static Search recherche;


	public static void scanFile(InputStream intput){
		System.out.println("Analyse du fichier commencee");
		scanFile = new Scanner(intput);
		scanFile.useDelimiter("\n");
		while (scanFile.hasNext()){
			String currentLine = scanFile.next();//the  line we'll analyze
			builder(currentLine);
		}
		System.out.println("Analyse du fichier terminee");
		
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
		}
		builder.buildSet();
	}

	public static void build(String args, Search search) {
		if(builder==null){
			InputStream input;
			try {
				builder = new IndexBuilder();
			} catch (IOException e) {
				e.printStackTrace();
			}


			try {
				input = new FileInputStream(args+".txt");
			} catch (java.io.FileNotFoundException e) {
				System.out.println("Fichier introuvable.");
				return;
			}
			scanFile(input);
			System.out.println("Lecture du fichier reussie");
			
		}
		recherche = search;
		recherche.toDo();
		
	}
}
