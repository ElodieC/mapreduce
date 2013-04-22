package reading;

import index.IndexBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import search.Search;

public class ReadFile {
	
	private String fileName;
	private BufferedReader fileBuffer;
	
	private IndexBuilder builder;
	private Search recherche;
	
	public ReadFile(String fileName, Search recherche){
		this.fileName = fileName;
		this.recherche = recherche;
	}
	
	public static void main(String[] args){
		
	FileReader fileReader;
	
	String line;
	try {
		fileReader = new FileReader("line.txt");
		BufferedReader in = new BufferedReader(fileReader);
		while((line =in.readLine())!= null){
			System.out.println(line);
		}
		in.close();
		fileReader.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
