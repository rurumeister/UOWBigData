// cc FileSystemCat Displays files from a Hadoop filesystem on standard output by using the FileSystem directly
//import java.io.InputStream;

import java.net.URI;
import java.io.IOException; //used to handle errors
import java.util.StringTokenizer; //used to split strings
import org.apache.hadoop.conf.Configuration; //used to configure hadoop
import org.apache.hadoop.fs.FileSystem; //used to create filesystems
import org.apache.hadoop.fs.Path; //namenode manager
import org.apache.hadoop.fs.FSDataInputStream; //used to read input and output
import org.apache.hadoop.io.IOUtils; //used to write input and output
import org.apache.hadoop.io.IntWritable; //used to write integers
import org.apache.hadoop.io.Text; //used to write text
import org.apache.hadoop.mapreduce.Job; //used to create jobs
import org.apache.hadoop.mapreduce.Mapper; //used to create mappers
import org.apache.hadoop.mapreduce.Reducer; //used to create reducers
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; //used to format input files
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; //used to format output files

public class WordCount {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count"); //create a new job
        job.setJarByClass(WordCount.class); //set the jar file
        job.setMapperClass(TokenizerMapper.class); //set the mapper class
        job.setCombinerClass(IntSumReducer.class); //set the combiner class
        job.setReducerClass(IntSumReducer.class); //set the reducer class
        job.setOutputKeyClass(Text.class); //set the output key class
        job.setOutputValueClass(IntWritable.class); //set the output value class
        FileInputFormat.addInputPath(job, new Path(args[0])); //set the input path
        FileOutputFormat.setOutputPath(job, new Path(args[1])); //set the output path
        System.exit(job.waitForCompletion(true) ? 0 : 1); //exit the job
    }
    //TokenizerMapper class to map the values (split the words)
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1); //set the value of one
        private Text word = new Text(); //set the value of word
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString()); //create a new string tokenizer
            while (itr.hasMoreTokens()) { //while there are more tokens
                word.set(itr.nextToken()); //set the word
                context.write(word, one); //write the word and one
            }
        }
    }
    //IntSumReducer class to reduce the values (add the words)
    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable(); //set the value of result
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0; //set the value of sum
            for (IntWritable val : values) { //for each value in values
                sum += val.get(); //add the value to sum
            }
            result.set(sum); //set the result
            context.write(key, result); //write the key and result
        }
    }

}