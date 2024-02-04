import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class myFrame extends JFrame implements ActionListener {

    //declaration
    JButton valider = new JButton("Envoyer");
    JButton quitter= new JButton("Annuler");

    myFrame(){

        this.setTitle("Ma premiere fenetre");
        this.setSize(900, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        JLabel lb_titre = new JLabel("cv");
        lb_titre.setForeground(Color.WHITE);
        lb_titre.setOpaque(true);
        lb_titre.setBackground(Color.GREEN);
        lb_titre.setFont(new Font("Arial",Font.BOLD ,32));
        lb_titre.setHorizontalAlignment(JLabel.CENTER);
        lb_titre.setPreferredSize(new Dimension(300,50));
        this.add(lb_titre);


        JPanel psouth = new JPanel();
        psouth.setLayout(new FlowLayout());
        psouth.add(valider);
        psouth.add(quitter);
        psouth.setBackground(Color.pink);
        this.add(lb_titre,BorderLayout.NORTH);
        this.add(psouth,BorderLayout.SOUTH);

        this.setVisible(true);

        //events
        quitter.addActionListener(this);
        valider.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==quitter) {
            System.exit(0);
        }else{
            //creation des fichiers
            File f = new File("cv.html");
            try {
                FileWriter fw = new FileWriter(f);
                fw.write("<h1>cv</h1>");
                fw.close();//sauvegarde du fichier
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this,"saved");
        }
    }
}
