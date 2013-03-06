import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class IndexReducer extends Reducer<Text,LongWritable,Text,Text> {

	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException,InterruptedException {
		// replace KeyType with the real type of your key
		KeyType key = (KeyType) _key;

		while (values.hasNext()) {
			// replace ValueType with the real type of your value
			ValueType value = (ValueType) values.next();

			// process value
		}
	}

}