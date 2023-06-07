# Question1

```java

public patternMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final Text patternKey = new Text();
    private final IntWritable one = new IntWritable(1);
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        Configuration conf =context.Configuration;
        String patterns[] = conf.get("patterns");
        for(String pattern: patterns) {
            if(match(line, pattern)) {
                patternKey.set(pattern);
                context.write(patternKey, one);
            }
        }
    }
}
public patternReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    private final IntWritable result = new IntWritable();
    public void reduce(Text key, IntWritable<Iterable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable value: values) {
            sum += value.get();
        }
        result.set(sum);
        context.write(key, sum);
    }
}

```