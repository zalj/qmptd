package socketTest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class sendServerSocket {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1234);
		Socket socket = server.accept();
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		for(int i = 1; i < 20000; i++) {
			String str = System.currentTimeMillis() + "," + i;
			dataOutputStream.writeUTF(str);
		}
		server.close();
	}

}
