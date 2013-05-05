package grammar;

import index.IndexBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import search.Search;
import window.Logger;

/**
 * Cette classe va parcourire le fichier Index et appeler les methodes adequate pour
 * construire la hashmap
 * Elle est statique afin d'avoir acces à la creation d'index partout
 * scanLine est un {@link Scanner} qui va s'occupait mot par mot de la ligne pour construire la hashmap
 * builder est {@link IndexBuilder} charger de construire la hashmap
 * recherche est le {@link Search} 
 * @author Renou Clarisse
 * @see IndexBuilder
 *
 */
public class Index2 {
	private static Scanner scanLine;
	public static IndexBuilder builder=null;
	public static Search recherche;

	/**
	 * Cette methode scan ligne par ligne le fichier index pour la transmettre
	 * au {@link #builder(String)}
	 * @param input BufferedReader on entre le fichier index
	 * @see #builder(String)
	 */
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
				e.printStackTrace();
				Logger.addInLog(e.getMessage());
			}
		}
		Logger.addInLog("Analyse de l'index terminée");
		System.out.println("Analyse de l'index terminée");

	}

	/**
	 * Ici on scanne la ligne envoyee mot par mot
	 * on reccupere ainsi grace a l'index bien constitue, le titre, l'offset 
	 * et le mot en question
	 * l'index est constitue ainsi :
	 * word , title.txt , offset1 offset2 etc
	 * @param line String, La ligne envoyee
	 * @see IndexBuilder
	 */
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

	/**
	 * principale methode de la classe, elle initialise l'{@link IndexBuilder} 
	 * et le {@link BufferedReader} puis lance l'analyse
	 * @param args String, nom du fichier Index
	 * @param search Search
	 * @exception catch l'exception si le fichier n'est pas trouve
	 * @see Search#toDo()
	 */
	public static void build(String args, Search search) {
		if (builder==null){
			BufferedReader input;
			try {
				builder = new IndexBuilder();
			} catch (IOException e) {
				e.printStackTrace();
				Logger.addInLog(e.getMessage());
			}


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
		recherche = search;
		recherche.toDo();
	}
}
