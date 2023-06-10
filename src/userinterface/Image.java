package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Image implements Shape{
	private String fileName;
	private double left;
	private double top;
	private double width;
	private double height;
	public Image(String fileName, double left, double top, double width, double height) {
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
		this.fileName = fileName;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void draw() {
		UI.drawImage(fileName, left,top, width, height);
	}
	public boolean checkPoint(double x, double y) {
		if(x>=left && x<=left+width && y>=top && y<=top+height) {
			return true;
		}return false;
	}
	public String toString() {
		return "Image"+" "+fileName+" "+left+" "+top+" "+width+" "+height;
	}
	public void fill(Color newColor) {
//		this.color = newColor;
//		UI.setColor(this.color);
		// This method does not apply here
	}
}
