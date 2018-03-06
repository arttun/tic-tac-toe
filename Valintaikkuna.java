/**
 * Pelimerkin valintaikkuna.
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

public class Valintaikkuna extends JFrame implements ActionListener {

	/**
	 * Risti-valintapainike.
	 */
	private JButton risti;
	
	/**
	 * Nolla-valintapainike.
	 */
	private JButton nolla;
	
	/**
	 * Meneill‰‰n oleva pelikerta.
	 */
	Peli peli;
	
	/**
	 * Luo ikkunan, jossa k‰ytt‰j‰lt‰ kysyt‰‰n haluamansa pelimerkki.s
	 * 
	 * @param peli		aloitettu uusi pelikerta
	 */
	public Valintaikkuna(Peli peli) {
		super();
		this.peli = peli;
		this.setTitle("Ristinolla");
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		JLabel ohje1 = new JLabel("Valitse pelimerkkisi.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(ohje1, gbc);
		this.add(ohje1);
		
		JLabel ohje2 = new JLabel("Risti aloittaa pelin.");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(ohje2, gbc);
		this.add(ohje2);
		
		this.risti = new JButton("X");
		this.risti.addActionListener(this);	
		this.nolla = new JButton("O");
		this.nolla.addActionListener(this);	
		
		JPanel nappulat = new JPanel(new FlowLayout());		
	    nappulat.add(this.risti);
	    nappulat.add(this.nolla);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(nappulat, gbc);
		this.add(nappulat);
		
		this.pack();
	    this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * K‰sittelee k‰ytt‰j‰n hiiripainalluksen.
	 * 
	 * @param tapahtuma		ikkunassa ilmennyt tapahtuma
	 */
	public void actionPerformed(ActionEvent tapahtuma) {
		if (tapahtuma.getSource() == this.risti) {
			this.peli.asetaPelaajanMerkki(Ruutu.RISTI);
			this.dispose();
		}
		else if (tapahtuma.getSource() == this.nolla) {
			this.peli.asetaPelaajanMerkki(Ruutu.NOLLA);
			this.dispose();
		}
	}
}
