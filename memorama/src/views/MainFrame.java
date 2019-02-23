package views;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

import components.JPairButton;
import routines.Rutinas;

public class MainFrame extends JFrame implements ActionListener{

	private static final String [] IMAGE_NAMES = {"caballo.jpg","camaleon.jpg","delfin.jpg","elefante.jpg","gato.jpg","leon.jpg",
													"murcielago.jpg","perro.jpg","pulpo.jpg","serpiente.jpg"};
	private JPairButton[] pairs; 
	private JPairButton currentPair;
	int contClicks;
	
	private MainFrame() {
		super("Memorama");
		setLayout(new GridLayout(0,3,5,5));
		createButtons();
		addListeners();
		
		setSize(300,300);
		addButtons();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	private void createButtons() {
		pairs = new JPairButton[20]; 
		JPairButton pair1,pair2;
		int position1, position2;
		
		for(int i = 0; i < IMAGE_NAMES.length; i++ ) {
			position1 = Rutinas.nextInt(0,19);
			position2 = Rutinas.nextInt(0,19);
			if(pairs[position1] != null || pairs[position2] != null || position1 == position2) {
				//System.out.println("repetido");
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
		for(int i = 0; i< pairs.length; i++) {
			pairs[i].setIcon(Rutinas.changeSize(pairs[i].getBackImage(),50,50));
			add(pairs[i]);
		}
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
		
		btnOnClick.setIcon(Rutinas.changeSize(btnOnClick.getFrontImage(), 50, 50));
		btnOnClick.update(btnOnClick.getGraphics());
		
		if(contClicks == 1) {
			currentPair = btnOnClick;
			return;
		}

		try{
			Thread.sleep(1000);
			contClicks = 0;
			if(currentPair.getPair() == btnOnClick) {
				btnOnClick.setEnabled(false);
				btnOnClick.setDisabledIcon(Rutinas.changeSize(btnOnClick.getFrontImage(), 50, 50));
				currentPair.setEnabled(false);
				currentPair.setDisabledIcon(Rutinas.changeSize(currentPair.getFrontImage(), 50, 50));
				return;
			}
			btnOnClick.setIcon(Rutinas.changeSize(btnOnClick.getBackImage(), 50, 50));
			currentPair.setIcon(Rutinas.changeSize(btnOnClick.getBackImage(), 50, 50));
			
		} catch (Exception ex){

		}

	}

}
