package cacu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class CalcuTest {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException{
    		launch();
    }

    public static void launch() throws UnknownHostException, IOException, InterruptedException{
//    	String receiveHost = "localhost";
    	int receivePort = 1234;
//    	String sendHost = "localhost";
//    	int sendPort = 4321;
    	@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(receivePort);
    	Socket receiveSocket = ss.accept();
    	Runnable receive = new ReceiveRunnable(receiveSocket);
    	Thread receiveThread = new Thread(receive);
    	receiveThread.start();	
//    	Runnable send = new sendRunnable(sendSocket);
    	
//    	Thread sendThread = new Thread(send);
    	
//    	sendThread.start();
    	//receiveSocket.close();
    }
    

    public static String getTime() {
    	long totalMillSeconds = System.currentTimeMillis();
    	long totalSeconds = totalMillSeconds / 1000;
    	long currentSeconds = totalSeconds % 60;
    	long totalMinutes = totalSeconds / 60;
    	long currentMinutes = totalMinutes % 60;
    	long totalHours = totalMinutes / 60;
    	long currentHours = totalHours % 24;
    	String time =  "Current time is " + (currentHours + 8) % 24 + ":" + currentMinutes + ":" + currentSeconds;
//    	System.out.println("Current time is " + currentHours + ":" + currentMinutes + ":" + currentSeconds);
//    	System.out.println("China Time is " + (currentHours + 8) % 24 + ":" + currentMinutes + ":" + currentSeconds);
    	return time;
    }
}
