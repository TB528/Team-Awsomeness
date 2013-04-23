package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ResultsGUI extends JFrame {
	private static final long serialVersionUID = 4L;
  String category1,category2,category3,category4,category5,category6,category7,category8,category9,category10,category11,category12,username;
  public ResultsGUI(String x,String c1,String c2,String c3,String c4,String c5,String c6,String c7,String c8,String c9,String c10,String c11,String c12) {
    category1=c1;
    category2=c2;
    category3=c3;
    category4=c4;
    category5=c5;
    category6=c6;
    category7=c7;
    category8=c8;
    category9=c9;
    category10=c10;
    category11=c11;
    category12=c12;
    username=x;
    JLabel scoreL=new JLabel("You scored "+"score");
    JLabel placeL=new JLabel("You Placed "+"place");
    JButton playagainB=new JButton("Play Again?");
    JButton exitB=new JButton("Exit");
    PlayButtonHandler pbhandler=new PlayButtonHandler();
     playagainB.addActionListener(pbhandler);
     ExitButtonHandler ebhandler=new ExitButtonHandler();
     exitB.addActionListener(ebhandler);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setTitle("Results");
     setSize(400,200);
     setVisible(true);
     Container pane=getContentPane();
     pane.setLayout(new GridLayout(2,2));
     pane.add(scoreL);
     pane.add(placeL);
     pane.add(playagainB);
     pane.add(exitB);
  }
  public class PlayButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      ReadyGUI nextgame=new ReadyGUI(username);
    }
  }
  public class ExitButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }
}