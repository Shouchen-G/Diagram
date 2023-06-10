package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Rectangle implements Shape{
	private double left;
	private double top;
	private double width;
	private double height;
	private Color color;
	public Rectangle(double left, double top, double width, double height, Color color) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
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
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void draw() {
		UI.setColor(this.color);
		UI.drawRect(left, top, width, height);
	}
	public void fill(Color newColor) {
		this.color = newColor;
		UI.setColor(this.color);
		UI.fillRect(left, top, width, height);
	}
	public boolean checkPoint(double x, double y) {
		if(x>=left && x<=left+width && y>=top && y<=top+height) {
			return true;
		}return false;
	}
	public String toString() {
		return "Box"+" "+left+" "+top+" "+width+" "+height+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue();
	}
}
