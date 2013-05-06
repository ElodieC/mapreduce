package search;

import java.util.List;

public class FoundInfos {
	public String mot;
	public List<String> fichiers;
	
	public FoundInfos(String mot, List<String> fichiers) {
		this.mot = mot;
		this.fichiers=fichiers;
	}
	
	public String toString(){
		return mot+" : "+fichiers;
	}
}
