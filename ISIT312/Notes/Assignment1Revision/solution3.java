//Various dependency imports
public class Solution3 {
    public static void main(String[] args) {
        
    }

    //TokeniseMapper
    public static class TokenizeMapper extends Mapper <Ojbect, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private final static IntWritable two = new IntWritable(0);
        private final static Text xs = new Text("1X short:");
        private final static Text s = new Text("2short:");
        private final static Text m = new Text("3medium:");
        private final static Text l = new Text("4long:");
        private final static Text xl = new Text("5X long:");
        private final static Text xxl = new Text("6XX long:");
        public void map (Object key, Text value, Context context) throws IOException, InterruptedException {
            try  {
                StringTokenizer itr = new StringTokenizer(value.toString());
                while (itr.hasMoreTokens()) {
                    String aWord = itr.nextToken();
                    int wordLen = aWord.length();
                    if (wordLen >= 1 && wordLen <= 3) {
                        context.write(xs, one);
                    } else {
                        context.write(xs, two);
                    }
                    if (wordLen >= 4 && wordLen <= 5) {
                        context.write(s, one);
                    } else {
                        context.write(s, two);
                    }
                    if (wordLen >= 6 && wordLen <= 8) {
                        context.write(m, one);
                    } else {
                        context.write(m, two);
                    }
                    if (wordLen >= 9 && wordLen <= 12) {
                        context.write(l, one);
                    } else {
                        context.write(l, two);
                    }
                    if (wordLen >= 13 && wordLen <= 15) {
                        context.write(xl, one);
                    } else {
                        context.write(xl, two);
                    }
                    if (wordLen >= 16) {
                        context.write(xxl, one);
                    } else {
                        context.write(xxl, two);
                    }
                }
            } catch (Exception e) {
                Sytem.output.println(e.getMessage());
            }
        }
    }
    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, Text> {
        private Text result1 = new Text();
        private Text result2 = new Text();
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get(); //get the value of the IntWritable object
            }
            result1.set("Total number of words: ");
            result2.set(Integer.toString(sum));
            context.write(result1, result2);
        }
    }
}