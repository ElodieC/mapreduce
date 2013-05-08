import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Le Reducer de MapReduce
 * En entrée, on a pour clé intermédiaire le mot , le nom du fichier et en valeur le numéro de 
 * la ligne dans lequel il se trouve
 * En sortie, on a pour clé le mot, le nom du fichier et en valeur la liste des numéros de lignes
 * @author Corbel Elodie, Renou Clarisse
 * @see Reducer
 */
public class IndexReducer extends Reducer<Text,Text,Text,Text> {
	/**
	 * Fonction reduce qui aggrégège les résultats du mapper afin d'avoir en résultat la liste des numéros de lignes
	 */
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,InterruptedException {
		
		//pour stocker les lignes et les ecrire a la fin
		StringBuilder lineNumbers = new StringBuilder();
		final String separator = " ";
		
		for (Text value:values) {
			if (lineNumbers.toString().equals("")){
				lineNumbers.append(value.toString());
			}
			else {
				lineNumbers.append(separator+value.toString());
			}
		}
		context.write(key,new Text(lineNumbers.toString()));
	}

}
