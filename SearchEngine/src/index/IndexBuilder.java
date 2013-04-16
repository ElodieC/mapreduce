package index;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import search.Search;
import search.Seeker;

public class IndexBuilder {	
	private static Map<Integer, HashMap<String, Informations > > index;
	private String currentWord;
	private String currentFile;
	private ArrayList<String> offsets;
	private Integer currentID;
	
	public IndexBuilder() throws IOException{
		index = new HashMap<Integer,HashMap<String, Informations > >();
		offsets = new ArrayList<String>();
		currentID = 0;
	}

	public void addOffset(String ident) {
		offsets.add(ident);
	}

	public void addFile(String ident) {
		// TODO Auto-generated method stub
		currentFile = ident;
	}

	public void addWord(String ident) {
		// TODO Auto-generated method stub
		currentID++;
		currentWord = ident;
	}
	
	public void buildSet(){
			HashMap<String, Informations> mapped = new HashMap<String, Informations >();
			mapped.put(currentWord, new Informations(offsets, currentFile));
			getIndex().put(currentID, mapped);	
	}
	
	public static Map<Integer, HashMap<String, Informations > >  getIndex() {
		return index;
	}
}
