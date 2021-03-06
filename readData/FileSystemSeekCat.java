package readData;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemSeekCat {	public static void main(String[] args) throws IOException {
	String uri = args[0];
	Configuration conf = new Configuration();
	FileSystem fs = FileSystem.get(URI.create(uri), conf);
	FSDataInputStream in = null;
	try {
		in = fs.open(new Path(uri));
		IOUtils.copyBytes(in, System.out, 4096, false);
		in.seek(0);
		IOUtils.copyBytes(in, System.out, 4096, false);
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		IOUtils.closeStream(in);
	}
}
}
