import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;
/**
 * Classe qui gère les fichiers en entrée
 * Ici, elle sert à indiquer que l'on ne veut pas qu'hadoop découpe les fichiers
 * @author Corbel Elodie
 * @see FileInputFormat
 */
public class NotSplit<K,V> extends FileInputFormat<K, V> {

	/**
	 * Pour qu'un fichier ne soit pas découpé par hadoop afin d'obtenir les numéros de ligne
	 */
	@Override
	public boolean isSplitable(JobContext context, Path filename){
		return false;
	}

	@Override
	public RecordReader<K, V> createRecordReader(InputSplit split,
			TaskAttemptContext task) throws IOException, InterruptedException {
		return (RecordReader<K, V>) new LineRecordReader(/*(FileSplit)split*/);
	}

}
