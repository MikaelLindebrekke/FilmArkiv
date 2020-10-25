package Tilfeldig;

public class FilmListe {

	String filmer[];
	int antall = 0;

	public FilmListe() {
	}
	
	public void setUp(int x) {
		filmer = new String[x];
	}
	
	public void leggTil(String film) {
		if (filmer.length == antall) 
			utvid();
		
		filmer[antall] = film;
		antall++;
	}

	private void utvid() {
		String[] hjelpeTabell = new String[filmer.length * 2];

		for (int i = 0; i < filmer.length; i++) {
			hjelpeTabell[i] = filmer[i];
		}
		filmer = hjelpeTabell;
	}

	public String velgRandom() {
		int p = (int) (Math.random() * antall);
		return filmer[p];

	}

	public int antallFilmer() {
		return antall;
	}

	public String[] hentFilmer() {
		return filmer;
	}
	public void fjernSett(String film) {
		int i = 0; 
		while (i < antall) { 
			if (filmer[i].equals(film)) {
				antall--; 
				for (int j = i; j < antall; j++) {
					filmer[j] = filmer[j + 1];
				}
				i = antall;
			}
			i++;
		}
	}
	public String toString() {
		
		String alleFilmer = filmer[0]; 
		
		for(int i = 1; i < antall; i++) {
			if (i % 3 == 0) {
				alleFilmer += "\n" + filmer[i];
			} else {
				alleFilmer += "   -   " + filmer[i];
			}
				

		}
		
		return alleFilmer;
				
				
	}

}
