import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WordCountMapper extends Mapper<LongWritable, Text, Text, Text> {
/*
 * On va dire qu'en entree on veut le Text
 * Et en sortie chaque mot
 * et le fichier dans lequel il apparait
 * Peut etre mettre en key (mot, fichier)
 * et en value occurence
 */
	public void map(LongWritable key, Text values,
			Context context) throws IOException, InterruptedException {
		String line = values.toString().toLowerCase();//on ignore la casse
		String[] tabSplitLine = line.split(" ");
		/*pour l'instant on va dire que les mots sont seulements separes par des espaces
		 * dans une ligne
		 * */
		//TODO gerer les ponctuations (les enlever)
		for (String mot:tabSplitLine){
			//TODO compter le nombre de mots par ligne
			context.write(new Text(mot), new Text());
		}
	}

}
