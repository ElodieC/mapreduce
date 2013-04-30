import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class NotSplit<K,V> extends FileInputFormat<K, V> {

	/**
	 * To make the file not split in different mappers
	 */
	@Override
	public boolean isSplitable(JobContext context, Path filename){
		return false;
	}

	@Override
	public RecordReader<K, V> createRecordReader(InputSplit split,
			TaskAttemptContext task) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return (RecordReader<K, V>) new LineRecordReader(/*(FileSplit)split*/);
	}

}
