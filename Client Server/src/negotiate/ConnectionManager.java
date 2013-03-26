package negotiate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

import send.recieve.Message;
import send.recieve.Transiever;

public class ConnectionManager extends Thread{
	private LinkedList<Connection> connections;
	private Transiever trans;
	private boolean isAlive;
	private String name = "John doe";
	private LinkedList<Message> messageQueue;
	public ConnectionManager(String name){
		setLocalName(name);
	}
	public void run() {
		trans = new Transiever();
		isAlive = true;
		messageQueue = new LinkedList<Message>();
		connections = new LinkedList<Connection>();
		while (isAlive) {
			if(!trans.isAlive()){
				trans.start();
				ping();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flushMsgQueue();
			}
			messageQueue.addAll(flushMsgQueue());
		}
	}
	public void setLocalName(String name){
		this.name = name;
	}
	public void sendMsg(Message msg){
		trans.sendMsg(Security.encrypt(msg));
	}
	public void sendMsg(Object data, String type){
		Message temp;
		if(connections != null && connections.size() > 0)
			for(Connection c : connections)
				try {
					temp = new Message(c,type,data);
					//System.out.println("*** sendMsg ***");
					//temp.print();
					sendMsg(temp);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		else
			try {
				sendMsg(new Message(new Connection(4000,InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255})),type,data));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public synchronized LinkedList<Message> flushMsgQueue(){
		LinkedList<Message> msgs = new LinkedList<Message>();
		msgs = trans.getMsgs();
		int size = msgs.size();
		try {
			Message temp = new Message();
			for( int i = 0; i < size; i++){
				temp = msgs.poll();
				msgs.add(Security.decrypt(temp));
			}
			negotiateNewConnections(msgs);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msgs;
	}
	public LinkedList<Connection> getConnections(){
		return connections;
	}
	public void killAll(){
		if(trans.isAlive())
			trans.killAll();
		isAlive = false; 
	}
	public synchronized LinkedList<Message> getMessages() throws InterruptedException, IOException {
		LinkedList<Message> messages = new LinkedList<Message>();
		int size = messageQueue.size();
		for(int i = 0; i < size; i++)
			messages.addLast(messageQueue.pop());
		return messages;
	}
	
	private synchronized void negotiateNewConnections(LinkedList<Message> msgs) throws UnknownHostException{
		for(Message m : msgs){
			//System.out.println("*** negotiateNewConnections ***");
			//m.print();
			if(isNewConnection(m))
				if(m.getType().compareTo("pong") == 0)
					connections.add(new Connection(4000,m.getSource().getIp(),m.getData().toString()));
				else
					connections.add(m.getSource());
			if(m.getType().compareTo("ping") == 0)
				sendMsg(new Message(new Connection(4000,InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255})),"pong",name));
		}
	}
	private synchronized boolean isNewConnection(Message msg){
		boolean isNew = true;
		for(Connection c : connections)
			if(msg.getSource().equals(c))
				isNew = false;
		return isNew;				
	}
	private synchronized void ping(){
		try {
			Message temp = new Message(new Connection(4000,InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255})),"ping",name);
			//System.out.println("*** ping ***");
			//temp.print();
			sendMsg(temp);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
