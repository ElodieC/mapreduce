import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class IndexDriver {

	public static void main(String[] args) throws Exception{
		
		Job job = new Job();
		job.setJarByClass(IndexDriver.class);

		// TODO: specify input and output DIRECTORIES 
		FileInputFormat.addInputPath(job,new Path("/home/hduser/hadoop/livres"));
		FileOutputFormat.setOutputPath(job,new Path("/home/hduser/hadoop/francais"));//ne doit pas exister

		job.setMapperClass(IndexMapper.class);

		job.setReducerClass(IndexReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
