package main;

import java.awt.EventQueue;

import GUI.Meny;
/**
 * Hovedmetoden som kjører hele resten av programmet. 
 * @author Mikael
 */
public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meny frame = new Meny();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
