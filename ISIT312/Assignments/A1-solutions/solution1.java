//Dependency imports
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.util.Random;
//Extra import
import org.apache.hadoop.io.IOUtils;

public class solution1 {
	public static void main(String[] args) throws IOException {
		// Instantiate a configuration 
		Configuration conf = new Configuration();
		// Instantiate an input stream
		FSDataInputStream in1 = null;
		// Instantiate a hdfs using the URI of an input directory
		FileSystem hdfs = FileSystem.get(URI.create(args[1]), conf);
		try {
		   // Instantiate a file system using the configuration
		   FileSystem fs = FileSystem.get(URI.create(args[0]), conf);
		   // Instantiate an input directory (path)
		   Path inFile1 = new Path(args[0]); 
		   // Open the file specified in input directory 
		   in1 = fs.open(inFile1); 
		   // Instantiate an output directory (path)
		   Path outFile = new Path(args[1]); 
		   // Instantiate on output stream according to the
		   // output directory (path)
		   FSDataOutputStream out = hdfs.create(outFile);
		   // Create a buffer array to read bytes of data
		   byte buffer[] = new byte[256];
		   // Initialize a counter
		   int bytesRead = 0;
		   // Loop over the input stream until there is no
		   // more data
		   while( (bytesRead = in1.read(buffer)) > 0) 
			// Write the content of buffer to output stream
			out.write(buffer, 0, bytesRead);
		   // When the input stream is emptied, close the input file
		   in1.close();
		   // Deletes file from where inFile1
		   fs.delete(inFile1, true);
		} catch (IOException e) {
		    e.printStackTrace();
		}
   	}
//parent class closing
}