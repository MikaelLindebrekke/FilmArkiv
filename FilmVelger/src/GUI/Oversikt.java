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
 * Viser en fullstendig oversikt over alle Filmer som er ført opp i arkivet.
 * 
 * @author Mikael
 *
 */
public class Oversikt extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l1, l2, l3, l4, l5, l6;

	private List<Film> alleFilmer;

	public Oversikt() {

		FilmarkivDB filmarkiv = new FilmarkivDB();
		filmarkiv.hentAlleFilmer();
		alleFilmer = filmarkiv.getList();

		JPanel oversikt = new JPanel(new GridLayout(0, 6, 5, 5));

		l1 = new JLabel("Filmnr: ");
		l2 = new JLabel("Tittel: ");
		l3 = new JLabel("Utgivelsesaar: ");
		l4 = new JLabel("Sjanger: ");
		l5 = new JLabel("IMDB Rating: ");
		l6 = new JLabel("Har vi sett filmen?: ");

		oversikt.add(l1);
		oversikt.add(l2);
		oversikt.add(l3);
		oversikt.add(l4);
		oversikt.add(l5);
		oversikt.add(l6);

		for (Film f : alleFilmer) {
			oversikt.add(new JLabel(f.getFilmNr() + ""));
			oversikt.add(new JLabel(f.getTittel() + ""));
			oversikt.add(new JLabel(f.getUtgivelsesaar() + ""));
			oversikt.add(new JLabel(f.getSjanger() + ""));
			oversikt.add(new JLabel(f.getRating() + ""));
			oversikt.add(new JLabel(f.isSett() + ""));

		}

		setContentPane(oversikt);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		setTitle("Filmarkiv");
		setExtendedState(MAXIMIZED_BOTH);
	}

}
