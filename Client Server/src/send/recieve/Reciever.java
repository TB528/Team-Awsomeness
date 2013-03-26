package send.recieve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import negotiate.Connection;

public class Reciever extends Thread{
	private int PORT = 4000;
	private byte[] buf;
	private DatagramPacket dgp;
	private DatagramSocket sk;    
	private LinkedList<Message> messageQueue;
	private boolean isAlive;
	
	public void run() {
		isAlive = true;
		buf = new byte[1000];
		dgp = new DatagramPacket(buf, buf.length);
		messageQueue = new LinkedList<Message>();
		
		
        try { 
        	sk = new DatagramSocket(PORT);
            while ( isAlive ) {
            	sk.receive(dgp);
            	Message temp = (Message) Serializer.deserialize(dgp.getData());
            	temp.setSource(new Connection(dgp.getPort(),dgp.getAddress()));
            	messageQueue.add(temp);
            	//System.out.println("*** Reciever ***");
            	//temp.print();
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
	public LinkedList<Message> getMessages() throws InterruptedException, IOException {
		LinkedList<Message> messages = new LinkedList<Message>();
		int size = messageQueue.size();
		Message temp;
		for(int i = 0; i < size; i++){
			temp = messageQueue.pop();
			//System.out.println("*** getMessages ***");
        	//temp.print();
			messages.addLast(temp);
		}
		return messages;
	}
	public void printBoard(){
		for(Message m : messageQueue)
			System.out.println(m);
	}
	public void kill(){
		isAlive = false;
	}
}
