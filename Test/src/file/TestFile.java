package file;

import java.io.File;
import java.io.IOException;

public class TestFile {
	public static void main(String[] args) {
		File file = new File("d:/TestFile.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("创建成功");
			} else {
				file.delete();
				System.out.println("删除成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
