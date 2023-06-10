package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Text implements Shape{
	private String string;
	private double left;
	private double top;
	private Color color;
	private double fontSize;
	public Text(String string, double left, double top, Color color, double fontSize) {
		this.string = string;
		this.left = left;
		this.top = top;
		this.color = color;
		this.fontSize = fontSize;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	public void draw() {
		UI.setColor(color);
		UI.setFontSize(fontSize);
		UI.drawString(string, left, top);
	}
	public boolean checkPoint(double x, double y) {
		if(x>=left && x<=left+string.length()*fontSize && y>=top-fontSize && y<=top) {
			return true;
		}return false;
	}
	public String toString() {
		return"Text"+" "+string+" "+left+" "+top+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue()+" "+fontSize;
	}
	public void fill(Color newColor) {
//		this.color = newColor;
//		UI.setColor(this.color);
		// This method does not apply here
	}
}
