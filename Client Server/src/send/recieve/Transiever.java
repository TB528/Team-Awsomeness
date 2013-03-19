package send.recieve;

import java.util.Scanner;

public class Transiever {
	 public static void main(String[] args) throws Exception {
		 Sender sender = new Sender();
		 Reciever reciever = new Reciever();
		 sender.start();
		 reciever.start();
		 Scanner sc = new Scanner(System.in);
		 while (true) {
			 System.out.print("Msg: ");
			 String outMessage = sc.nextLine();		     
			 sender.addMessage(outMessage); 
			 reciever.printBoard();
		 }
	 }

}
