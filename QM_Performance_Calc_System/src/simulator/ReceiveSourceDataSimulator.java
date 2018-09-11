package simulator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class ReceiveSourceDataSimulator {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String sendHost = "localhost";
		System.out.println(11111111);
    	int sendPort = 1234;
    	System.out.println(11111111);
    	Socket sendSocket = new Socket(sendHost, sendPort);
    	System.out.println(333333333);
    	Runnable sendSourceData = new SimulatorSendRunnable(sendSocket);
    	System.out.println(11111111);
    	Thread test = new Thread(sendSourceData);
    	System.out.println(11111111);
    	test.start();
    	System.out.println(22222222);
	}

}
class SimulatorSendRunnable implements Runnable {
	private Socket sendSocket;
	public DataOutputStream dataOutputStream;
	private static int packageId = 0;
	public SimulatorSendRunnable() {}
	public SimulatorSendRunnable(Socket socket) {
		sendSocket = socket;
	}
	
	@Override
	public void run() {
		try {
			dataOutputStream = new DataOutputStream(sendSocket.getOutputStream());
			for(int i = 0; i < 100; i++) {
				dataOutputStream.writeLong(System.currentTimeMillis());
				dataOutputStream.writeChar(',');
				dataOutputStream.writeInt(packageId);
				if(Math.random() * 100 > 2)
					packageId += 1;
				else
					packageId += (int)(Math.random() * 3);
			}
				Thread.sleep(1000);
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}