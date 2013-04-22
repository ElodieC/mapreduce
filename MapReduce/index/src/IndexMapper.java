import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class IndexMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	
/*
 * On va dire qu'en entree on veut le Text
 * Et en sortie chaque mot
 * et le fichier dans lequel il apparait
 * et son offset
 * En sortie on veut en key mot, nomFichier
 * et en value offset
 */
	/**
	 * Numero de la ligne dans le fichier
	 */
	private int lineNumber=0;
	
	public void map(LongWritable key, Text values,
			Context context) throws IOException, InterruptedException {
		lineNumber++;
		String line = values.toString().toLowerCase();//on ignore la casse
		line=supprimerPonctuation(line);
		StringTokenizer token = new StringTokenizer(line, " ");
		
		//On recupere le nom de fichier
		InputSplit split = context.getInputSplit();
		String fileName=null;
		if (split != null){//pour les tests a effacer apres
			fileName = ((FileSplit) split).getPath().getName();
		}
		/**
		 * Decallage par rapport au debut de la ligne
		 */
		int lineOffset = 0;
		
		while (token.hasMoreTokens()){
			lineOffset++;
			Text t = new Text (token.nextToken());//le mot
			if (!motAIgnorer(t))
				context.write(new Text(t + "," +fileName), new Text(""+lineNumber/*key.toString()*/));
		}
	}
	/**
	 * 
	 * @param text
	 * @return vrai si le mot est a ignorer (non pertinent)
	 */
	public static boolean motAIgnorer(Text text){
		boolean res=false;
		List<String> caracteresIgnores = new ArrayList<String>();
		String[] tab = {"et", "ou", "de", "des", "d", "le", "les","l","au","aux","du","un",
				"une","a","à","or","ni","que","si","y"};
		caracteresIgnores=Arrays.asList(tab);
		if (caracteresIgnores.contains(text.toString())){
			res= true;
		}
		return res;
	}
	
	public static String supprimerPonctuation(String texte)
	   {
	      StringBuffer sb = new StringBuffer();
	      for (String s : texte.split("[\\p{P}]"))
	         sb.append(s);
	      return sb.toString();      
	   }

}
