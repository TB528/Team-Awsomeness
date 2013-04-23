package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginGUI extends JFrame {
	private static final long serialVersionUID = 1L;
  JTextField usernameTF;
  public LoginGUI() {
    JLabel usernameL=new JLabel("Enter your username");
    usernameTF=new JTextField(15);
    JButton submitB=new JButton("Submit");
    SubmitButtonHandler sbhandler=new SubmitButtonHandler();
    submitB.addActionListener(sbhandler);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Log In");
    setSize(400,200);
    setVisible(true);
    Container pane=getContentPane();
    pane.setLayout(new GridLayout(3,1));
    pane.add(usernameL);
    pane.add(usernameTF);
    pane.add(submitB);
  }
  public class SubmitButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      String username=usernameTF.getText();
      setVisible(false);
      ReadyGUI moveon=new ReadyGUI(username);
    }
  }
}
