/**
 * Kuvaa ihmispelaajan vastustajaa tietokonetta 
 * ja toteuttaa tietokoneen pelaamiseen vaadittavan teko‰lyn.
 * 
 * @author Arttu Nieminen
 * @version 1.0
 */

public class Tietokone {

	/**
	 * Minimax-haun syvyys.
	 */
	private static final int SYVYYS = 5;
	
	/**
	 * Pelikerta, johon tietokone osallistuu.
	 */
	private Peli peli;
	
	/**
	 * Pelikerran ruudukko.
	 */
	private Ruudukko ruudukko;
	
	/**
	 * Tietokoneen pelimerkki t‰ss‰ pelikerrassa.
	 */
	private int tietokoneenMerkki;
	
	/**
	 * Luo uuden tietokonepelaajan.
	 * 
	 * @param peli		pelikerta
	 */
	public Tietokone(Peli peli) {
		this.peli = peli;
		this.ruudukko = this.peli.haeRuudukko();
	}
	
	/**
	 * Asettaa tietokoneelle tiedon sen pelimerkist‰.
	 * 
	 * @param merkki	tietokoneen pelimerkki
	 */
	public void asetaTietokoneenMerkki(int merkki) {
		if (merkki != Ruutu.RISTI && merkki != Ruutu.NOLLA)
			return;		// virheellinen merkki
		this.tietokoneenMerkki = merkki;
	}
	
	/**
	 * Arviointifunktio, joka palauttaa arvion pelitilanteen 
	 * hyvyydest‰ tietokoneen kannalta. yadda yadda
	 * 
	 * @return 		arvio pelitilanteen hyvyydest‰
	 */	
	public int arvio() {
		return laskeSuorat(this.tietokoneenMerkki) - laskeSuorat(this.peli.haePelaajanMerkki());
	}
	
	/**
	 * Laskee parametrina annettavalla merkill‰ 
	 * muodostettavissa olevien suorien lukum‰‰r‰n.
	 * 
	 * @param merkki	pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @return 			muodostettavissa olevien suorien lukum‰‰r‰
	 */	
	public int laskeSuorat(int merkki) {
		if (merkki != Ruutu.RISTI && merkki != Ruutu.NOLLA)
			return 0;		// virheellinen merkki
		
		int laskuri = 0;
		
		laskuri = laskuri+laskeVaakaSuorat(merkki);
		laskuri = laskuri+laskePystySuorat(merkki);
		laskuri = laskuri+laskeLaskevatVinoSuorat(merkki);
		laskuri = laskuri+laskeNousevatVinoSuorat(merkki);

		return laskuri;
	}
	
	/**
	 * Laskee parametrina annettavalla merkill‰ 
	 * muodostettavissa olevien 
	 * vaakasuuntaisten suorien lukum‰‰r‰n.
	 * 
	 * @param merkki	pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @return 			muodostettavissa olevien vaakasuuntaisten suorien lukum‰‰r‰
	 */	
	private int laskeVaakaSuorat(int merkki) {
		int laskuri = 0;
		int suoranPituus = this.peli.haeSuoranPituus();
		int leveys = this.peli.haeLeveys();
		int korkeus = this.peli.haeKorkeus();
		int vastaMerkki = this.peli.haeVastaMerkki(merkki);
		int potkyla = 0;		

		for (int i=0; i<korkeus; ++i) {		// k‰yd‰‰n l‰pi kaikki rivit
			for (int j=0; j<leveys; ++j) {	// k‰yd‰‰n l‰pi kaikki sarakkeet
				if (this.ruudukko.haeRuutu(j, i).haeTila() != vastaMerkki)
					potkyla++;
				else {// (this.ruudukko.haeRuutu(j, i).haeTila() == vastaMerkki) {
					if (potkyla >= suoranPituus)
						laskuri = laskuri + potkyla - suoranPituus + 1;
					potkyla = 0;
				}
			}
			if (potkyla >= suoranPituus)
				laskuri = laskuri + potkyla - suoranPituus + 1;
			potkyla = 0;	// vaakarivi vaihtui
		}
		return laskuri;
	}
	
	/**
	 * Laskee parametrina annettavalla merkill‰ 
	 * muodostettavissa olevien 
	 * pystysuuntaisten suorien lukum‰‰r‰n.
	 * 
	 * @param merkki	pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @return 			muodostettavissa olevien pystysuuntaisten suorien lukum‰‰r‰
	 */	
	private int laskePystySuorat(int merkki) {
		int laskuri = 0;
		int suoranPituus = this.peli.haeSuoranPituus();
		int leveys = this.peli.haeLeveys();
		int korkeus = this.peli.haeKorkeus();
		int vastaMerkki = this.peli.haeVastaMerkki(merkki);
		int potkyla = 0;		

		for (int i=0; i<leveys; ++i) {		// k‰yd‰‰n l‰pi kaikki rivit
			for (int j=0; j<korkeus; ++j) {	// k‰yd‰‰n l‰pi kaikki sarakkeet
				if (this.ruudukko.haeRuutu(i, j).haeTila() != vastaMerkki)
					potkyla++;
				else {// (this.ruudukko.haeRuutu(i, j).haeTila() == vastaMerkki) {
					if (potkyla >= suoranPituus)
						laskuri = laskuri + potkyla - suoranPituus + 1;
					potkyla = 0;
				}
			}
			if (potkyla >= suoranPituus)
				laskuri = laskuri + potkyla - suoranPituus + 1;
			potkyla = 0;	// pystyrivi vaihtui
		}
		return laskuri;
	}
	
