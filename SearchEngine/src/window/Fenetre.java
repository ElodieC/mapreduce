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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import search.Search;


public class Fenetre extends JApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField entree;
	private JTextPane resultat;
	private Search toEvaluate;
	private String whereSearch;
	
	public void init() {
		whereSearch="/home/hduser/Téléchargements/resultat";
		
		toEvaluate = new Search();
		
		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);
		entree = new JTextField();
		JButton search = new JButton("Search");
		search.setOpaque(true);
		
		search.setBackground(new Color(255,255,0));
		resultat = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(resultat);
		
/*
		Container c = fenetre.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(resultat,"Center");
		
		JPanel recherche = new JPanel();
		recherche.add(entree);
		recherche.add(search);
		c.add(recherche,"North");*/

		fenetre.setLayout(new BorderLayout());
		add(scrollPane,"Center");
		
		JPanel recherche = new JPanel(new BorderLayout());
		recherche.add(entree, "North");
		recherche.add(search, "Center");
		add(recherche,"North");
		
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toEvaluate = new Search();
				toEvaluate.expression = entree.getText();
					
				try {
					Index.build(whereSearch, toEvaluate);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				resultat.setText(toEvaluate.seeker.message);
			}
		});
	}

}