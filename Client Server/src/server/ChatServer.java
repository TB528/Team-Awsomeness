package server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatServer {
  public static void main(String[] args) throws Exception {
    int PORT = 4000;
    byte[] buf = new byte[1000];
    DatagramPacket dgp = new DatagramPacket(buf, buf.length);
    DatagramSocket sk;
    
    String board = "";

    sk = new DatagramSocket(PORT);
    System.out.println("Server started");
    while (true) {
    	System.out.println("Listening on " + PORT);
      sk.receive(dgp);
      String rcvd = new String(dgp.getData(), 0, dgp.getLength()) + ", from address: "
          + dgp.getAddress() + ", port: " + dgp.getPort();
      System.out.println(rcvd);
      board = board + dgp.getPort() + ": " + new String(dgp.getData(), 0, dgp.getLength()) + "\n";
      
      String outMessage = board;
      buf = (outMessage).getBytes();
      DatagramPacket out = new DatagramPacket(buf, buf.length, dgp.getAddress(), dgp.getPort());
      sk.send(out);
    }
  }
}