	/**
	 * Laskee parametrina annettavalla merkill‰ 
	 * muodostettavissa olevien vasemmalta oikealle
	 * laskevien suorien lukum‰‰r‰n.
	 * 
	 * @param merkki	pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @return 			muodostettavissa olevien vasemmalta oikealle
	 * 					laskevien suorien lukum‰‰r‰
	 */	
	private int laskeLaskevatVinoSuorat(int merkki) {
		int laskuri = 0;
		int suoranPituus = this.peli.haeSuoranPituus();
		int leveys = this.peli.haeLeveys();
		int korkeus = this.peli.haeKorkeus();
		int vastaMerkki = this.peli.haeVastaMerkki(merkki);
		int potkyla = 0;		

		for (int i=0; i<leveys; ++i) {
			for (int j=0; j<korkeus; ++j) {
				if (i==0 || j==0) {
					for (int k=0; i+k<leveys && j+k<korkeus ;++k) {
						if (this.ruudukko.haeRuutu(i+k, j+k).haeTila() != vastaMerkki)
							potkyla++;
						else {
							if (potkyla >= suoranPituus)
								laskuri = laskuri + potkyla - suoranPituus + 1;
							potkyla = 0;
						}
					}
					if (potkyla >= suoranPituus)
						laskuri = laskuri + potkyla - suoranPituus + 1;
					potkyla = 0;
				}
			}
		}
		return laskuri;
	}
	
	/**
	 * Laskee parametrina annettavalla merkill‰ 
	 * muodostettavissa olevien vasemmalta oikealle
	 * nousevien suorien lukum‰‰r‰n.
	 * 
	 * @param merkki	pelimerkki, mahdolliset arvot Ruutu.RISTI ja Ruutu.NOLLA
	 * @return 			muodostettavissa olevien vasemmalta oikealle
	 * 					nousevien suorien lukum‰‰r‰
	 */
	private int laskeNousevatVinoSuorat(int merkki) {
		int laskuri = 0;
		int suoranPituus = this.peli.haeSuoranPituus();
		int leveys = this.peli.haeLeveys();
		int korkeus = this.peli.haeKorkeus();
		int vastaMerkki = this.peli.haeVastaMerkki(merkki);
		int potkyla = 0;		

		for (int i=0; i<leveys; ++i) {
			for (int j=korkeus-1; j>=0; --j) {
				if (i==0 || j==0) {
					for (int k=0; i+k<leveys && j-k>=0 ;++k) {
						if (this.ruudukko.haeRuutu(i+k, j-k).haeTila() != vastaMerkki)
							potkyla++;
						else {
							if (potkyla >= suoranPituus)
								laskuri = laskuri + potkyla - suoranPituus + 1;
							potkyla = 0;
						}
					}
					if (potkyla >= suoranPituus)
						laskuri = laskuri + potkyla - suoranPituus + 1;
					potkyla = 0;
				}
			}
		}
		return laskuri;
	}
	
	/**
	 * Tekee aloitussiirron, kun tietokone k‰ytt‰‰ risti‰.
	 * Asettaa tietokoneen pelimerkin ristin 
	 * mahdollisimman keskelle ruudukkoa.
	 * 
	 */	
	public void aloita() {
		this.ruudukko.haeRuutu(this.peli.haeLeveys()/2, 
		this.peli.haeKorkeus()/2).asetaTila(this.tietokoneenMerkki);
	}
	
	/**
	 * Kysyy minimax-algoritmilta parhaan pelimerkin 
	 * sijoitusruudun ja asettaa pelimerkin kyseiseen ruutuun.
	 * 
	 * @return 			tietokoneen t‰ytt‰m‰ ruutu
	 */	
	public Ruutu pelaa() {
		Ruutu parasRuutu = minimax(Tietokone.SYVYYS);
		parasRuutu.asetaTila(this.tietokoneenMerkki);
		return parasRuutu;
	}
	
