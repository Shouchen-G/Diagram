package userinterface;

import java.awt.Color;

public interface Shape {
	public void draw();
	public boolean checkPoint(double x, double y);
	public void setLeft(double left);
	public void setTop(double top);
	public void fill(Color newColor);
	
}
