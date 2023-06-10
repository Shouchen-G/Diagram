package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Circle implements Shape{
	private double left;
	private double top;
	private double size;
	private Color color;
	//X value of the center point in the circle
	private double centerX;
	//Y value of the center point in the circle
	private double centerY;
	public Circle(double left, double top, double size,Color color) {
		this.left = left;
		this.top = top;
		this.size = size;
		this.color = color;
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
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getCenterX() {
		return centerX;
	}
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	public double getCenterY() {
		return centerY;
	}
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	public void draw() {
		UI.setColor(color);
		UI.drawOval(left, top, size, size);
	}
	public void fill(Color newColor) {
		this.color = newColor;
		UI.setColor(this.color);
		UI.fillOval(left, top, size, size);
	}
	public boolean checkPoint(double x, double y) {
		centerX = left+size/2;
		centerY = top +size/2;
		if(Math.sqrt((centerX-x)*(centerX-x)+(centerY-y)*(centerY-y))<=size/2) {
			return true;
		}
		return false;
		}
	public String toString() {
		return"Circle"+" "+left+" "+top+" "+size+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue();
	}
}