	/**
	 * Minimax-algoritmi, joka tutkii rekursiivisesti, 
	 * mik‰ ruutu tuottaa parhaan tuloksen tietokoneelle.
	 * 
	 * @param syvyys	syvyys, johon rajoitutaan pelipuuta l‰pik‰yt‰ess‰
	 * @return			paras ruutu tietokoneen kannalta
	 */
	public Ruutu minimax(int syvyys) {
		Ruutu parasRuutu = null;
		int parasArvo = Integer.MIN_VALUE;
		for (int i=0; i<this.peli.haeKorkeus(); ++i) {
			for (int j=0; j<this.peli.haeLeveys(); ++j) {
				Ruutu testi = this.ruudukko.haeRuutu(j, i);
				if (testi.haeTila() == Ruutu.TYHJA) {
					testi.asetaTila(this.peli.haeTietokoneenMerkki());
					int uusiArvo = mini(testi, syvyys, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
					if (uusiArvo > parasArvo) {
						parasArvo = uusiArvo;
						parasRuutu = testi;
					}
					testi.asetaTila(Ruutu.TYHJA);
				}
			}
		}
		return parasRuutu;
	}
	
	/**
	 * Minimax-algoritmin max-osa. 
	 * Tutkii max-solmun lapsisolmut ja valitsee
	 * tietokoneen kannalta parhaan (suurimman)
	 * arvon omaavan solmun.
	 * 
	 * @param ruutu		ruutu, johon viimeksi asetettiin merkki
	 * @param syvyys	syvyys, johon rajoitutaan pelipuuta l‰pik‰yt‰ess‰
	 * @param askelia	tutkittavaan pelitilanteeseen johtavien pelisiirtojen m‰‰r‰
	 * @param a			tietokoneen (MAX) toistaiseksi parhaan ruudun hyvyysarvo
	 * 					hakupolkua pitkin tutkittavaan tilaan
	 * @param b			pelaajan (MIN) toistaiseksi parhaan ruudun hyvyysarvo
	 * 					hakupolkua pitkin tutkittavaan tilaan
	 * @return			tietokoneen kannalta parhaan ruudun minimax-arvo
	 */
	public int max(Ruutu ruutu, int syvyys, int askelia, int a, int b) {
		if (this.peli.voittiko(this.peli.haePelaajanMerkki(), ruutu))
			return Integer.MIN_VALUE;	// pelaaja (MIN) voittaa
		if (this.peli.onkoTaynna())
			return 0;	// tasapeli
		if (syvyys <= 0)
			return arvio();
		int parasArvo = Integer.MIN_VALUE;
		for (int i=0; i<this.peli.haeKorkeus(); ++i) {
			for (int j=0; j<this.peli.haeLeveys(); ++j) {
				Ruutu testi = this.ruudukko.haeRuutu(j, i);
				if (testi.haeTila() == Ruutu.TYHJA) {
					testi.asetaTila(this.peli.haeTietokoneenMerkki());
					int uusiArvo = mini(testi, syvyys-1, askelia+1, a, b) - askelia;
					if (uusiArvo > parasArvo) {
						parasArvo = uusiArvo;
					}
					testi.asetaTila(Ruutu.TYHJA);
					if (parasArvo >= b)
						return parasArvo;
					if (parasArvo > a)
						a = parasArvo;
				}
			}
		}
		return parasArvo;
	}
	
	/**
	 * Minimax-algoritmin min-osa. 
	 * Tutkii min-solmun lapsisolmut ja valitsee
	 * pelaajan kannalta parhaan (pienimm‰n)
	 * arvon omaavan solmun.
	 * 
	 * @param ruutu		ruutu, johon viimeksi asetettiin merkki
	 * @param syvyys	syvyys, johon rajoitutaan pelipuuta l‰pik‰yt‰ess‰
	 * @param askelia	tutkittavaan pelitilanteeseen johtavien pelisiirtojen m‰‰r‰
	 * @param a			tietokoneen (MAX) toistaiseksi parhaan ruudun hyvyysarvo
	 * 					hakupolkua pitkin tutkittavaan tilaan
	 * @param b			pelaajan (MIN) toistaiseksi parhaan ruudun hyvyysarvo
	 * 					hakupolkua pitkin tutkittavaan tilaan
	 * @return			pelaajan kannalta parhaan ruudun minimax-arvo
	 */
	public int mini(Ruutu ruutu, int syvyys, int askelia, int a, int b) {
		if (this.peli.voittiko(this.peli.haeTietokoneenMerkki(), ruutu))
			return Integer.MAX_VALUE;	// tietokone (MAX) voittaa
		if (this.peli.onkoTaynna())
			return 0;	// tasapeli
		if (syvyys <= 0)
			return arvio();
		int parasArvo = Integer.MAX_VALUE;
		for (int i=0; i<this.peli.haeKorkeus(); ++i) {
			for (int j=0; j<this.peli.haeLeveys(); ++j) {
				Ruutu testi = this.ruudukko.haeRuutu(j, i);
				if (testi.haeTila() == Ruutu.TYHJA) {
					testi.asetaTila(this.peli.haePelaajanMerkki());
					int uusiArvo = max(testi, syvyys-1, askelia+1, a, b) + askelia;
					if (uusiArvo < parasArvo) {
						parasArvo = uusiArvo;
					}
					testi.asetaTila(Ruutu.TYHJA);
					if (parasArvo <= a)
						return parasArvo;
					if (parasArvo < b)
						b = parasArvo;
				}
			}
		}
		return parasArvo;
	}
}