package socketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class recieveServerSocket {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(4321);
		Socket socket = server.accept();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
		while(bufferedReader.readLine() != null) {
			System.out.println(bufferedReader.readLine());
		}
		server.close();
	}
}
