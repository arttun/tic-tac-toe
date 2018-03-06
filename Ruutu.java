/**
 * Kuvaa pelialueen yksittäistä ruutua. Toimii konkreettisena graafisen käyttöliittymän painikkeena.
 * Pitää kirjaa ruudun tilasta ja sijainnista pelialueella.
 * 
 * @author Arttu Nieminen
 * @version 1.0
 */

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ruutu extends JButton {

	// ruudun tilaa kuvaavat vakiot
	
	/**
	 * Kuvaa sellaisen ruudun, jossa on nolla, tilaa.
	 */
	public static final int NOLLA = 0;
	
	/**
	 * Kuvaa sellaisen ruudun, jossa on risti, tilaa.
	 */
	public static final int RISTI = 1;
	
	/**
	 * Kuvaa tyhjän ruudun tilaa.
	 */
	public static final int TYHJA = 2;
		
	// ruudun mitat
	
	/**
	 * Ruudun sivun pituus.
	 */
	public static final int SIVU = 50;
	
	/**
	 * Ruudun koko, joka määräytyy sen sivun pituudesta.
	 */
	public static final Dimension KOKO = new Dimension(SIVU, SIVU);
	
	// ruudun ominaisuudet
	
	/**
	 * Ruudun tila. Sallitut arvot TYHJA, RISTI, NOLLA.
	 */
	private int tila;
	
	/**
	 * Ruudun x-koordinaatti pelialueella. 
	 * Sallitut arvot 0...pelialueen leveys-1.
	 */
	private int x;
	
	/**
	 * Ruudun y-koordinaatti pelialueella. 
	 * Sallitut arvot 0...pelialueen korkeus-1.
	 */
	private int y;
	
	/**
	 * Ruudukko, johon ruutu kuuluu.
	 */
	private Ruudukko ruudukko;
	
	/**
	 * Luo uuden alkutilaisen (tyhjän) ruudun, 
	 * jonka koordinaatit ovat parametrien mukaiset.
	 * Käytettäessä on huolehdittava siitä, 
	 * että ruudun koordinaatit ovat pelialueella.
	 * 
	 * @param x		ruudun x-koordinaatti, oltava epänegatiivinen
	 * @param y		ruudun y-koordinaatti, oltava epänegatiivinen
	 */	
	public Ruutu(int x, int y, Ruudukko ruudukko){
		super(new ImageIcon("grafiikka/tyhja.gif"));
		this.setSize(KOKO);
		this.setMinimumSize(KOKO);
		this.setMaximumSize(KOKO);
		this.setPreferredSize(KOKO);
		this.tila = TYHJA;
		this.x = x;
		this.y = y;
		this.ruudukko = ruudukko;
	}
	
	/**
	 * Hakee ruudun x-koordinaatin.
	 * 
	 * @return	ruudun x-koordinaatti
	 */
	public int haeX() {
		return this.x;
	}
	
	/**
	 * Hakee ruudun y-koordinaatin.
	 * 
	 * @return	ruudun y-koordinaatti
	 */
	public int haeY() {
		return this.y;
	}
	
	/**
	 * Hakee ruudun tilan.
	 * 
	 * @return	ruudun tilan kertova arvo
	 */	
	public int haeTila(){
		return this.tila;
	}
	
	/**
	 * Muuttaa ruudun tilan ja grafiikan halutun tilan mukaiseksi.
	 * Päivittää ruudukon täyttöasteen.
	 * 
	 * @param tila			arvo, joka kertoo ruudun uuden tilan; 
	 * 						sallitut arvot TYHJA, RISTI, NOLLA
	 */	
	public void asetaTila(int tila) {
		if (tila != Ruutu.TYHJA && tila != Ruutu.RISTI && tila != Ruutu.NOLLA)
			return;		// virheellinen tila
		
		if (this.tila == Ruutu.TYHJA && tila == Ruutu.RISTI)
			this.ruudukko.lisaaRuutu();
		else if (this.tila == Ruutu.TYHJA && tila == Ruutu.NOLLA)
			this.ruudukko.lisaaRuutu();
		else if (this.tila == Ruutu.RISTI && tila == Ruutu.TYHJA)
			this.ruudukko.vahennaRuutu();
		else if (this.tila == Ruutu.NOLLA && tila == Ruutu.TYHJA)
			this.ruudukko.vahennaRuutu();
		
		this.tila = tila;	
		
		if (tila == RISTI)
			this.setIcon(new ImageIcon("grafiikka/risti.gif"));
		if (tila == NOLLA)
			this.setIcon(new ImageIcon("grafiikka/nolla.gif"));
		if (tila == TYHJA)
			this.setIcon(new ImageIcon("grafiikka/tyhja.gif"));
	}
}
