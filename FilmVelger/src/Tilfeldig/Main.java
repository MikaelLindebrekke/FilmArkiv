package Tilfeldig;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {

		FilmListe filmListe = new FilmListe();
		
		String fil = "C:\\Users\\Mikael Lindebrekke\\git\\FilmVelger\\FilmVelger\\src\\Tilfeldig\\Filmer.txt";

		Fil.lesFraFil(filmListe, fil);
		
		Object[] menyValg = {"Se film!", "Legge til film!", "Se info om filmlisten"};
		
		int valg = JOptionPane.showOptionDialog(null, "Hva vil dere gjøre?", 
				"Filmrouletten", JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, menyValg, menyValg[0]);
		if (valg == 0) {
			String film = filmListe.velgRandom();
			JOptionPane.showMessageDialog(null, "Filmen vi skal se er: \n\n" + film + "\n\nGod fornøyelse!");
			filmListe.fjernSett(film);

		} else if (valg == 1){
			int leggTilFlere = 0; 
			while (leggTilFlere == 0) {
				filmListe.leggTil(JOptionPane.showInputDialog(null, "Tittel på filmen som skal legges til: "));
				leggTilFlere = JOptionPane.showConfirmDialog(null, "Filmen er lagt til i listen, legge til flere?",
						"Bekreftelse", JOptionPane.YES_NO_OPTION);
			}
			JOptionPane.showMessageDialog(null, "Programmet er avsluttet.");
		} else {
			JOptionPane.showMessageDialog(null, "Antall filmer i listen: " + filmListe.antallFilmer() + 
					"\n\n" + filmListe.toString());
		}

		Fil.skrivTilFil(filmListe, fil);
	}

}
