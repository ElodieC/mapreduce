import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class IndexMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
/*
 * On va dire qu'en entree on veut le Text
 * Et en sortie chaque mot
 * et le fichier dans lequel il apparait
 * et son offset
 * En sortie on veut en key mot, nomFichier
 * et en value offset
 */
	public void map(LongWritable key, Text values,
			Context context) throws IOException, InterruptedException {
		String line = values.toString().toLowerCase();//on ignore la casse
		StringTokenizer token = new StringTokenizer(line, " ");
		
		//On recupere le nom de fichier
		InputSplit split = context.getInputSplit();
		String fileName=null;
		if (split != null){//pour les tests a effacer apres
			fileName = ((FileSplit) split).getPath().getName();
		}
		
		
		/*pour l'instant on va dire que les mots sont seulements separes par des espaces
		 * dans une ligne
		 * */
		//TODO gerer les ponctuations (les enlever)
		while (token.hasMoreTokens()){
			context.write(new Text(token.nextToken()/*le mot*/ + "," +fileName), key);
		}
	}

}
