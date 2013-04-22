package window;

import grammar.Index;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import search.Search;


public class Fenetre extends JApplet {
	
	private JTextField entree;
	private JLabel resultat;
	private Search toEvaluate;
	private String whereSearch;
	
	public void init() {
		whereSearch="/home/hduser/Téléchargements/line";
		
		toEvaluate = new Search();
		
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
				toEvaluate.expression = entree.getText();
				
				try {
					Index.build(whereSearch, toEvaluate);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	
				resultat.setText(toEvaluate.result);
			}
		});
	}

}