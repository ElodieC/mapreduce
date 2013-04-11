import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * Exemple de code qui donne un bouton au centre
 * @author elodie
 * Pour l'utiliser clic droit sur le projet export
 * jar file
 * Creer une page HTML avec pour code ceci
 * <html>
	<title>The Hello, World Applet</title>
		<applet code="AppletHTML.class" archive="applet.jar">
		If your browser was Java-enabled, a "Hello, World"
		message would appear here.
		</applet>
	</html>
	avec AppletHTML nom de la classe qui extends de JApplet et applet.jar nom de l'archive exporté
	la page html et le .jar doivent se trouver dans le meme repertoire
	lancer la page html avec firefox 
 */
public class Fenetre extends JApplet
{
  private JTextField entree;
  private JLabel resultat;

  public void init()
  {
   
    this.entree = new JTextField();
    JButton search = new JButton("Search");
    this.resultat = new JLabel();

    setLayout(new BorderLayout());
    /*fenetre.getContentPane().*/add(search, "South");
    /*fenetre.getContentPane().*/add(this.entree, "North");
    /*fenetre.getContentPane().*/add(this.resultat, "Center");

    search.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String recherche = Fenetre.this.entree.getText();
        Fenetre.this.resultat.setText(recherche);
      }
    });
  }
 
}


/*public class AppletHTML extends JApplet {
	
	public void init(){
		setLayout(new BorderLayout());
		JButton button = new JButton("Hello");
		JTextField field = new JTextField();
		add(button,"Center");
		add(field,"North");
	}

}*/
