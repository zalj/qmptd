package cacu;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Client {
	public static String IP_ADDR = "localhost";
	public static int port = 12345;
	public static int packageId = 0;
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Client start...");
		File file = new File("IP_ADDR.txt");
		if(file.exists()) {
			try(
				Scanner scanner = new Scanner(file);
			){
				IP_ADDR = scanner.next().split("=")[1];
				port = Integer.parseInt(scanner.next().split("=")[1]);
			}
		}
		System.out.println("开始发送模拟数据！");
		while(true) {
			Socket socket = null;
			try {
				socket = new Socket(IP_ADDR, port);
				
				DataOutputStream out  = new DataOutputStream(socket.getOutputStream());
				
				String str = System.currentTimeMillis() + "," + packageId;
				if(Math.random() * 40 > 2)
					packageId += 1;
				else
					packageId += (int)(Math.random() * 3) + 1;
				Thread.sleep((int)(Math.random() * 1800) + 200);
				out.writeUTF(str);
				out.close();
				System.out.println("(TimeStamp, packageId) = (" + str + ")");
			} catch (Exception e) {
				System.out.println("exception :" + e.getMessage());
			}finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
