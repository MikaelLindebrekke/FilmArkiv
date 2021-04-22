package GUI;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import klasser.Film;
import util.FilmarkivDB;

/**
 * Viser litt diverse statestikk om filmarkivet
 * 
 * @author Mikael
 */
public class Statestikk extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l1, l2, l3, l4, l5, l6;
	private JTextField t1, t2, t3, t4, t5, t6;

	private FilmarkivDB filmarkiv;

	public Statestikk() {
		filmarkiv = new FilmarkivDB();
		filmarkiv.hentAlleFilmer();

		JPanel statPanel = new JPanel(new GridLayout(6, 2));

		l1 = new JLabel("Antall filmer totalt: ");
		l2 = new JLabel("Antall som er sett: ");
		l3 = new JLabel("Antall som ikke er sett: ");
		l4 = new JLabel("Gjennomsnittlig rating: ");
		l5 = new JLabel("Beste rating: ");
		l6 = new JLabel("Dårligste rating: ");

		List<Film> liste = filmarkiv.getList();
		int sett = 0, ikkeSett = 0;
		double snitt = 0, min = 10.0, max = 0.0;

		for (Film f : liste) {
			if (f.isSett()) {
				sett++;
			} else {
				ikkeSett++;
			}
			snitt += f.getRating();
			min = Math.min(f.getRating(), min);
			max = Math.max(f.getRating(), max);
		}
		snitt = snitt / liste.size();

		t1 = new JTextField(liste.size() + "");
		t2 = new JTextField(sett + "");
		t3 = new JTextField(ikkeSett + "");
		t4 = new JTextField(String.format("%.2f", snitt));
		t5 = new JTextField(max + "");
		t6 = new JTextField(min + "");

		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);

		statPanel.add(l1);
		statPanel.add(t1);
		statPanel.add(l2);
		statPanel.add(t2);
		statPanel.add(l3);
		statPanel.add(t3);
		statPanel.add(l4);
		statPanel.add(t4);
		statPanel.add(l5);
		statPanel.add(t5);
		statPanel.add(l6);
		statPanel.add(t6);

		setContentPane(statPanel);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setTitle("Filmarkiv");
		setSize(450, 200);
		setLocation(700, 400);

	}

}
