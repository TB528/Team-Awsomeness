package negotiate;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connection implements java.io.Serializable{
	private static final long serialVersionUID = 8484247106055780642L;
	private int port;
	private InetAddress ip;
	private String name;
	
	public Connection(){
		try {
			setPort(-1);
			setIp(InetAddress.getByAddress(new byte[] {(byte)0,(byte)0,(byte)0,(byte)0}));
			setName("John Doe");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection(int port, InetAddress ip){
		setPort(port);
		setIp(ip);
		setName("John Doe");
	}
	
	public Connection(int port, InetAddress ip, String name){
		setPort(port);
		setIp(ip);
		setName(name);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(Connection c){
		return c.getIp().equals(ip) && (c.getPort() == port);
	}
	public String toString(){
		return ip + ":" + port;
	}

}
