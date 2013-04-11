import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Fenetre extends JApplet {
	
	private JTextField entree;
	private JLabel resultat;
	
	public void init() {
		
		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);
		entree = new JTextField();
		JButton search = new JButton("Search");
		search.setOpaque(true);
		  /*try {
		    Image img = ImageIO.read(getClass().getResource("orange.jpg"));
		    search.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }*/
		search.setBackground(new Color(255,255,0));
		resultat = new JLabel();
		
/*
		Container c = fenetre.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(resultat,"Center");
		
		JPanel recherche = new JPanel();
		recherche.add(entree);
		recherche.add(search);
		c.add(recherche,"North");*/

		fenetre.setLayout(new BorderLayout());
		add(resultat,"Center");
		
		JPanel recherche = new JPanel(new BorderLayout());
		recherche.add(entree, "North");
		recherche.add(search, "Center");
		add(recherche,"North");
		
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String r = entree.getText();
				resultat.setText(r);
			}
		});
	}

}
