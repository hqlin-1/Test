package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {
	public static void main(String[] args) {
        
		try {
			// 准备服务 ip(本机)端口
			ServerSocket serversocket = new ServerSocket(8888);
			Socket socket=serversocket.accept();
			System.out.println("客户端连接成功");
			InputStream inputStream = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String info=null;
			while((info=reader.readLine())!=null) {
				System.out.println(info);
			}
//			OutputStream outputStream = socket.getOutputStream();
       //			outputStream.write("欢迎你".getBytes());
//			outputStream.write("欢迎你".getBytes());
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}

}
