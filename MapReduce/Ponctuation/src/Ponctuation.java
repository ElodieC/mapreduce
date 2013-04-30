import java.util.regex.Pattern;


public class Ponctuation {
	
	private String line;
	private Pattern pattern;
	
	public Ponctuation(String string){
		this.line = string;
		pattern = Pattern.compile("[,.?;/:!€]", Pattern.CASE_INSENSITIVE );
	}
	
	public String deletePonctuation(){
		String res="";
		String[] split = pattern.split(line);
		for(int i=0;i<split.length;i++){
			String s = split[i];
			System.out.println(s.toString());
			res+=s + " ";
		}
		
		
		return res;
	}
	
	public static void main(String[] args){
		Ponctuation p = new Ponctuation("boo,and,foo");
		System.out.println(p.deletePonctuation());
		
		//TODO choisir si on veut eliminer les ponctuations avant ou apres 
	}

}
