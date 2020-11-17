package Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("发送连接");
			Socket socket = new Socket("127.0.0.1",8888);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("Hello Service".getBytes());
			socket.shutdownOutput();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
