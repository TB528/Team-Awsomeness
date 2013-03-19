package send.recieve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Sender extends Thread{
	DatagramSocket s;
	byte[] buf = new byte[1000];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    InetAddress hostAddress;
    
    private LinkedList<String> messageQueue;

	public void run() {
		messageQueue = new LinkedList<String>();
        try { 
			s = new DatagramSocket();
			hostAddress = InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255});
            while ( true ) { 
                sendMessage(); 
                sleep( 1000 ); 
            } 
        }  
        catch( InterruptedException e ) { } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
	private synchronized void sendMessage() throws InterruptedException, IOException {
		while(!messageQueue.isEmpty()){
			String outString = messageQueue.pop();
			buf = outString.getBytes();
			DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, 4000);
			s.send(out);
		}
		notify(); 
	}
	public synchronized void addMessage(String message) throws InterruptedException, IOException {
		messageQueue.add(message);
		notify(); 
	}
}
