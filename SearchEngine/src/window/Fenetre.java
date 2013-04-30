package window;

import grammar.Index;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import search.Search;
import search.Seeker;


public class Fenetre extends JApplet {

	/**
	 * est ce qu'il y aurait quelqu'un qui pourrait tenter d'enlever
	 * le jpanel recherche pour mettre directement dans la jframe fenetre. 
	 * pour voir si c'est mieux. J'ai l'impression qu'on peut pas redimentionner 
	 * et puis c'est assez étrange de faire comme ça
	 */
	private static final long serialVersionUID = 1L;
	private JTextField entree;
	private JTextPane resultat;
	private Search toEvaluate;
	private String whereSearch;

	public void init() {
		String chemindeClarisse = "/media/Data_/Bibliotheque/Documents/INSA/Etudes pratiques/mapreduce/hadoopMR/outputFiles/output";
		whereSearch=chemindeClarisse;
		toEvaluate = new Search();

		JFrame fenetre = new JFrame();
		fenetre.setPreferredSize(new Dimension(400, 600));
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		fenetre.setVisible(true);
	

		JButton search = new JButton("Search");
		search.setOpaque(true);
		search.setBackground(new Color(255,255,0));
		search.setPreferredSize(new Dimension(20, 20));


		resultat = new JTextPane();
		resultat.setEditable(false);
		resultat.setContentType("text/html");// <== because THIS line was commented I have passed all the
		JScrollPane scrollPane = new JScrollPane(resultat);// day to find how to put html in JtExtpane
		entree = new JTextField();				//"Dark Idiot" I am !!!!!!!
		entree.setPreferredSize(new Dimension(100, 30));

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
				//toEvaluate.expression = entree.getText().toLowerCase()
				toEvaluate.expression = "apache".toLowerCase();// Here is the pseudo word 
																//typed in the field toLowerCase
											//to find the word even if we are wrong in the cases
				try {
					Index.build(whereSearch, toEvaluate);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//TODO ignorer la casse
				//toEvaluate.seeker.message <== dans les () 
				/*"Official site of the Apache project to provide an open-source " +
						"implementation of frameworks for reliable, scalable, " +
						"distributed computing and data storage."*/
			
				
				//The string TEST
				String resultTest = "Official site of the Apache project to provide an open-source " +
						"implementation of frameworks for reliable, scalable, " +
						"distributed computing and data storage.";
				
				//We must find the place of the searched word to replace it in the JTextPane with 
				//the good case (in the example apache is in fact Apache)
				int offset = resultTest.toLowerCase().indexOf(toEvaluate.expression);
				//We find the good written word word = Apache
				String word = resultTest.substring(offset, offset+toEvaluate.expression.length());
				//we split the string test in 2 to put html for the searched word
				//that why we have to search the word apache with the right case,
				//else we have a beautiful exception
				String split[] = resultTest.split(word);
				//insert the word with right case in bold
				resultat.setText("<html>"+split[0]+"<strong>"+word+
						"</strong>"+split[1]+"</html>");				
			}
		});
	}

}