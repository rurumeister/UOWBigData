//Implementation of a HDFS Client Application
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus; //This import is required for the FileStatus class
import org.apache.hadoop.fs.FileSystem; //This import is required for the FileSystem class
import org.apache.hadoop.fs.Path; //This import is required for the Path class
//Extra import
import org.apache.hadoop.io.IOUtils; //This import is required for the IOUtils.copyBytes() method

public class solution1 {
    public static void main(String[] args) throws IOExceptions {
        Configuration conf = new Configuration();
        FSDataInputStream in1 = null;
        FileSystem hdfs = FileSystem.get(URI.create(args[1]), conf);
        try {
            FileSystem fs = FileSystem.get(URI.create(args[0]), conf);
            Path inFile1 = newPath(args[0]);
            int1 = fs.open(inFile1);
            Path outFile - new Path(args[1]);
            FSDataOutputStream out = hdfs.create(outFile);
            byte buffer[] = new byte[256];
            int bytesRead = 0;
            while( (bytesRead = in1.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            in1.close();
            fs.delete(inFile1, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}