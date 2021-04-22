package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Hovedmenyen for filmarkivet
 * @author Mikael
 *
 */
public class Meny extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JButton velgFilmbtn;
	private JButton filmListbtn;
	private JButton leggTilFilmbtn;
	private JButton statestikkbtn;
	private JButton redigerbtn;
	private JLabel	velkommen;
	

	public Meny() {
		
		JPanel velkomstPanel = new JPanel(new FlowLayout());
		
		velkommen = new JLabel("Velkommen til Filmarkivet!");
		velkomstPanel.add(velkommen);
		
		JPanel menyPanel1 = new JPanel(new FlowLayout());
		
		leggTilFilmbtn = new JButton("Legg til film");
		menyPanel1.add(leggTilFilmbtn);
		leggTilFilmbtn.addActionListener(new leggTilfilmListener());
		
		velgFilmbtn = new JButton("Velg en film");
		menyPanel1.add(velgFilmbtn);
		velgFilmbtn.addActionListener(new velgfilmListener());
		
		filmListbtn = new JButton("Se oversikt");
		menyPanel1.add(filmListbtn);
		filmListbtn.addActionListener(new seOversiktListener());

		JPanel menyPanel2 = new JPanel(new FlowLayout());
		
		statestikkbtn = new JButton("Statestikk");
		menyPanel2.add(statestikkbtn);
		statestikkbtn.addActionListener(new statestikkListener());
		
		redigerbtn = new JButton("Rediger en film");
		menyPanel2.add(redigerbtn);
		redigerbtn.addActionListener(new redigerListener());
		
		setLayout(new GridLayout(3, 1));
		add(velkomstPanel);
		add(menyPanel1);
		add(menyPanel2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Filmarkiv");
		setSize(450, 200);
		setLocation(700, 400);
		setVisible(true);
	}
	
	private class velgfilmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Velgfilm vf = new Velgfilm();
						vf.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	private class leggTilfilmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LeggTil lt = new LeggTil();
						lt.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	private class seOversiktListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Oversikt o = new Oversikt();
						o.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	private class statestikkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Statestikk stat = new Statestikk();
						stat.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	private class redigerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Rediger r = new Rediger();
						r.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
