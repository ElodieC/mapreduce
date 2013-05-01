package index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IndexBuilder {
	//Classe chargée de construire l'index
	private static Map<Integer, HashMap<String, Informations > > index;
	private String currentWord;
	private String currentFile;
	private ArrayList<Long> offsets;
	private Integer currentID;
	
	public IndexBuilder() throws IOException{//pourquoi IOException ?
		index = new HashMap<Integer,HashMap<String, Informations > >();
		offsets = new ArrayList<Long>();
		currentID = 0;
	}

	public void addOffset(long entierLu) {
		//System.out.println(entierLu);
		offsets.add(entierLu);
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
			offsets.clear();
	}
	
	public static Map<Integer, HashMap<String, Informations > >  getIndex() {
		return index;
	}
}
