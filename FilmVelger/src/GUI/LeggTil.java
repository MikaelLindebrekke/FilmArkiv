package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.FilmarkivDB;

/**
 * Brukes til å legge inn en film i arkivet. 
 * @author Mikael
 *
 */
public class LeggTil extends JFrame {


	private static final long serialVersionUID = 1L;
	
	private JLabel l1, l2, l3, l4, l5, filler;
	private JTextField t1, t2, t3, t4, t5;
	private JButton leggtil;
	
	private FilmarkivDB filmarkiv;
	
	public LeggTil() {
		
		filmarkiv = new FilmarkivDB();
		filmarkiv.hentAlleFilmer();
		
		JPanel panel = new JPanel(new GridLayout(7, 2));
		
		l1 = new JLabel("Filmnr: ");
		l2 = new JLabel("Tittel: ");
		l3 = new JLabel("Utgivelsesaar: ");
		l4 = new JLabel("Sjanger: ");
		l5 = new JLabel("IMDB Rating: ");

		t1 = new JTextField(filmarkiv.size() + 1 + "");
		t2 = new JTextField("");
		t3 = new JTextField("");
		t4 = new JTextField("");
		t5 = new JTextField("");
		
		t1.setEditable(false);
		t2.setEditable(true);
		t3.setEditable(true);
		t4.setEditable(true);
		t5.setEditable(true);
		
		panel.add(l1);
		panel.add(t1);
		panel.add(l2);
		panel.add(t2);
		panel.add(l3);
		panel.add(t3);
		panel.add(l4);
		panel.add(t4);
		panel.add(l5);
		panel.add(t5);
		
		filler = new JLabel("");
		panel.add(filler);
		
		leggtil = new JButton("Legg til filmen");
		panel.add(leggtil);
		
		setContentPane(panel);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setTitle("Filmarkiv");
		setSize(450, 200);
		setLocation(700, 400);
		
		leggtil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				boolean sett = false;
				filmarkiv.leggTilFilm(
						Integer.parseInt(t1.getText()), 
						t2.getText(), 
						Integer.parseInt(t3.getText()), 
						t4.getText(), 
						Double.parseDouble(t5.getText()),
						sett);
				filmarkiv.oppdaterLokaltArkiv();
				t1.setText(filmarkiv.size() + 1 + "");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
			}
		});
		
		
	}
}
