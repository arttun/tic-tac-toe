/**
 * Toteuttaa ristinollapelin graafisen käyttöliittymäikkunan.
 * 
 * @author Arttu Nieminen
 * @version 1.0
 */

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ristinolla extends JFrame implements ActionListener {
	
	/**
	 * Pelikerta.
	 */
	private Peli peli;
	
	/**
	 * Uusi peli -navigointipainike.
	 */
	private JButton uusipeli;
	
	/**
	 * Lopeta-navigointipainike.
	 */
	private JButton lopeta;
	
	/**
	 * Teksti ilmoituksia ja ohjeita varten.
	 */
	private JLabel ilmoitus;
	
	/**
	 * Pelikerran ruudukko.
	 */
	private Ruudukko ruudukko;
	
	/**
	 * Käyttöliittymäikkunan layoutmanageri.
	 */
	private GridBagLayout gbl;
	
	/**
	 * Layoutmanageriin liittyvät vakiot.
	 */
	private GridBagConstraints gbc;
		
	/**
	 * Luo pelin graafisen käyttöliittymäikkunan ja uuden pelikerran.
	 */	
	public Ristinolla() {
		
		// luodaan uusi peli
		this.peli = new Peli();
		
		this.gbl = new GridBagLayout();
		this.setLayout(gbl);
		this.gbc = new GridBagConstraints();
		
		// asetetaan muuttuvat käyttöliittymäkomponentit
		this.asetaKomponentit();
		
		// asetetaan pysyvät käyttöliittymäkomponentit		
		this.uusipeli = new JButton("Uusi");
		this.uusipeli.addActionListener(this);
		this.lopeta = new JButton("Lopeta");
		this.lopeta.addActionListener(this);
		
		JPanel nappulat = new JPanel(new FlowLayout());
		
	    nappulat.add(this.uusipeli);
	    nappulat.add(this.lopeta);
	    
	    this.gbc.fill = GridBagConstraints.NONE;
	    asetaGBCVakiot(this.gbc, 0, 2, GridBagConstraints.CENTER);
	    this.gbl.setConstraints(nappulat, this.gbc);
	    this.add(nappulat);
	   
	    this.setResizable(false);
		this.setTitle("Ristinolla");
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		// asetetaan pelimerkki
		Valintaikkuna valinta = new Valintaikkuna(this.peli);
	}
	
	/**
	 * Asettaa tietyt GridBagConstraints-vakiot parametrien mukaisiksi. (apumetodi)
	 * 
	 * @param gbc		muutettavat GridBagConstraints-vakiot
	 * @param x			komponentin solun x-koordinaatti
	 * @param y			komponentin solun y-koordinaatti
	 * @param a			arvo, joka kertoo komponentin sijoittelun solussa
	 */
	public void asetaGBCVakiot(GridBagConstraints gbc, int x, int y, int a) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = a;
	}
	
	/**
	 * Asettaa pelikerroittain vaihtuvat komponentit käyttöliittymään.
	 */	
	public void asetaKomponentit() {		
		this.gbc.fill = GridBagConstraints.NONE;
		this.gbc.ipadx = 0;
		this.gbc.ipady = 0;
		this.gbc.weightx = 100;
		this.gbc.weighty = 100;
		this.gbc.gridwidth = 1;
		this.gbc.gridheight = 1;
		this.gbc.insets = new Insets(5, 10, 5, 10);
		
		this.ilmoitus = this.peli.haeIlmoitus();		
		asetaGBCVakiot(this.gbc, 0, 0, GridBagConstraints.CENTER);	
		this.gbl.setConstraints(this.ilmoitus, this.gbc);
	    this.add(this.ilmoitus);
	    
	    this.ruudukko = this.peli.haeRuudukko();
	    asetaGBCVakiot(this.gbc, 0, 1, GridBagConstraints.CENTER);	
	    this.gbl.setConstraints(this.ruudukko, this.gbc);
	    this.add(this.ruudukko);
	}

	/**
	 * Käsittelee käyttöliittymäkomponenttien tapahtumat 
	 * ja toteuttaa Uusi- ja Lopeta-nappien toiminnan.
	 * 
	 * @param tapahtuma		käyttöliittymäikkunassa ilmennyt tapahtuma
	 */	
	public void actionPerformed(ActionEvent tapahtuma) {
		Object aiheuttaja = tapahtuma.getSource();		
		if(aiheuttaja == this.uusipeli) {			
			// luodaan uusi peli
			this.peli = new Peli();
			
			// asetetaan pelimerkki
			Valintaikkuna valinta = new Valintaikkuna(this.peli);
			
			// poistetaan komponentit, 
			// jotka uutta peliä luodessa mahdollisesti vaihdetaan
			this.remove(this.ilmoitus);
			this.remove(this.ruudukko);

			// lisätään uudet komponentit
			this.asetaKomponentit();
			
			// tulostetaan viesti käyttäjälle
			this.peli.ilmoita("Pelaa! ");
		}
		
		if(aiheuttaja == this.lopeta) {
			System.exit(0);
		}
	}
	
	/**
	 * Luo käyttöliittymäikkunan näytölle.
	 * 
	 * @param args		komentoriviparametrit 
	 * 					(eivät vaikuta ohjelman toimintaan)
	 */	
	public static void main(String[] args) {
		Ristinolla peliIkkuna = new Ristinolla();
	}
}
