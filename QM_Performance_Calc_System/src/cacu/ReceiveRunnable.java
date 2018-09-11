package cacu;

import java.io.*;
import java.net.Socket;


public class ReceiveRunnable implements Runnable {
	private Socket receiveSocket;
	public DataInputStream dataInputStream;
	public OutputStream outputStream;
	public static Calculate cal = new Calculate(System.currentTimeMillis(),0);
	
	
	
	public ReceiveRunnable(Socket socket) {
		receiveSocket = socket;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < 100; i++) {
				String[] fields;
				String temp = "";
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
				while(bufferedReader.readLine() != null) {
					temp = temp + bufferedReader.readLine();
				}
				bufferedReader.close();
				fields = temp.split(",");
				cal = new Calculate(Long.valueOf(fields[0]), Integer.valueOf(fields[1]));
			}
			Thread.sleep(1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public String[] deSerializae() throws IOException {
		String temp = "";
		String[] fields;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
		while(bufferedReader.readLine() != null) {
			temp = temp + bufferedReader.readLine();
		}
		bufferedReader.close();
		fields = temp.split(",");
		return fields;
	}
}
