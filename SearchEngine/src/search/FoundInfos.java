package search;

import java.util.List;

public class FoundInfos {
	public String mot;
	public List<String> fichiers;
	
	public FoundInfos(String mot, List<String> fichiers) {
		super();
		this.mot = mot;
		this.fichiers=fichiers;
	}
}
