package klasser;

/**
 * Klasse som representerer en Film med standard Get og Set metoder
 * 
 * @author Mikael
 */
public class Film {

	int filmNr;
	String tittel;
	int utgivelsesaar;
	String sjanger;
	double rating;
	boolean sett;

	public Film() {
	}

	public Film(int filmNr, String tittel, int utgivelsesaar, String sjanger, double rating, boolean sett) {
		super();
		this.filmNr = filmNr;
		this.tittel = tittel;
		this.utgivelsesaar = utgivelsesaar;
		this.sjanger = sjanger;
		this.rating = rating;
		this.sett = sett;
	}

	public int getFilmNr() {
		return filmNr;
	}

	public void setFilmNr(int filmNr) {
		this.filmNr = filmNr;
	}

	public String getTittel() {
		return tittel;
	}

	public void setTittel(String tittel) {
		this.tittel = tittel;
	}

	public int getUtgivelsesaar() {
		return utgivelsesaar;
	}

	public void setUtgivelsesaar(int utgivelsesaar) {
		this.utgivelsesaar = utgivelsesaar;
	}

	public String getSjanger() {
		return sjanger;
	}

	public void setSjanger(String sjanger) {
		this.sjanger = sjanger;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isSett() {
		return sett;
	}

	public void setSett(boolean sett) {
		this.sett = sett;
	}

	@Override
	public String toString() {
		return "Filmnr: " + filmNr + "\nTittel: " + tittel + "\nUtgivelsesaar: " + utgivelsesaar + "\nSjanger: "
				+ sjanger + "\nRating: " + rating + "\nEr sett: " + sett;
	}

}
