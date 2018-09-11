package cacu;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static int port = 0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("IP_ADDR.txt");
		if(file.exists()) {
			try(
				Scanner scanner = new Scanner(file);
			){
				scanner.next();
				port = Integer.parseInt(scanner.next().split("=")[1]);
			}
		}
		System.out.println("Server now Start...");
		DataBaseOperator.getInstance().delete();
		System.out.println("数据库刷新成功！");
		Server server = new Server();
		server.init();
	}
	public void init() {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				Socket client = serverSocket.accept();
				new HandlerThread(client);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
	class HandlerThread implements Runnable{
		private Socket socket;
		public static Calculate cal = null;
		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}
		public void run() {
			try {
				DataInputStream input = new DataInputStream(socket.getInputStream());
				String clientInputStr = input.readUTF();
				String[] temp = clientInputStr.split(",");
				long timeStamp = Long.valueOf(temp[0]);
				int packageId = Integer.valueOf(temp[1]);
				System.out.println("Deserializae: " + timeStamp + " " + packageId);
				cal = new Calculate(timeStamp, packageId);
				System.out.println("*********************************************************************");
				System.out.println("Time Delay: " + HandlerThread.cal.getTimeDelay());
				System.out.println("Average Time Delay: " + Calculate.getAverageTimeDelay());
				String missingRate = (int)(HandlerThread.cal.getMissingRate() * 10000) / 100.0 + "%";
				System.out.println("Missing Rate: " + missingRate);
				System.out.println("Good Jitter: " + HandlerThread.cal.getJitter()[0]);
				System.out.println("Bad Jitter: " + HandlerThread.cal.getJitter()[1]);
				System.out.println("Throughput Capacity: " + Calculate.getCountOfCalculate() + " Packages.");
				System.out.println("Missing " + Calculate.getMissCount() + " Packages");
				System.out.println("*********************************************************************");
				String[] timeDelay = new String[7];
				timeDelay[0] = HandlerThread.cal.getTimeDelay() + "";
				timeDelay[1] = (int)(Calculate.getAverageTimeDelay() * 1000) / 1000.0 + "";
				timeDelay[2] = (int)(HandlerThread.cal.getJitter()[0] * 1000) / 1000.0 + "";
				timeDelay[3] = (int)(HandlerThread.cal.getJitter()[1] * 1000) / 1000.0 + "";
				timeDelay[4] = Calculate.getMissCount() + "";
				timeDelay[5] = missingRate;
				timeDelay[6] = Calculate.getCountOfCalculate() + "";
				DataBaseOperator.getInstance().insertDelayData(timeDelay);
			} catch (Exception e) {
				System.out.println("Server Runtime exception: " + e.getMessage());
			} 
			finally {
				try {
					socket.close();
				} catch (Exception e2) {
					socket = null;
					System.out.println("Server finally exception: " + e2.getMessage());
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Sleep to die..." + e.getMessage());
			}
		}
	}
