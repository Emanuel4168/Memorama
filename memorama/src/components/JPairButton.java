package components;

import javax.swing.*;

import routines.Rutinas;

public class JPairButton extends JButton{
	public static final String BASE_URL = "/img/";
	
	private String backImage;
	private String frontImage;
	private JPairButton pair;
	
	public JPairButton(String frontImage, String backImage, JPairButton pair) {
		this.backImage = backImage;
		this.frontImage = frontImage;
		this.pair = pair;
	}
	
	public JPairButton(String frontImage, String backImage) {
		this.backImage = backImage;
		this.frontImage = frontImage;
	}

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public String getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}

	public JPairButton getPair() {
		return pair;
	}

	public void setPair(JPairButton pair) {
		this.pair = pair;
	}

}
