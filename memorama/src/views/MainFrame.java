package views;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

import components.JPairButton;
import routines.Rutinas;

public class MainFrame extends JFrame implements ActionListener{

	private static final String [] IMAGE_NAMES = {"caballo.jpg","camaleon.jpg","delfin.jpg","elefante.jpg","gato.jpg","leon.jpg",
													"murcielago.jpg","perro.jpg","pulpo.jpg","serpiente.jpg","persona.jpg","buho.jpg"
													,"dinosaurio.jpg","panda.jpg","tiburon.jpg"};
	private JPairButton[] pairs; 
	private JPairButton currentPair;
	int contClicks,totalPairs,contPairs = 0;
	
	
	private MainFrame() {
		super("Memorama");
		totalPairs = Rutinas.nextInt(10,15);
		System.out.println(totalPairs);
		setLayout(new GridLayout(0,4,5,5));
		createButtons();
		
		setSize(700,700);
		addButtons();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		updateFirst();
		addListeners();
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	private void createButtons() {
		pairs = new JPairButton[totalPairs*2]; 
		JPairButton pair1,pair2;
		int position1, position2;
		
		for(int i = 0; i < totalPairs; i++ ) {
			position1 = Rutinas.nextInt(0,(totalPairs*2)-1);
			position2 = Rutinas.nextInt(0,(totalPairs*2)-1);
			if(pairs[position1] != null || pairs[position2] != null || position1 == position2) {
				i--;
				continue;
			}
			
			pair1 = new JPairButton(IMAGE_NAMES[i],"back.png");
			pair2 = new JPairButton(IMAGE_NAMES[i],"back.png",pair1);
			pair1.setPair(pair2);
			
			pairs[position1] = pair1;
			pairs[position2] = pair2;
		}
	}
	
	private void addButtons() {
		for(int i = 0; i< pairs.length; i++)
			add(pairs[i],i);
	}
	
	private void updateFirst() {
		for(JPairButton pair: pairs) {
			pair.setIcon(Rutinas.changeSize(pair.getBackImage(), pair.getWidth(), pair.getHeight()));
			pair.update(pair.getGraphics());
		}
	}
	
	public void Restart() {
		removeButtons();
		totalPairs = Rutinas.nextInt(10,15);
		createButtons();
		addButtons();
		SwingUtilities.updateComponentTreeUI(this);
		updateFirst();
		addListeners();
	}
	
	private void removeButtons() {
		for(JPairButton pair: pairs) 
			this.remove(pair);
	}
	
	/*
	 *******************************ESCUCHADORES**************************
	 * */

	private void addListeners() {
		for(JPairButton pair: pairs)
			pair.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		contClicks ++;
		JPairButton btnOnClick = (JPairButton) e.getSource();
		
		if(currentPair == btnOnClick)
			return;
	
		btnOnClick.setIcon(Rutinas.changeSize(btnOnClick.getFrontImage(), btnOnClick.getWidth(), btnOnClick.getHeight()));
		btnOnClick.update(btnOnClick.getGraphics());
		
		if(contClicks == 1) {
			currentPair = btnOnClick;
			return;
		}

		try {
			Thread.sleep(1000);
			contClicks = 0;
			if(currentPair.getPair() == btnOnClick) {
				btnOnClick.setEnabled(false);
				btnOnClick.setDisabledIcon(Rutinas.changeSize(btnOnClick.getFrontImage(), btnOnClick.getWidth(), btnOnClick.getHeight()));
				currentPair.setEnabled(false);
				currentPair.setDisabledIcon(Rutinas.changeSize(currentPair.getFrontImage(), currentPair.getWidth(), currentPair.getHeight()));
				contPairs++;
				
				if(contPairs == totalPairs) {
					RestartMenu restart = new RestartMenu(this);
					restart.start();
				}
					
				return;
			}
			btnOnClick.setIcon(Rutinas.changeSize(btnOnClick.getBackImage(), btnOnClick.getWidth(), btnOnClick.getHeight()));
			currentPair.setIcon(Rutinas.changeSize(btnOnClick.getBackImage(), currentPair.getWidth(), currentPair.getHeight()));
		} catch (Exception ex){

		}

	}

}
