import java.netimport java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.util.Random;

public class FileSystemPut {
   public static void main(String[] args) throws IOException {
	Configuration conf = new Configuration();
	FSDataInputStream in1 = null;
	FileSystem hdfs = FileSystem.get(URI.create(args[1]), conf);
	try {
	   FileSystem fs = FileSystem.get(conf); 
	   Path inFile1 = new Path(args[0]); 
	   in1 = fs.open(inFile1); 
	   Path outFile = new Path(args[1]); 
	   FSDataOutputStream out = hdfs.create(outFile);
	   byte buffer[] = new byte[256];
	   int bytesRead = 0;
	   while( (bytesRead = in1.read(buffer)) > 0) 
		out.write(buffer, 0, bytesRead);
	   in1.close();
	} catch (IOException e) {
            e.printStackTrace();
        }
   }
}