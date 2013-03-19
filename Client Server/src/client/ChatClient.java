package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ChatClient {

  public static void main(String[] args) throws Exception {
    DatagramSocket s = new DatagramSocket();
    byte[] buf = new byte[1000];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);

    InetAddress hostAddress = InetAddress.getByAddress(new byte[] {(byte)255,(byte)255,(byte)255,(byte)255});
    System.out.println(hostAddress);
    Scanner sc = new Scanner(System.in);
    while (true) {
    	System.out.print("Msg: ");
      String outMessage = sc.nextLine();
      //System.out.println(outMessage);
      if (outMessage.equals("bye"))
        break;
      String outString = outMessage;
      buf = outString.getBytes();

      DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, 4000);
      s.send(out);
      //System.out.println("Message sent");
      s.receive(dp);
      String rcvd = new String(dp.getData(), 0, dp.getLength());
      System.out.println(rcvd);
    }
  }
}