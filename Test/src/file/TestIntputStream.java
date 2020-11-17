package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestIntputStream {
	public static void main(String[] args) {
		InputStream in = null;
		try {
			in = new FileInputStream("D:\\TestFile.txt");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			System.out.println(new String(buf));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
