/**
 * Kuvaa yhtä pelikertaa ja toimii pelin ohjausoliona.
 * Sisältää tiedot pelikerran ominaisuuksista, pelin tilanteesta 
 * sekä tiettyyn pelikertaan liittyvistä 
 * graafisen käyttöliittymän komponenteista.
 * 
 * @author Arttu Nieminen
 * @version 1.0
 */

import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class Peli {

	/**
	 * Ruudukon oletusleveys.
	 */
	public static final int LEVEYS = 3;
	
	/**
	 * Ruudukon oletuskorkeus.
	 */
	public static final int KORKEUS = 3;
	
	/**
	 * Muodostettavan suoran oletuspituus.
	 */
	public static final int SUORANPITUUS = 3;
	
	/**
	 * Pelikerran ruudukon leveys.
	 */
	private int leveys;
	
	/**
	 * Pelikerran ruudukon korkeus.
	 */
	private int korkeus;
	
	/**
	 * Muodostettavan suoran pituus pelikerrassa.
	 */
	private int suoranPituus;
	
	/**
	 * Pelikerran ruudukko.
	 */
	private Ruudukko ruudukko;
	
	/**
	 * Pelikerran ilmoitus/ohjetekstit.
	 */
	private JLabel ilmoitus;
	
	/**
	 * Pelaajan pelimerkki pelikerrassa.
	 */
	private int pelaajanMerkki;
	
	/**
	 * Tietokoneen pelimerkki pelikerrassa.
	 */
	private int tietokoneenMerkki;
	
	/**
	 * Tietokonevastustaja.
	 */
	private Tietokone tietokone;
	
	/**
	 * True, jos peli on kesken; 
	 * pelin päätyttyä false.
	 */
	private boolean peliKesken;
	
	/**
	 * Luo uuden pelikerran.
	 */
	public Peli() {
		this.leveys = LEVEYS;
		this.korkeus = KORKEUS;
		this.ruudukko = new Ruudukko(this.leveys, this.korkeus, this);
		this.suoranPituus = SUORANPITUUS; 	
		this.tietokone = new Tietokone(this);
		this.peliKesken = true;
		this.ilmoitus = new JLabel("Pelaa!");
	}
	
	/**
	 * Lisätään pelille tieto pelaajan ja tietokoneen pelimerkkivalinnoista 
	 * ja ilmoitetaan tietokoneen saama pelimerkki tietokoneellekin.
	 * 
	 * @param merkki	pelaajan pelimerkki
	 */
	public void asetaPelaajanMerkki(int merkki) {
		if (merkki == Ruutu.RISTI) {
			this.pelaajanMerkki = Ruutu.RISTI;
			this.tietokoneenMerkki = Ruutu.NOLLA;
			this.tietokone.asetaTietokoneenMerkki(Ruutu.NOLLA);
			return;
		}
		else if (merkki == Ruutu.NOLLA){
			this.pelaajanMerkki = Ruutu.NOLLA;
			this.tietokoneenMerkki = Ruutu.RISTI;
			this.tietokone.asetaTietokoneenMerkki(Ruutu.RISTI);
			// tietokone sai ristin ja siis aloittaa:
			this.tietokone.aloita();
		}
	}
	
	/**
	 * Hakee pelaajan pelimerkin.
	 * 
	 * @return	pelaajan pelimerkki
	 */
	public int haePelaajanMerkki() {
		return this.pelaajanMerkki;
	}
	
	/**
	 * Hakee tietokoneen pelimerkin.
	 * 
	 * @return	tietokoneen pelimerkki
	 */
	public int haeTietokoneenMerkki() {
		return this.tietokoneenMerkki;
	}
	
	/**
	 * Palauttaa parametrina annetun pelimerkin vastamerkin.
	 * 
	 * @param merkki	pelimerkki (Ruutu.RISTI tai Ruutu.NOLLA)
	 * @return			parametrina annetun merkin vastamerkki
	 */
	public int haeVastaMerkki(int merkki) {
		if (merkki == Ruutu.RISTI)
			return Ruutu.NOLLA;
		if (merkki == Ruutu.NOLLA)
			return Ruutu.RISTI;
		return Ruutu.TYHJA; 	// virheellinen syöte
	}
	
	/**
	 * Hakee peliruudukon.
	 * 
	 * @return	peliruudukko
	 */
	public Ruudukko haeRuudukko() {
		return this.ruudukko;
	}
	
	/**
	 * Hakee pelialueen leveyden.
	 * 
	 * @return	pelialueen leveys
	 */
	public int haeLeveys() {
		return this.leveys;
	}
	
	/**
	 * Hakee pelialueen korkeuden.
	 * 
	 * @return	pelialueen korkeus
	 */
	public int haeKorkeus() {
		return this.korkeus;
	}
	
	/**
	 * Hakee pelikertaan liittyvän suoran pituuden.
	 * 
	 * @return	suoran pituus
	 */
	public int haeSuoranPituus() {
		return this.suoranPituus;
	}
	
	/**
	 * Hakee pelin ilmoitukset/ohjeet.
	 * 
	 * @return	pelin ilmoituksen/ohjeen sisältävä JLabel
	 */
	public JLabel haeIlmoitus() {
		return this.ilmoitus;
	}
	
	/**
	 * Asettaa tekstin pelin ilmoitukset/ohjeet näyttävään JLabeliin.
	 * 
	 * @param ilmoitus	asetettava teksti
	 */
	public void ilmoita(String ilmoitus) {
		this.ilmoitus.setText(ilmoitus);
	}
	
	/**
	 * Tarkistaa, muodostiko viimeksi asetettu pelimerkki suoran.
	 * 
	 * @param merkki	tarkistettava pelimerkki, 
	 * 					mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @param ruutu		tarkistettava ruutu, johon merkki asetettiin
	 * @return 			true, jos suora muodostui; muuten false
	 */	
	public boolean voittiko(int merkki, Ruutu ruutu) {		
		if (ruutu.haeTila() != merkki)
			return false;	
			// edes tarkistettavassa ruudussa ei ole oikeaa merkkiä
		if (tarkistaVaakarivit(merkki, ruutu))
			return true;		
		if (tarkistaPystyrivit(merkki, ruutu))
			return true;		
		if (tarkistaLaskevatVinorivit(merkki, ruutu))
			return true;		
		if (tarkistaNousevatVinorivit(merkki, ruutu))
			return true;		
		return false;	// suoraa ei löytynyt
	}
	
	/**
	 * Tarkistaa, muodostiko viimeksi asetettu pelimerkki vaakasuoran.
	 * 
	 * @param merkki	tarkistettava pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @param ruutu		tarkistettava ruutu, johon merkki asetettiin
	 * @return 			true, jos suora muodostui; muuten false
	 */	
	private boolean tarkistaVaakarivit(int merkki, Ruutu ruutu) {
		int x = ruutu.haeX();
		int y = ruutu.haeY();
		int laskuri = 1;
		
		for (int i=1; x+i<this.leveys; ++i) {
			if(this.ruudukko.haeRuutu(x+i, y).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		for (int i=1; x-i>=0; ++i) {
			if(this.ruudukko.haeRuutu(x-i, y).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		return false;
	}
	
	/**
	 * Tarkistaa, muodostiko viimeksi asetettu pelimerkki pystysuoran.
	 * 
	 * @param merkki	tarkistettava pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @param ruutu		tarkistettava ruutu, johon merkki asetettiin
	 * @return 			true, jos suora muodostui; muuten false
	 */	
	private boolean tarkistaPystyrivit(int merkki, Ruutu ruutu) {
		int x = ruutu.haeX();
		int y = ruutu.haeY();
		int laskuri = 1;
		
		for (int i=1; y+i<this.korkeus; ++i) {
			if(this.ruudukko.haeRuutu(x, y+i).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		for (int i=1; y-i>=0; ++i) {
			if(this.ruudukko.haeRuutu(x, y-i).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		return false;
	}
	
	/**
	 * Tarkistaa, muodostiko viimeksi asetettu pelimerkki 
	 * vasemmalta oikealle laskevan vinosuoran.
	 * 
	 * @param merkki	tarkistettava pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @param ruutu		tarkistettava ruutu, johon merkki asetettiin
	 * @return 			true, jos suora muodostui; muuten false
	 */	
	private boolean tarkistaLaskevatVinorivit(int merkki, Ruutu ruutu) {
		int x = ruutu.haeX();
		int y = ruutu.haeY();
		int laskuri = 1;
		
		for (int i=1; x+i<this.leveys && y+i<this.korkeus; ++i) {
			if(this.ruudukko.haeRuutu(x+i, y+i).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		for (int i=1; x-i>=0 && y-i>=0; ++i) {
			if(this.ruudukko.haeRuutu(x-i, y-i).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		return false;
	}
	
	/**
	 * Tarkistaa, muodostiko viimeksi asetettu pelimerkki 
	 * vasemmalta oikealle nousevan vinosuoran.
	 * 
	 * @param merkki	tarkistettava pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @param ruutu		tarkistettava ruutu, johon merkki asetettiin
	 * @return 			true, jos suora muodostui; muuten false
	 */	
	private boolean tarkistaNousevatVinorivit(int merkki, Ruutu ruutu) {
		int x = ruutu.haeX();
		int y = ruutu.haeY();
		int laskuri = 1;
		
		for (int i=1; x+i<this.leveys && y-i>=0; ++i) {
			if(this.ruudukko.haeRuutu(x+i, y-i).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		for (int i=1; x-i>=0 && y+i<this.korkeus; ++i) {
			if(this.ruudukko.haeRuutu(x-i, y+i).haeTila() == merkki) {
				laskuri++;
				if (laskuri == this.suoranPituus)
					return true;		// suora löytyi
			}
			else
				break;
		}
		return false;
	}
	
	/**
	 * Tarkistaa, onko ruudukko täynnä.
	 * 
	 * @return 			true, jos ruudukko on täynnä; muuten false
	 */	
	public boolean onkoTaynna() {
		if (this.ruudukko.haeTayttoLaskuri() == this.leveys * this.korkeus)
			return true;
		return false;
	}
	
	/**
	 * Ohjaa pelin kulkua käyttäjän pelialueella antamien 
	 * hiirikomentojen (klikkausten) perusteella. 
	 * 
	 * @param tapahtuma		käyttäjän hiirellä aiheuttama tapahtuma
	 */	
	public void kasitteleTapahtuma(MouseEvent tapahtuma) {
		Object aiheuttaja = tapahtuma.getComponent();
		if (this.peliKesken) {
			if (aiheuttaja instanceof Ruutu) {
				Ruutu ruutu = (Ruutu)aiheuttaja;
				if(ruutu.haeTila() == Ruutu.TYHJA) {
					ruutu.asetaTila(this.pelaajanMerkki);
					if (this.voittiko(this.pelaajanMerkki, ruutu)) {
						this.peliKesken = false;
						this.ilmoita("VOITIT PELIN!");
						return;
					}
					if (this.onkoTaynna()) {
						this.peliKesken = false;
						this.ilmoita("TASAPELI!");
						return;
					}
					Ruutu tietokoneenRuutu = this.tietokone.pelaa();
					if (this.voittiko(this.tietokoneenMerkki, tietokoneenRuutu)) {
						this.peliKesken = false;
						this.ilmoita("HÄVISIT PELIN!");
						return;
					}
					if (this.onkoTaynna()) {
						this.peliKesken = false;
						this.ilmoita("TASAPELI!");
						return;
					}
				}
			}
		}
	}
}
