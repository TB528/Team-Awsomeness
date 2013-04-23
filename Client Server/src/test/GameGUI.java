package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
public class GameGUI extends JFrame {
	private static final long serialVersionUID = 2L;
  int b=180;
  JTextField Category1TF;
  JTextField Category2TF;
  JTextField Category3TF;
  JTextField Category4TF;
  JTextField Category5TF;
  JTextField Category6TF;
  JTextField Category7TF;
  JTextField Category8TF;
  JTextField Category9TF;
  JTextField Category10TF;
  JTextField Category11TF;
  JTextField Category12TF;
  String category1;
  String category2;
  String category3;
  String category4;
  String category5;
  String category6;
  String category7;
  String category8;
  String category9;
  String category10;
  String category11;
  String category12;
  JLabel seconds=new JLabel("Remaining Time: "+b);
  Timer timer;
  String username;
  boolean done=false;
  public GameGUI(String x) {
     username=x;
     JLabel usernameL=new JLabel(username+":");
     JLabel category1L=new JLabel("Category 1");
     Category1TF=new JTextField(15);
     JLabel category2L=new JLabel("Category 2");
     Category2TF=new JTextField(15);
     JLabel category3L=new JLabel("Category 3");
     Category3TF=new JTextField(15);
     JLabel category4L=new JLabel("Category 4");
     Category4TF=new JTextField(15);
     JLabel category5L=new JLabel("Category 5");
     Category5TF=new JTextField(15);
     JLabel category6L=new JLabel("Category 6");
     Category6TF=new JTextField(15);
     JLabel category7L=new JLabel("Category 7");
     Category7TF=new JTextField(15);
     JLabel category8L=new JLabel("Category 8");
     Category8TF=new JTextField(15);
     JLabel category9L=new JLabel("Category 9");
     Category9TF=new JTextField(15);
     JLabel category10L=new JLabel("Category 10");
     Category10TF=new JTextField(15);
     JLabel category11L=new JLabel("Category 11");
     Category11TF=new JTextField(15);
     JLabel category12L=new JLabel("Category 12");
     Category12TF=new JTextField(15);
     Time te=new Time(b);
     timer=new Timer(1000,te);
     JButton enterB=new JButton("Enter Answers");
     EnterButtonHandler ebhandler=new EnterButtonHandler();
     enterB.addActionListener(ebhandler);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setTitle("Game Card");
     setSize(400,730);
     setVisible(true);
     Container pane=getContentPane();
     pane.setLayout(new GridLayout(29,1));
     pane.add(usernameL);
     pane.add(category1L);
     pane.add(Category1TF);
     pane.add(category2L);
     pane.add(Category2TF);
     pane.add(category3L);
     pane.add(Category3TF);
     pane.add(category4L);
     pane.add(Category4TF);
     pane.add(category5L);
     pane.add(Category5TF);
     pane.add(category6L);
     pane.add(Category6TF);
     pane.add(category7L);
     pane.add(Category7TF);
     pane.add(category8L);
     pane.add(Category8TF);
     pane.add(category9L);
     pane.add(Category9TF);
     pane.add(category10L);
     pane.add(Category10TF);
     pane.add(category11L);
     pane.add(Category11TF);
     pane.add(category12L);
     pane.add(Category12TF);
     pane.add(seconds);
     pane.add(enterB);
     timer.start();
     //////////////////////////////////////////////////////
  }
  public class EnterButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      setvariables();
      done=true;
      ResultsGUI nextwindow=new ResultsGUI(username,category1,category2,category3,category4,category5,category6,category7,category8,category9,category10,category11,category12);
    }
  }
  public class Time implements ActionListener {
    int count;
    public Time (int b) {
      count=b;
    }
    public void actionPerformed(ActionEvent te) {
      b--;
      if (b>=1) {
      seconds.setText("Remaining Time: "+b);
    }
    else {
      setVisible(false);
      if (done==false) {
      setvariables();
      ResultsGUI nextwindow=new ResultsGUI(username,category1,category2,category3,category4,category5,category6,category7,category8,category9,category10,category11,category12);
      }
  timer.stop();
    }
  }
}
  public void setvariables() {
    category1=Category1TF.getText();
    category2=Category2TF.getText();
    category3=Category3TF.getText();
    category4=Category4TF.getText();
    category5=Category5TF.getText();
    category6=Category6TF.getText();
    category7=Category7TF.getText();
    category8=Category8TF.getText();
    category9=Category9TF.getText();
    category10=Category10TF.getText();
    category11=Category11TF.getText();
    category12=Category12TF.getText();
  }
}