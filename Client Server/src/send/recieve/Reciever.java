package send.recieve;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Reciever extends Thread{
	private int PORT = 4000;
	private byte[] buf;
	private DatagramPacket dgp;
	private DatagramSocket sk;    
	private String board = "";

	public void run() {		
		buf = new byte[1000];
		dgp = new DatagramPacket(buf, buf.length);
		
        try { 
        	sk = new DatagramSocket(PORT);
            while ( true ) { 
            	 sk.receive(dgp);
                 String rcvd = dgp.getAddress() + ": " + dgp.getPort() + ":  \"" + new String(dgp.getData(), 0, dgp.getLength()) + "\"";
                 //System.out.println(rcvd);
                 board = board + dgp.getPort() + ": " + new String(dgp.getData(), 0, dgp.getLength()) + "\n";
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
	public String getBoard() throws InterruptedException, IOException {
		return board;
	}
	public void printBoard(){
		System.out.println(board);
	}
}
