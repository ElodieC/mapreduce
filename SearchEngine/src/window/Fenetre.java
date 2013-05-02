package window;


import grammar.Index2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import search.Search;


public class Fenetre extends JApplet {


	private static final long serialVersionUID = 1L;
	private JTextField entree;
	private JTextPane resultat;
	private Search toEvaluate;
	private String whereSearch; 

	public void init() {
		String chemindeClarisse = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/outputFiles/output";
		String cheminElodie = "/home/hduser/hadoopMR/outputFiles/output";
		//whereSearch=chemindeClarisse;
		whereSearch=cheminElodie;
		toEvaluate = new Search();

		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);


		JButton search = new JButton("Search");
		search.setOpaque(true);
		search.setBackground(new Color(255,255,0));


		resultat = new JTextPane();
		resultat.setEditable(false);
		resultat.setContentType("text/html");// <== because THIS line was commented I have passed all the
		JScrollPane scrollPane = new JScrollPane(resultat);// day to find how to put html in JTextpanel
		entree = new JTextField();				//"Dark Idiot" I am !!!!!!!

		fenetre.setLayout(new BorderLayout());
		add(scrollPane,"Center");

		JPanel recherche = new JPanel(new BorderLayout());
		recherche.add(entree, "North");
		JRootPane rootPane = getRootPane();
		rootPane.setDefaultButton(search);
		recherche.add(search, "Center");
		add(recherche,"North");

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toEvaluate = new Search();
				toEvaluate.expression = entree.getText().toLowerCase();
				Index2.build(whereSearch, toEvaluate);
				//We must find the place of the searched word to replace it in the JTextPane with 
				//the good case (in the example apache is in fact Apache)
				//int offset = resultTest.toLowerCase().indexOf(toEvaluate.expression);
				//We find the good written word word = Apache
				//String word = resultTest.substring(offset, offset+toEvaluate.expression.length());
				//we split the string test in 2 to put html for the searched word
				//that why we have to search the word apache with the right case,
				//else we have a beautiful exception
				
				//System.out.println("getMessage"+toEvaluate.seeker.getMessage());
				resultat.setText(toEvaluate.seeker.getMessage().toString());
			}
		});
	}

}