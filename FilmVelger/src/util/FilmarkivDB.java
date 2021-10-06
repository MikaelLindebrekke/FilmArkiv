package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import klasser.Film;

/**
 * Klasse som håndtere CRUD i forbindelse med databasen som ligger på en PostgresSQL server.
 * 
 * @author Mikael
 */
public class FilmarkivDB {

	// Database informasjon - Passord er sensurert bort
	String jdbcUrl = "jdbc:postgresql://data1.hib.no:5433/h239630?user=h239630&password=********&currentSchema=filmarkiv";

	List<Film> arkiv;

	public FilmarkivDB() {
		arkiv = new ArrayList<Film>();
	}

	/**
	 * Legger til en ny film i databasen
	 * 
	 * @param filmnr
	 * @param tittel
	 * @param utgivelsesaar
	 * @param sjanger
	 * @param rating
	 * @param sett
	 */
	public void leggTilFilm(int filmnr, String tittel, int utgivelsesaar, String sjanger, double rating, boolean sett) {

		try (Connection conn = DriverManager.getConnection(jdbcUrl)) {

			String sql = "INSERT INTO film (filmnr, tittel, utgivelsesaar, sjanger, rating, sett) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, filmnr);
			statement.setString(2, tittel);
			statement.setInt(3, utgivelsesaar);
			statement.setString(4, sjanger);
			statement.setDouble(5, rating);
			statement.setBoolean(6, sett);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Henter ut alle filmer som er lagret i databasen.
	 */
	public void hentAlleFilmer() {

		try (Connection conn = DriverManager.getConnection(jdbcUrl)) {

			String sql = "SELECT * FROM Film";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Film film = new Film();
				film.setFilmNr(result.getInt(1));
				film.setTittel(result.getString(2));
				film.setUtgivelsesaar(result.getInt(3));
				film.setSjanger(result.getString(4));
				film.setRating(result.getDouble(5));
				film.setSett(result.getBoolean(6));
				arkiv.add(film);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Oppdatere alle innganger i databasen.
	 */
	public void oppdaterDatabase() {

		try (Connection conn = DriverManager.getConnection(jdbcUrl)) {

			String sql = "UPDATE Film SET filmnr=?, tittel=?, utgivelsesaar=?, sjanger=?, rating=?, sett=?";

			PreparedStatement statement = conn.prepareStatement(sql);

			for (Film f : arkiv) {
				statement.setInt(1, f.getFilmNr());
				statement.setString(2, f.getTittel());
				statement.setInt(3, f.getUtgivelsesaar());
				statement.setString(4, f.getSjanger());
				statement.setDouble(5, f.getRating());
				statement.setBoolean(6, f.isSett());
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Oppdaterer en gitt film.
	 * 
	 * @param film
	 */
	public void oppdaterFilm(Film film) {
		try (Connection conn = DriverManager.getConnection(jdbcUrl)) {

			String sql = "UPDATE Film SET tittel=?, utgivelsesaar=?, sjanger=?, rating=?, sett=? WHERE filmnr=?";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, film.getTittel());
			statement.setInt(2, film.getUtgivelsesaar());
			statement.setString(3, film.getSjanger());
			statement.setDouble(4, film.getRating());
			statement.setBoolean(5, film.isSett());
			statement.setInt(6, film.getFilmNr());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sletter en film med gitt filmnr.
	 * 
	 * @param filmnr
	 */
	public void slettFilm(int filmnr) {

		try (Connection conn = DriverManager.getConnection(jdbcUrl)) {

			String sql = "DELETE FROM Film WHERE filmnr=?";

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, filmnr);

			statement.executeUpdate();

			oppdaterLokaltArkiv();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finner en film med gitt tittel på film.
	 * 
	 * @param tittel
	 * @return
	 */
	public Film finnFilm(String tittel) {
		Film film = null;

		for (Film f : arkiv) {
			if (f.getTittel().toLowerCase().contains(tittel.toLowerCase())) {
				film = f;
			}
		}

		return film;
	}
	
	public int finnFilmer(String tittel) {
		int antall = 0;
		for (Film f : arkiv) {
			if (f.getTittel().toLowerCase().contains(tittel.toLowerCase())) {
				antall++;
			}
		}
		
		return antall;
	}

	/**
	 * Markerer en film med gitt tittel som sett.
	 * 
	 * @param tittel
	 */
	public void markerSett(String tittel) {
		Film f = finnFilm(tittel);
		f.setSett(true);
		oppdaterFilm(f);
	}

	/**
	 * En metode som velger en tilfeldig film ifra arkivet som ikke er sett. 
	 * 
	 * @return
	 */
	public Film velgRandom() {
		
		List<Film> ikkeSett = new ArrayList<Film>();
		for (Film f : arkiv) {
			if(!f.isSett()) {
				ikkeSett.add(f);
			}
		}
		
		int p = (int) (Math.random() * ikkeSett.size());
		
		return ikkeSett.get(p);
	}

	/**
	 * Oppdatere den lokale listen av filmer med oppdatert informasjon fra
	 * databasen.
	 */
	public void oppdaterLokaltArkiv() {
		arkiv.clear();
		hentAlleFilmer();
	}

	public List<Film> getList() {
		return arkiv;
	}

	public int size() {
		return arkiv.size();
	}

}
