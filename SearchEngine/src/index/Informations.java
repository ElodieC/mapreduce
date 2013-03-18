package index;

import java.util.ArrayList;

public class Informations {
	private ArrayList<String> offsets;
	private String file;
	
	public Informations(ArrayList<String> offsets, String file) {
		super();
		this.offsets = offsets;
		this.file = file;
	}
	public ArrayList<String> getOffsets() {
		return offsets;
	}
	public void setOffsets(ArrayList<String> offsets) {
		this.offsets = offsets;
	}
	public String getfile() {
		return file;
	}
	public void setfile(String file) {
		this.file = file;
	}
}
