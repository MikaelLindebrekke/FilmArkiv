package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import klasser.Film;
import util.FilmarkivDB;

/**
 * Velger en vilkårlig film ifra arkivet.
 * Denne klassen har vært hovedmotivasjonen for å lage dette lille prosjektet. 
 * 
 * @author Mikael
 *
 */
public class Velgfilm extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTextField valgtFilm;
	private JButton spinbtn, seFilm;
	private JLabel velkommen;
	
	private FilmarkivDB filmarkiv;
	
	public Velgfilm() {
		filmarkiv = new FilmarkivDB();
		filmarkiv.hentAlleFilmer();
		
		JPanel toppPanel = new JPanel(new FlowLayout());
		JPanel midtPanel = new JPanel(new FlowLayout());
		JPanel bunnPanel = new JPanel(new FlowLayout());
		
		velkommen = new JLabel("Velkommen til Film Roulette");
		toppPanel.add(velkommen);
		
		valgtFilm = new JTextField("...", 15);
		valgtFilm.setFont(valgtFilm.getFont().deriveFont(Font.PLAIN, 15f));
		valgtFilm.setEditable(false);
		midtPanel.add(valgtFilm);
		
		spinbtn = new JButton("Spin rouletten!");
		midtPanel.add(spinbtn);
		
		seFilm = new JButton("Se film.");
		bunnPanel.add(seFilm);
		
		setLayout(new GridLayout(3, 1));
		add(toppPanel);
		add(midtPanel);
		add(bunnPanel);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setTitle("Filmarkiv");
		setSize(450, 200);
		setLocation(700, 400);
		

		spinbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Film f = filmarkiv.velgRandom();
				valgtFilm.setText(f.getTittel() + "");
			}
		});
		
		seFilm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				filmarkiv.markerSett(valgtFilm.getText());
				CloseFrame();
			}
		});
		
	}
	public void CloseFrame() {
		super.dispose();
	}
}
