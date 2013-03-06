import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class IndexReducer extends Reducer<Text,LongWritable,Text,Text> {

	public void reduce(Text key, Iterator<LongWritable> values, Context context) throws IOException,InterruptedException {
		
		//pour stocker les offsets et les ecrire a la fin
		String offsets="";
		final String separator = ",";
		
		while (values.hasNext()) {
			LongWritable value = (LongWritable) values.next();
			if (offsets.equals("")){//oui c'est pas joli je sais pas comment faire autrement
				offsets=value.toString();
			}
			else{
				offsets+=separator+value.toString();
			}
		}
		context.write(key,new Text(offsets));
	}

}
