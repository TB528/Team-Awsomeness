package send.recieve;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import negotiate.Connection;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = -3575129505474011591L;
	private Object data;
	private Connection source;
	private Connection destination;
	private String type;
	

	public Message() throws UnknownHostException{
		data = "";
		source = new Connection();
		destination = new Connection();
		type = "null";
		setTimeStampNow();
	}
	
	public Message(Object data) throws UnknownHostException{
		this.data = data;
		source = new Connection();
		destination = new Connection();
		type = "null";
		setTimeStampNow();
	}
	
	public Message(String data) throws UnknownHostException{
		this.data = data;
		source = new Connection();
		destination = new Connection();
		type = "message";
		setTimeStampNow();
	}
	public Message(int destPort, InetAddress destIp, String type, Object data) throws UnknownHostException{
		this.data = data;
		source = new Connection();
		destination = new Connection(destPort,destIp);
		this.type = type;
		setTimeStampNow();
	}
	public Message(int sourcePort,InetAddress sourceIp, int destPort, InetAddress destIp, String type, Object data) throws UnknownHostException{
		this.data = data;
		source = new Connection(sourcePort,sourceIp);
		destination = new Connection(destPort,destIp);
		this.type = type;
		setTimeStampNow();
	}
	public Message(Connection destination, String type, Object data) throws UnknownHostException{
		this.data = data;
		source = new Connection();
		this.destination = destination;
		this.type = type;
		setTimeStampNow();
	}
	public Message(Connection source, Connection destination, String type, Object data) throws UnknownHostException{
		this.data = data;
		this.source = source;
		this.destination = destination;
		this.type = type;
		setTimeStampNow();
	}
	
	public Connection getSource() {
		return source;
	}

	public void setSource(Connection source) {
		this.source = source;
	}

	public Connection getDestination() {
		return destination;
	}

	public void setDestination(Connection destination) {
		this.destination = destination;
	}
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	private String timeStamp;
	
	public void setTimeStampNow(){
		timeStamp = new Date().toString();
	}
	public String toString(){
		return  "From: " + source.getName() + " at " + source.getIp() + ":" + source.getPort() + "\n" +
				"To: " + destination.getName() + " at " + destination.getIp() + ":" + destination.getPort() + "\n" +
				"type: " + type + "\n" +
				"TimeStamp: " + timeStamp + "\n" +
				"Msg: " + data.toString();
				
	}
	public void print(){
		System.out.println(toString());
	}
	public boolean equals(Message m){/*
		System.out.println("******************************");
		System.out.println("******************************");
		System.out.println("******************************");
		print();
		System.out.println("******************************");
		m.print();*/
		boolean isData = m.data.toString().compareTo(data.toString()) == 0;
		boolean istime = m.timeStamp.compareTo(timeStamp) == 0;
		boolean isType = true;
		try{
			isType = m.type.compareTo(type) == 0;
		}catch(NullPointerException e){}
		
		//System.out.println("******************************" + ((isData) && (istime) && (isType)) + "******************************");
		
		return (isData) && (istime) && (isType);
	}
}
