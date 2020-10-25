package Tilfeldig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Fil {

	public static void lesFraFil(FilmListe fl, String filnavn) {

		try {

			BufferedReader innfil = new BufferedReader(new FileReader(filnavn));

			// Leser den første posten som er antall info-poster

			String linje = innfil.readLine();
			int n = Integer.parseInt(linje);
			fl.setUp(n);
			// Leser hver linje som en film
			for (int i = 0; i < n; i++) {
				String film = innfil.readLine();
				fl.leggTil(film);
			}
			innfil.close();

		} catch (FileNotFoundException unntak) {// arver fra IOE.. må stå først
												// hvis ikke vil unntaket for IOException skygge
			System.out.println("Finner ikke filen " + filnavn);

		} catch (IOException e) {
			System.out.println("Feil ved lesing av fil: " + e);

		}

	}

	public static void skrivTilFil(FilmListe fl, String filnavn) {
		try {

			PrintWriter utfil = new PrintWriter(new FileWriter(filnavn, false));

			int antall = fl.antallFilmer();
			// Skriver foerst ut antall ansatt-info-er på første linje
			utfil.println(antall);

			String[] liste = fl.hentFilmer();
			
			for (int j = 0; j < antall; j++) {
				utfil.println(liste[j]);
			}
		
			utfil.close();
			
		} catch (IOException e) {
			System.out.println("Feil på skriving til fil : " + e);
		}
	}
}
