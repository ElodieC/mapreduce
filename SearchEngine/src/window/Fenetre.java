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

/**
 * Applet du programme, réccupère l'entrée utilisation dans un premier JTextField
 * et réécrit les résultats en dessous
 * @author Corbel Elodie, M'ghari Kevin, Renou Clarisse
 * @version 2.0
 * @see Page web
 */
public class Fenetre extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField entree;
	private JTextPane resultat;
	private Search toEvaluate;
	private String whereSearch; 
	
	/**
	 * Methode d'initialisation de l'applet
	 * whereSearch représente le chemin où sont stockés les fichiers txt ou l'on veut
	 * lancer les recherches
	 * 
	 */
	public void init() {
		String chemindeClarisse = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/outputFiles/output";
		String cheminElodie = "/home/hduser/hadoopMR/outputFiles/output";
		//whereSearch=chemindeClarisse;
		whereSearch=cheminElodie;
		
		Logger.createLogger();
		Logger.addInLog("Chemin du fichier d'index :");
		Logger.addInLog(whereSearch);
	
		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);


		JButton search = new JButton("Search");
		search.setOpaque(true);
		search.setBackground(new Color(255,255,0));


		resultat = new JTextPane();
		resultat.setEditable(false);
		resultat.setContentType("text/html");
		JScrollPane scrollPane = new JScrollPane(resultat);
		entree = new JTextField();				

		fenetre.setLayout(new BorderLayout());
		add(scrollPane,"Center");

		JPanel recherche = new JPanel(new BorderLayout());
		recherche.add(entree, "North");
		JRootPane rootPane = getRootPane();
		rootPane.setDefaultButton(search);
		recherche.add(search, "Center");
		add(recherche,"North");


		/**
		 * Action sur le bouton Search
		 * Construit la hashmap par rapport au ficher d'Index
		 * Puis traite le résultat
		 */
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logger.addInLog("Mot entré : ");
				Logger.addInLog(entree.getText());
				toEvaluate = new Search(entree.getText().toLowerCase());
				Index2.build(whereSearch, toEvaluate);
				
				resultat.setText(toEvaluate.seeker.getMessage().toString());
				Logger.addInLog("Sortie : ");
				Logger.addInLog(toEvaluate.seeker.getMessage().toString());
			}
		});
	}

}