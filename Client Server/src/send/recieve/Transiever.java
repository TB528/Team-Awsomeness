package send.recieve;

import java.io.IOException;
import java.util.LinkedList;

public class Transiever extends Thread {
	Sender sender = new Sender();
	Reciever reciever = new Reciever();
	private boolean isAlive;
	
	public void run() {
		isAlive = true;
		 while (isAlive) {
			 if(!reciever.isAlive())
				 reciever.start();
			 if(!sender.isAlive())
				 sender.start();
		 }
	}
	public void sendMsg(Message msg){
		try {
			//System.out.println("*** transiever sendMsg ***");
			//msg.print();
			sender.addMessage(msg);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public LinkedList<Message> getMsgs(){
		LinkedList<Message> msgs = new LinkedList<Message>();
		try {
			msgs = reciever.getMessages();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgs;
	}
	public void killAll(){
		if(reciever.isAlive())
			 reciever.kill();
		if(sender.isAlive())
			 sender.kill();
		isAlive = false; 
	}

}
