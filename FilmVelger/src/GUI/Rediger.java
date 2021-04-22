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

import klasser.Film;
import util.FilmarkivDB;

/**
 * Gui som gjør det mulig å redigere informasjonen på en film.
 * 
 * @author Mikael
 *
 */
public class Rediger extends JFrame {


	private static final long serialVersionUID = 1L;
	
	private JLabel l1, l2, l3, l4, l5, l6, l7, filler;
	private JTextField t1, t2, t3, t4, t5, t6, t7, info;
	private JButton sok, rediger;
	
	private FilmarkivDB filmarkiv;
	private Film aktuellFilm;
	private boolean funnet = false;
	
	public Rediger() {
		filmarkiv = new FilmarkivDB();
		filmarkiv.hentAlleFilmer();
		
		JPanel panel = new JPanel(new GridLayout(9, 2));
		
		l1 = new JLabel("Hvilken film skal redigeres?");
		t1 = new JTextField("Skriv tittel her..");
		t1.setEditable(true);
		filler = new JLabel("");
		sok = new JButton("Søk");
		
		
		l2 = new JLabel("Filmnr: ");
		l3 = new JLabel("Tittel: ");
		l4 = new JLabel("Utgivelsesaar: ");
		l5 = new JLabel("Sjanger: ");
		l6 = new JLabel("IMDB Rating: ");
		l7 = new JLabel("Har vi sett filmen?");
		
		t2 = new JTextField("");
		t3 = new JTextField("");
		t4 = new JTextField("");
		t5 = new JTextField("");
		t6 = new JTextField("");
		t7 = new JTextField("");
		
		info = new JTextField("");
		
		rediger = new JButton("Rediger filmen");
		
		t2.setEditable(false);
		t3.setEditable(true);
		t4.setEditable(true);
		t5.setEditable(true);
		t6.setEditable(true);
		t7.setEditable(true);
		info.setEditable(false);
		
		
		panel.add(l1);
		panel.add(t1);
		panel.add(filler);
		panel.add(sok);
		
		panel.add(l2);
		panel.add(t2);
		panel.add(l3);
		panel.add(t3);
		panel.add(l4);
		panel.add(t4);
		panel.add(l5);
		panel.add(t5);
		panel.add(l6);
		panel.add(t6);
		panel.add(l7);
		panel.add(t7);
		
		panel.add(info);
		panel.add(rediger);
		
		setContentPane(panel);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setTitle("Filmarkiv");
		setSize(450, 200);
		setLocation(700, 400);
		
		
		sok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tittel = t1.getText();
				
				int sjekk = filmarkiv.finnFilmer(tittel);
				
				if (sjekk == 1) {
					aktuellFilm = filmarkiv.finnFilm(tittel);
					t2.setText(aktuellFilm.getFilmNr() + "");
					t3.setText(aktuellFilm.getTittel());
					t4.setText(aktuellFilm.getUtgivelsesaar() + "");
					t5.setText(aktuellFilm.getSjanger());
					t6.setText(aktuellFilm.getRating() + "");
					t7.setText(aktuellFilm.isSett() + "");
					info.setText("Du kan nå redigere informasjonen");
					funnet = true;
				} else if (sjekk >= 2) {
					info.setText("Fant flere filmer, spesifiser søk.");
					funnet = false;
				} else {
					info.setText("Fant ikke filmen, forsøk igjen");
					funnet = false;
				}
				
			}
			
		});
		
		rediger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (!funnet) {
					info.setText("Ingen film å redigere!");
				} else {
					aktuellFilm.setTittel(t3.getText());
					aktuellFilm.setUtgivelsesaar(Integer.parseInt(t4.getText()));
					aktuellFilm.setSjanger(t5.getText());
					aktuellFilm.setRating(Double.parseDouble(t6.getText()));
					if(t7.getText().equals("ja")) {
						aktuellFilm.setSett(true);
					} else {
						aktuellFilm.setSett(false);
					}
					filmarkiv.oppdaterFilm(aktuellFilm);
					filmarkiv.oppdaterLokaltArkiv();
					
					t1.setText("");
					info.setText("Filmen er redigert. Velg en ny?");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					t7.setText("");
				}
				
				
			}
		});
	}
}
