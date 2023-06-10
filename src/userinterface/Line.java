package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Line implements Shape{
	private double left;
	private double x2;
	private double top;
	private double y2;
	private Color color;
	public Line(double left, double top, double x2, double y2, Color color) {
		this.left = left;
		this.x2 = x2;
		this.top = top;
		this.y2 = y2;
		this.color = color;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void draw() {
		UI.setColor(color);
		UI.drawLine(left, top, x2, y2);
	}
	public boolean checkPoint(double x, double y) {
		if(x>=left && x<=x2 && y>=y2 && y<=top) {
			return true;
		}
		if(x>=left && x<=x2 && y>=top && y<=y2) {
			return true;
		}
		if(x>=x2 && x<=left && y>=y2 && y<=top) {
			return true;
		}
		if(x>=x2 && x<=left && y>=top && y<=y2) {
			return true;
		}
		return false;
		}
	public String toString() {
		return "Line"+" "+left+" "+top+" "+x2+" "+y2+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue();
	}
	public void fill(Color newColor) {
		//this.color = newColor;
		//UI.setColor(this.color);
		// This method does not apply here
	}
}
