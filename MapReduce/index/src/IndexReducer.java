import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class IndexReducer extends Reducer<Text,Text,Text,Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,InterruptedException {
		
		//pour stocker les offsets et les ecrire a la fin
		String offsets="";
		final String separator = " , ";
		
		for (Text value:values) {
			if (offsets.equals("")){//oui c'est pas joli je sais pas comment faire autrement
				offsets=value.toString();
			}
			else {
				offsets+=separator+value.toString();
			}
		}
		context.write(key,new Text(offsets));
	}

}
