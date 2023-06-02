//Dependency imports
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class solution2 {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "State Rainfall");
		job.setJarByClass(solution2.class);
		job.setMapperClass(TokenizeMapper.class);
		job.setReducerClass(StateRainfallReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0: 1);	
	}
	//Tokenize Mapper
	public static class TokenizeMapper extends Mapper<Object, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text stateName = new Text();
		private IntWritable rain = new IntWritable();
		private int anInt = 0;
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			try {
				//Tokenize the data using regex expression that matches one or more characters.
				String[] inBlock = value.toString().split("\\s+");
				//State name
				stateName.set(inBlock[0]);
				//Rainfall
				rain.set(Integer.parseInt(inBlock[2]));
				//Writing key-value pairs
				context.write(stateName, rain);			
			}		
			catch(Exception e) {
				System.out.println(e.getMessage());		
			}
		}
	}
	//Editted sumreducer - as per tutorial
	public static class StateRainfallReducer extends Reducer<Text,IntWritable,Text,Text> {
        //variable declaration
		private String totalS = null;
		private String maxS = null;
		private String minS = null;
		private String resultS = null;
		private Text result1 = new Text();
		private Text result2 = new Text();
        //reduce(), returns a reduced string from the Text, IntWritable, Text, Text input.
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            //variable declaration
			int totalRainfall = 0;
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
            //iterate through values
			for (IntWritable val: values) {
				totalRainfall += val.get();//accumulate the totalRainfall
                //Finding max and min rainfall
				if(val.get() > max)
					max = val.get();
				if(val.get() < min)
					min = val.get();
			}	
			//Building output string
			result1.set(String.format("%-19s", key));
			totalS = String.format("%-4s", totalRainfall);
			maxS = String.format("%-4s", max);
			minS = String.format("%-4s", min);
			resultS = totalS + maxS + minS;
			result2.set(resultS);
			context.write(result1, result2);
		}
	}
//parent class closing
}