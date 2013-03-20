package send.recieve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Reciever extends Thread{
	private int PORT = 4000;
	private byte[] buf;
	private DatagramPacket dgp;
	private DatagramSocket sk;    
	private LinkedList<Message> messageQueue;

	public void run() {
		buf = new byte[1000];
		dgp = new DatagramPacket(buf, buf.length);
		messageQueue = new LinkedList<Message>();
		
        try { 
        	sk = new DatagramSocket(PORT);
            while ( true ) {
            	sk.receive(dgp);
            	Message temp = (Message) Serializer.deserialize(dgp.getData());
            	temp.setSourceIp(dgp.getAddress());
            	temp.setSourcePort(dgp.getPort());
            	messageQueue.add(temp);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
	public synchronized LinkedList<Message> getMessages() throws InterruptedException, IOException {
		LinkedList<Message> messages = new LinkedList<Message>();
		int size = messageQueue.size();
		for(int i = 0; i < size; i++)
			messages.addLast(messageQueue.pop());
		return messages;
	}
	public synchronized void printBoard(){
		for(Message m : messageQueue)
			System.out.println(m);
	}
}
