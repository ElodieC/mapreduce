import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Classe qui sert à configurer MapReduce et à lancer l'indexation
 * @author Corbel Elodie, Renou Clarisse
 *
 */
public class IndexDriver {

	public static void main(String[] args) throws Exception{
		
		Job job = new Job();
		job.setJarByClass(IndexDriver.class);

		FileInputFormat.addInputPath(job,new Path("/home/hduser/hadoop/inputFiles"));
		FileOutputFormat.setOutputPath(job,new Path("/home/hduser/hadoop/output"));//ne doit pas exister

		job.setMapperClass(IndexMapper.class);

		job.setReducerClass(IndexReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setInputFormatClass(NotSplit.class);
		
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
