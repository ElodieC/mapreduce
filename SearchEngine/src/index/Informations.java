package index;

import java.util.ArrayList;

/**
 * 
 * @author Olivier Mickael
 *
 */
public class Informations {
	//Informations liées à un mot, à savoir le fichier dans lequel il se trouve et les offsets où il a été repéré dans ce fichier
	private ArrayList<Long> offsets;
	private String file;
	
	@SuppressWarnings("unchecked")
	public Informations(ArrayList<Long> offsets, String file) {
		super();
		/*TRES TRES MOCHE !*/
		this.offsets = (ArrayList<Long>) offsets.clone();
		this.file = file;
	}
	public ArrayList<Long> getOffsets() {
		//System.out.println(offsets);
		return offsets;
	}
	public void setOffsets(ArrayList<Long> offsets) {
		this.offsets = offsets;
	}
	public String getfile() {
		return file;
	}
	public void setfile(String file) {
		this.file = file;
	}
}
