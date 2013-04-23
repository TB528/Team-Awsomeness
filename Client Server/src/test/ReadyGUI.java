package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ReadyGUI extends JFrame {
	private static final long serialVersionUID = 3L;
  String username;
    public ReadyGUI(String x) {
     username=x;
     JLabel readyL=new JLabel("Are you ready "+username+"?");
     JButton readyB=new JButton("Ready");
     ReadyButtonHandler rbhandler=new ReadyButtonHandler();
     readyB.addActionListener(rbhandler);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setTitle("Ready Screen");
     setSize(400,200);
     setVisible(true);
     Container pane=getContentPane();
     pane.setLayout(new GridLayout(2,1));
     pane.add(readyL);
     pane.add(readyB);
    }
    public class ReadyButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      GameGUI moveon=new GameGUI(username);
      //go to other method here
    }
  }
}
