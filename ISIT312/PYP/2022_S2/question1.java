// The World Meteorological Organization created a file hightemp.txt with information about the 
// highest temperatures recorded every day in a number of cities all over the world. The file
// higtemp.txt is a text file where information about the highest temperature recorded on a given 
// day, in a given city is stored in a single row. Data items like date, temperature, city name are 
// separated with a single blank. For example, few sample rows from the file are listed below
// 01-JAN-1991 25 Sydney
// 01-JAN-1991 30 Brisbane
// 01-JAN-1991 32 Singapore
//  ... ... ...
// 02-JAN-1991 25 Sydney
// 02-JAN-1991 31 Brisbane
// 02-JAN-1991 35 Singapore
//  ... ... ...
// 05-JUN-2022 15 Sydney
// 05-JUN-2022 20 Brisbane
// 05-JUN-2022 25 Singapore
// (1) 6 marks
// Write an implementation of Map-Reduce application that lists the highest temperature recorded
// together with a name of city where such temperature was recorded in a given year. Organize your 
// application such that it would be possible to re-use it for any given year.
// For example, if we process only 9 rows visible above then the highest temperature recorded in 
// 1991 was 35 in Singapore.
// Assume that due to the global warming no negative temperatures are recorded.
// To write your application use Java programming language or pseudo-code at a level of complexity 
// of Java statements. So, for example, you can write your application in Python or in some other 
// pseudo-code that looks like Java. It is compulsory to include the comments explaining your code. 
// Please, write only the implementations of Mapper and Reducer
public class HighestTemp {
    public static void main(String[] args) {
        
    }
    public static class TokenizeMapper extends Mapper<Object, Text, Text, Text> {
        private Text year = new Text();
        private Text tempAndCity = new Text();
        public void map(Object key, Text value, Context context) {
         try {
            String line = value.toString();
            String[] data = line.split(" ");
            String date = data[0];
            int temp = Integer.parseInt(data[1]);
            String city = data[2];
            String yearString = date.split("-")[2];

            year.set(yearString);
            temperatureAndCity.set(temp + " " + city);
            context.write(year, temperatureAndCity);
         }   
         catch(Exception e) {
            System.out.println(e.getMessage());
         }
        }
    }

    public static class HighestTempReducer extends Reducer<Text, Text, Text, Text> {
       private IntWritable result = new IntWritable();
       public void reduce(Text key, Iterable<IntWritable> values, Context context) thows IOException, InterruptedException {
          int maxTemp = Integer.MIN_VALUE;
          String maxCity = "";
          for (Text val : values) {
            String[] data = val.toString().split(" ");
            int temp = Integer.parseInt(data[0]);
            String city = data[1];
            if (temp > max) {
                max = temp;
                maxCity = city;
            }
          }
          result.set(maxTemp + " " + maxCity);
          context.write(key, result);
       }
    }
}
/*
Assume that source code of your application is written in Java and it is available in a file 
highest.java. List all steps you should follow to get the outcomes from preparation and 
processing of your application. For example, to list the highest temperatures recorded, together 
with a city where such temperature was recorded in 2021.
1. Create a directory in HDFS to store the input file
2. Copy the input file to HDFS
3. Compile the source code of your application
4. Create a directory in HDFS to store the output file
5. Run your application
6. Copy the output file from HDFS to the local file system
7. Display the content of the output file
 */