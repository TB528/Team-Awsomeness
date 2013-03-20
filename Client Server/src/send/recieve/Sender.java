package send.recieve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Sender extends Thread{
	DatagramSocket s;
	byte[] buf = new byte[1024];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    
    private LinkedList<Message> messageQueue;
    
	public void run() {
		messageQueue = new LinkedList<Message>();
        try { 
			s = new DatagramSocket();
            while ( true ) { 
                sendMessages(); 
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
	private synchronized void sendMessages() throws InterruptedException, IOException {
		while(!messageQueue.isEmpty()){
			Message outMessage = messageQueue.pop();
			outMessage.setTimeStampNow();
			buf = Serializer.serialize(outMessage);
			DatagramPacket out = new DatagramPacket(buf, buf.length, outMessage.getDestinationIp(), outMessage.getDestinationPort());
			s.send(out);
		}
		notify(); 
	}
	public synchronized void addMessage(Message message) throws InterruptedException, IOException {
		messageQueue.add(message);
		notify(); 
	}
}
