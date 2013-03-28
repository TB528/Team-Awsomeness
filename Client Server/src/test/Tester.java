//this tests
package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

import negotiate.Connection;
import negotiate.ConnectionManager;

import send.recieve.Message;
import send.recieve.Transiever;

public class Tester {
	static InetAddress broadcast;
	static Connection broad;
	public static void main(String[] args) {
		try {
			broadcast = InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255});
			broad = new Connection(4000,broadcast);
			//System.out.print("BasicTransmissionTest: ");
			//System.out.println(basicTransmissionTest());
			//Thread.sleep(6000);
			System.out.print("connectionManagerTest: ");
			System.out.println(connectionManagerTest());
		} catch (UnknownHostException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static boolean basicTransmissionTest() throws UnknownHostException, InterruptedException{
		int matchCount = 0;
		
		Transiever t = new Transiever();
		t.start();
		LinkedList<Message> msgs = new LinkedList<Message>();
		LinkedList<Message> rmsgs = new LinkedList<Message>();
		msgs.add(new Message(broad,"chat","test 1"));
		msgs.add(new Message(4000,broadcast,"chat","test 2"));
		msgs.add(new Message(broad,"chat","test 3"));
		msgs.add(new Message(4000,broadcast,"chat","test 4"));
		msgs.add(new Message(broad,"chat","test 5"));
		msgs.add(new Message(4000,broadcast,"chat","test 6"));
		msgs.add(new Message(broad,"chat",123456));
		msgs.add(new Message(4000,broadcast,"chat",new Message(4000,broadcast,"chat","sub Message")));
		for(Message m : msgs)
			t.sendMsg(m);
		Thread.sleep(1000*msgs.size());
		rmsgs = t.getMsgs();
		for(Message m : msgs){
			for(Message r : rmsgs){
				if(m.equals(r)){
					matchCount++;
					//System.out.println(matchCount);
				}
			}
		}
		t.killAll();
		return matchCount == msgs.size();
	}
	public static boolean connectionManagerTest() throws InterruptedException, IOException{
		int matchCount = 0;
		
		ConnectionManager cm = new ConnectionManager("Test Name 1");
		cm.start();
		cm.sendMsg("Test chat message 1", "chat");
		Thread.sleep(2000);
		for(Connection c : cm.getConnections())
			System.out.println(c);
		for(Message m : cm.getMessages())
			m.print();
		
		cm.killAll();
		return true;
	}

}
