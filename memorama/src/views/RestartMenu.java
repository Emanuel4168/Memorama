package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RestartMenu extends JDialog implements ActionListener{
	
	private JButton btnRestart , btnExit;
	private MainFrame parent; 
	
	public RestartMenu(MainFrame frame) {
		super(frame);
		parent = frame;
		setLayout(new GridLayout(0,1,10,10));
		setSize(200,200);
		add(new JLabel("Ganador"),JLabel.CENTER);
		btnRestart = new JButton("¿Volver a jugar?");
		btnExit = new JButton("¿Salir?");
		add(btnRestart);
		add(btnExit);
		setLocationRelativeTo(null);
		setModal(true);
		btnRestart.addActionListener(this);
		btnExit.addActionListener(this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void start() {
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClick = (JButton) e.getSource();				
		if(btnClick == btnRestart) {
			parent.Restart();
			dispose();
		}
		if(btnClick == btnExit) {
			setVisible(false);
			parent.dispose();
			dispose();
		}
	}
}
