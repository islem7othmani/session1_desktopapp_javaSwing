import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test extends JFrame implements ActionListener  {
     JButton button;
   Test() {

      ImageIcon imageIcon = new ImageIcon("src/images/cv.png");
      button = new JButton();
      button.setText("start");
      button.addActionListener(this);


      JLabel label = new JLabel();
      label.setText("Create your CV \uD83D\uDC7E");
      label.setIcon(imageIcon);
      label.setHorizontalTextPosition(JLabel.CENTER);
      label.setVerticalTextPosition(JLabel.TOP);
      label.setForeground(Color.lightGray);
      label.setFont(new Font("MV Boli",Font.PLAIN,20));
      label.setIconTextGap(25);
      label.setBackground(Color.WHITE);
      label.setOpaque(true);
      label.setVerticalAlignment(JLabel.CENTER);
      label.setHorizontalAlignment((JLabel.CENTER));


      JPanel welcome = new JPanel();
      welcome.setBackground(Color.BLUE);
      welcome.setBounds(0,0,100,100);
      welcome.add(label);


       this.setTitle("form for CV");
       this.setSize(300,300);
       ImageIcon image = new ImageIcon("src/images/icon.jpg");
       this.setIconImage(image.getImage());
       this.getContentPane().setBackground(Color.BLACK);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
       this.add(welcome,BorderLayout.NORTH);
       this.add(button,BorderLayout.SOUTH);


   }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("hi");
//creation des fichiers
            File f = new File("cv.html");
            try {
                FileWriter fw = new FileWriter(f);
                fw.write("<h1>cv</h1>" +
                        "<p>hfth</p>");
                fw.close();//sauvegarde du fichier
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this,"saved");
        }
    }



}
