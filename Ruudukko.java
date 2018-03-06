/**
 * Kuvaa peliruudukkoa. 
 * Toimii konkreettisena graafisen käyttöliittymän komponenttina, 
 * joka luo pelialueen ruudut ja piirtää niistä koostuvan tietyn kokoisen ruudukon.
 * Tarkkailee käyttäjän klikkauksia ruudukossa 
 * ja lähettää hiiritapahtumat Peli-oliolle käsiteltäviksi.
 * 
 * @author Arttu Nieminen
 * @version 1.0
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Ruudukko extends JPanel implements MouseListener {

	/**
	 * Taulukko, joka sisältää ruudukon ruudut.
	 */
	private Ruutu[][] ruudut;
	
	/**
	 * Ruudukon leveys.
	 */
	private int leveys;
	
	/**
	 * Ruudukon korkeus.
	 */
	private int korkeus;
	
	/**
	 * Ruudukon epätyhjien ruutujen lukumäärä.
	 */
	private int tayttoLaskuri;
	
	/**
	 * Pelikerta, johon ruudukko liittyy.
	 */
	private Peli peli;
	
	/**
	 * Luo uuden peliruudukon.
	 * 
	 * @param leveys	ruudukon leveys
	 * @param korkeus	ruudukon korkeus
	 * @param peli		pelikerta, johon ruudukko liittyy
	 */
	public Ruudukko(int leveys, int korkeus, Peli peli) {
		if (leveys > 0)
			this.leveys = leveys;
		else
			this.leveys = Peli.LEVEYS;
		if (korkeus > 0)
			this.korkeus = korkeus;
		else
			this.korkeus = Peli.KORKEUS;
		this.tayttoLaskuri = 0;
		this.peli = peli;
		
		Dimension alueenKoko = new Dimension(this.leveys*Ruutu.SIVU, this.korkeus*Ruutu.SIVU);
		this.setLayout(new GridLayout(korkeus, leveys));
		this.setSize(alueenKoko);
		this.setPreferredSize(alueenKoko);
		this.setMinimumSize(alueenKoko);
		this.setMaximumSize(alueenKoko);
		this.ruudut = new Ruutu[this.korkeus][this.leveys];
		for(int i=0; i<this.korkeus; ++i) {
			for(int j=0; j<this.leveys; ++j) {
				this.ruudut[i][j] = new Ruutu(j, i, this);
				this.ruudut[i][j].addMouseListener(this);
				this.add(ruudut[i][j]);
			}
		}
	}
	
	/**
	 * Hakee ruudun, joka sijaitsee ruudukossa 
	 * parametrien ilmaisemissa koordinaateissa.
	 * Ruudukon origo (0,0) on vasen ylänurkka ja
	 * siitä oikealle ja alaspäin olevat koordinaatit ovat positiivisia.
	 * 
	 * @param x			ruudun x-koordinaatti (vaaka-akseli)
	 * @param y			ruudun y-koordinaatti (pystyakseli)
	 * @return			Ruutu, joka sijaitsee ruudukossa 
	 * 					parametrina annetuissa koordinaateissa. 
	 * 					Jos parametrit ovat virheelliset, 
	 * 					palauttaa ruudun, joka sijaitsee kohdassa (0,0).
	 */	
	public Ruutu haeRuutu(int x, int y) {
		if (0 <= x && x < this.leveys && 0 <= y && y < this.korkeus)
			return this.ruudut[y][x];
		else
			return this.ruudut[0][0];	
			// tämä ruutu on ainakin olemassa
	}
	
	/**
	 * Hakee ruudukon täyttöasteen.
	 * 
	 * @return	ruudukon epätyhjien ruutujen lukumäärä
	 */
	public int haeTayttoLaskuri() {
		return this.tayttoLaskuri;
	}
	
	/**
	 * Lisää ruudukon täyttölaskuria yhdellä.
	 */
	public void lisaaRuutu() {
		tayttoLaskuri++;
	}
	
	/**
	 * Vähentää ruudukon täyttölaskuria yhdellä.
	 */
	public void vahennaRuutu() {
		tayttoLaskuri--;
	}
	
	/**
	 * Käskee pelialueeseen liittyvän pelin 
	 * käsittelemään pelialueella ilmenneen hiiritapahtuman.
	 * 
	 * @param tapahtuma		pelialueella ilmennyt hiiritapahtuma
	 */
	public void mouseClicked(MouseEvent tapahtuma) {
		this.peli.kasitteleTapahtuma(tapahtuma);
	}

	/**
	 * Ei tee mitään. (MouseListener-rajapinnan vaatima metodi)
	 * 
	 * @param tapahtuma		pelialueella ilmennyt hiiritapahtuma
	 */
	public void mouseEntered(MouseEvent tapahtuma) {}

	/**
	 * Ei tee mitään. (MouseListener-rajapinnan vaatima metodi)
	 * 
	 * @param tapahtuma		pelialueella ilmennyt hiiritapahtuma
	 */
	public void mouseExited(MouseEvent tapahtuma) {}

	/**
	 * Ei tee mitään. (MouseListener-rajapinnan vaatima metodi)
	 * 
	 * @param tapahtuma		pelialueella ilmennyt hiiritapahtuma
	 */
	public void mousePressed(MouseEvent tapahtuma) {}

	/**
	 * Ei tee mitään. (MouseListener-rajapinnan vaatima metodi)
	 * 
	 * @param tapahtuma		pelialueella ilmennyt hiiritapahtuma
	 */
	public void mouseReleased(MouseEvent tapahtuma) {}
}
