package cacu;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
   *    将计算结果发往目的socket的线程
 * @author zz562334786
 *
 */
public class SendRunnable implements Runnable {
	private Socket sendSocket;
	public DataOutputStream dataOutputStream;
	
	public SendRunnable() {}
	public SendRunnable(Socket socket) {
		sendSocket = socket;
	}
	
	@Override
	public void run() {
		try {
			dataOutputStream = new DataOutputStream(sendSocket.getOutputStream());			
			dataOutputStream.writeUTF(serializae(ReceiveRunnable.cal));
			Thread.sleep(1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String serializae(Calculate cal) {
        String res = "";
        res = res + cal.getTimeDelay() + ",";
        res = res + cal.getJitter()[0] + ",";
        res = res + cal.getJitter()[1] + ",";
        res = res + cal.getMissingRate() + ",";
        res = res + Calculate.getCountOfCalculate();
        return res;
	}
}