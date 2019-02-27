package putData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileSystemCopy {
	
	public static void main(String[] args) throws Exception {
		String localSrc = args[0];
		String hdfsPath = args[1];
		
		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		OutputStream out = fs.create(new Path(hdfsPath), new Progressable() {
			public void progress() {
				System.out.print(".");
			}
		});
//		OutputStream out = fs.append(new Path(hdfsPath));		
		IOUtils.copyBytes(in, out, 4096, true);
	}

}
