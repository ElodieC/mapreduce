import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
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

public class AppletHTML extends JApplet {
	
	public void init(){
		setLayout(new BorderLayout());
		JButton button = new JButton("Hello");
		add(button,"Center");
	}

}
