package send.recieve;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = -3575129505474011591L;
	private Object data;
	private int sourcePort;
	private InetAddress sourceIp;
	private int destinationPort;
	private InetAddress destinationIp;
	private String type;
	

	public Message() throws UnknownHostException{
		data = null;
		sourcePort = -1;
		sourceIp = InetAddress.getByAddress(new byte[] {(byte)0,(byte)0,(byte)0,(byte)0});
		destinationPort = -1;
		destinationIp = sourceIp;
		type = "null";
		setTimeStampNow();
	}
	
	public Message(Object data) throws UnknownHostException{
		this.data = data;
		sourcePort = -1;
		sourceIp = InetAddress.getByAddress(new byte[] {(byte)0,(byte)0,(byte)0,(byte)0});
		destinationPort = -1;
		destinationIp = sourceIp;
		type = "null";
		setTimeStampNow();
	}
	
	public Message(String data) throws UnknownHostException{
		this.data = data;
		sourcePort = -1;
		sourceIp = InetAddress.getByAddress(new byte[] {(byte)0,(byte)0,(byte)0,(byte)0});
		destinationPort = -1;
		destinationIp = sourceIp;
		type = "message";
		setTimeStampNow();
	}
	
	public Message(int sourcePort,InetAddress sourceIp,Object data) throws UnknownHostException{
		this.data = data;
		this.sourcePort = sourcePort;
		this.sourceIp = sourceIp;
		destinationPort = -1;
		destinationIp = sourceIp;
		type = "null";
		setTimeStampNow();
	}
	
	public Message(int destPort, InetAddress destIp, String type, Object data) throws UnknownHostException{
		this.data = data;
		sourcePort = -1;
		sourceIp = InetAddress.getByAddress(new byte[] {(byte)0,(byte)0,(byte)0,(byte)0});
		this.destinationPort = destPort;
		this.destinationIp = destIp;
		this.type = type;
		setTimeStampNow();
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
	 * @return the sourcePort
	 */
	public int getSourcePort() {
		return sourcePort;
	}

	/**
	 * @param sourcePort the sourcePort to set
	 */
	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}

	/**
	 * @return the sourceIp
	 */
	public InetAddress getSourceIp() {
		return sourceIp;
	}

	/**
	 * @param sourceIp the sourceIp to set
	 */
	public void setSourceIp(InetAddress sourceIp) {
		this.sourceIp = sourceIp;
	}

	/**
	 * @return the destinationPort
	 */
	public int getDestinationPort() {
		return destinationPort;
	}

	/**
	 * @param destinationPort the destinationPort to set
	 */
	public void setDestinationPort(int destinationPort) {
		this.destinationPort = destinationPort;
	}

	/**
	 * @return the destinationIp
	 */
	public InetAddress getDestinationIp() {
		return destinationIp;
	}

	/**
	 * @param destinationIp the destinationIp to set
	 */
	public void setDestinationIp(InetAddress destinationIp) {
		this.destinationIp = destinationIp;
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
		return  "From: " + sourceIp + ":" + sourcePort + "\n" +
				"To: " + destinationIp + ":" + destinationPort + "\n" +
				"type: " + type + "\n" +
				"TimeStamp: " + timeStamp + "\n" +
				"Msg: " + data.toString();
				
	}
	public void print(){
		System.out.println(toString());
	}
	public boolean equals(Message m){
		return (m.data.toString().compareTo(data.toString()) == 0) && (m.timeStamp.compareTo(timeStamp) == 0) && (m.type.compareTo(type) == 0);
	}
}
