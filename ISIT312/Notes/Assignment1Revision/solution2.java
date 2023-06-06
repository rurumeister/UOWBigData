//Implementation of MapReduce Application
public class Solution2 {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();//create a new configuration
        Job job = Job.getInstance(conf, "State Rainfall");//create a new job
        job.setJarByClass(Solution2.class);//set the jar class
        job.setMapperClass(TokenizeMapper.class);//set the mapper class
        job.setReducerClass(StateRainfallReducer.class);//set the reducer class
        job.setOutputKeyClass(Text.class);//set the output key class
        job.setOutputValueClass(IntWritable.class);//set the output value class
        FileInputFormat.addInputPath(job, new Path(args[0]));//set the input path
        FileOutputFormat.setOutputPath(job, new Path(args[1]));//set the output path
        System.exit(job.waitForCompletion(true) ? 0 : 1);//exit the job
    }
    //TokenizeMapper class
    public static class TokenizeMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text stateName = new Text();
        private IntWritable rain = new IntWritable();
        private int anInt = 0;
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            try {
                String[] inBlock = value.toString().split("\\s+"); //split by one or more spaces
                stateName.set(inBlock[0]); //state name
                rain.set(Integer.parseInt(inBlock[2])); //rainfall
                context.write(stateName, rain); //write key-value pairs
            }
            catch (Exception e) {
                system.out.println(e.getMessage());
            }
        }
    }
    //StateRainfallReducer class
    public StateRainfallReducer extends Reducer<Text, IntWritable, Text, Text> {
        private String totalS = null;
        private String maxS = null;
        private String minS = null;
        private String resultS = null;
        private Text result1 = new Text();
        private Text result2 = new Text();
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int totalRainfall = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(IntWritable val: values) {
                totalRainfall += val.get(); //accumulate total rainfall
                if(val.get() > max) //find max rainfall
                    max = val.get();
                if(val.get() < min) //find min rainfall
                    min = val.get();
            }
            //build output string
            result1.set(String.format("%-19s", key));
            totalS = String.format("%-4s", totalRainfall);
            maxS = String.format("%-4s", max);
            minS = String.format("%-4s", min);
            resultS = totalS + maxS + minS;
            result2.set(resultS);
            context.write(result1, result2); //write output key value pairs
        }
    }
}