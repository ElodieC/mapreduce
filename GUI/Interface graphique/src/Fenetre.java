import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Fenetre extends JApplet {
	private JTextField entree;
	private JLabel resultat;
	
	public void init() {
		
		JFrame fenetre = new JFrame();
		entree = new JTextField();
		JButton search = new JButton("Search");
		resultat = new JLabel();
		
		fenetre.setLayout(new BorderLayout());
		add(search,"South");
		add(entree,"North");
		add(resultat,"Center");
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String recherche = entree.getText();
				resultat.setText(recherche);
			}
		});
	}

}
