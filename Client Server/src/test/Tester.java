package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

import send.recieve.Message;
import send.recieve.Transiever;

public class Tester {
	static InetAddress broadcast;
	public static void main(String[] args) {
		try {
			broadcast = InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255});
			System.out.print("BasicTransmissionTest: ");
			System.out.println(basicTransmissionTest());
		} catch (UnknownHostException | InterruptedException e) {
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
		msgs.add(new Message(4000,broadcast,"chat","test 1"));
		msgs.add(new Message(4000,broadcast,"chat","test 2"));
		msgs.add(new Message(4000,broadcast,"chat","test 3"));
		msgs.add(new Message(4000,broadcast,"chat","test 4"));
		msgs.add(new Message(4000,broadcast,"chat","test 5"));
		msgs.add(new Message(4000,broadcast,"chat","test 6"));
		for(Message m : msgs)
			t.sendMsg(m);
		Thread.sleep(6000);
		rmsgs = t.getMsgs();
		for(Message m : msgs){
			for(Message r : rmsgs){
				if(m.equals(r)){
					matchCount++;
					//System.out.println(matchCount);
				}
			}
		}
				
		return matchCount == 6;
	}

}
