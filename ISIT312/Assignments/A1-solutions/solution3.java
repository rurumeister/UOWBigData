//Dependency imports
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class solution3 {
	public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "word count");
	    job.setJarByClass(solution3.class);
	    job.setMapperClass(TokenizerMapper.class);
	    //job.setCombinerClass(IntSumReducer.class); redundant function
	    job.setReducerClass(IntSumReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
  	}
//Editted mapper
  	public static class TokenizerMapper
       		extends Mapper<Object, Text, Text, IntWritable>{
		    //Variable declarations
		    private final static IntWritable one = new IntWritable(1);
		    private final static IntWritable zero = new IntWritable(0);
		    private final static Text xs = new Text("1X short:");
		    private final static Text s = new Text("2short:");
		    private final static Text m = new Text("3medium:");
		    private final static Text l = new Text("4long:");
		    private final static Text xl = new Text("5X long:");
		    private final static Text xxl = new Text("6XX long:");

		    public void map(Object key, Text value, Context context
				    ) throws IOException, InterruptedException {
		      StringTokenizer itr = new StringTokenizer(value.toString());
		      while (itr.hasMoreTokens()) { //not use .nextToken() in case of empty value.
			String aWord = itr.nextToken(); 
			int wordLen = aWord.length();
			//conditional flow
			if (wordLen >= 1 && wordLen <= 3) 
				context.write(xs, one);
			else
				context.write(xs, zero);	
			if (wordLen >= 4 && wordLen <= 5) 
				context.write(s, one);
			else
				context.write(s, zero);	
			if (wordLen >= 6 && wordLen <= 8)
				context.write(m, one);
			else
				context.write(m, zero);	
			if (wordLen >= 9 && wordLen <= 12) 
				context.write(l, one);
			else 
				context.write(l, zero);	
			if (wordLen >= 13 && wordLen <= 15) 
				context.write(xl, one);
			else
				context.write(xl, zero);	
			if (wordLen >= 16) 
				context.write(xxl, one);
			else 
				context.write(xxl, zero);	
	      	   	}
	        }
  	}

	  //Editted sumreducer - as per tutorial
	  public static class IntSumReducer
	       extends Reducer<Text,IntWritable,Text,Text> {
		    private Text result1 = new Text();
		    private Text result2 = new Text();
		    public void reduce(Text key, Iterable<IntWritable> values,
				       Context context
				       ) throws IOException, InterruptedException {
		      int sum = 0;
		      for (IntWritable val : values) {
			sum += val.get();
		      }
		      //Remove the prefixed sequence number
		      String newKey = key.toString().substring(1);
		      result1.set(String.format("%-9s", newKey));
		      // Form the count and "words"
		      String temp = String.format("%2s words", sum);
		      result2.set(temp);
		      //Write the key value pairs
		      context.write(result1, result2);
		    }
	  }
//parent class closing
}
