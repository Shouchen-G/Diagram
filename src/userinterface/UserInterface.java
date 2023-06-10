package userinterface;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import ecs100.UI;
import ecs100.UIFileChooser;

public class UserInterface {
	private List<Shape> shapes = new ArrayList<Shape>();
	private double fX;
	private double fY;
	// button is the switch between different shapes and actions to take along with mouseListener
	private String button = "";
	private String imageFile ="" ;
	// selectedShape is an interim channel hold the original shape which is to be replaced
	private Shape selectedShape = null;
	private Color newColor = Color.black;

	public Color setColor() {
		return newColor = JColorChooser.showDialog(null, "Choose a color", Color.black);
	}

	public void drawRec() {
		this.button = "rec";
	}

	public void drawCircle() {
		this.button = "circle";
	}

	public void drawLine() {
		this.button = "line";
	}

	public void drawString() {
		this.button = "string";
	}

	public void fillWithColor() {
		this.button = "fill";
	}
	//box is the solid filled rectangle
	public void addBox() {
		this.button = "box";
	}

	public void draImage() {
		imageFile= UIFileChooser.save("File to save data in");
		this.button = "image";
	}

	public void erase() {
		this.button = "erase";
	}

	public void move() {
		this.button = "move";
	}

	public void zoom() {
		this.button = "zoom";
	}

	public void displayShapes() {
		for (Shape b : shapes) {
			b.draw();
		}
	}
	public void game() {
		UI.println("Please load file 'findGeorge' to play the game\nYou can use move, erase and zoom function to unbox the gift");
	}

	public void doMouse(String action, double x, double y) {
		if (button.equals("rec")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
			}
			if (action.equals("released")) {
				UI.setColor(newColor);
				Rectangle rec = new Rectangle(fX, fY, Math.abs(fX - x), Math.abs(fY - y), newColor);
				shapes.add(rec);
				rec.draw();
			}
		}

		if (button.equals("circle")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
			}
			if (action.equals("released")) {
				UI.setColor(newColor);
				Double size = Math.sqrt((x - fX) * (x - fX) + (y - fY) * (y - fY));
				Double radius = size / 2;
				Circle circle = new Circle(fX, fY - radius, size, newColor);
				shapes.add(circle);
				circle.draw();
			}
		}

		if (button.equals("line")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
			}
			if (action.equals("released")) {
				UI.setColor(newColor);
				Line line = new Line(fX, fY, x, y, newColor);
				shapes.add(line);
				line.draw();
			}
		}

		if (button.equals("string")) {
			if (action.equals("clicked")) {
				String string = UI.askString("Please enter the text to present:");
				double fontSize = UI.askDouble("Please enter the font size in number:");
				UI.setFontSize(fontSize);
				Text text = new Text(string, x, y, newColor, fontSize);
				shapes.add(text);
				text.draw();
			}
		}

		if (button.equals("fill")) {
			if (action.equals("clicked")) {
				for(int i=0; i<shapes.size(); i++) {
					Shape s = shapes.get(i);
					if (s.checkPoint(x, y) == true) {
						selectedShape = s;
						s.fill(newColor);
					}
					selectedShape = null;
				}
			}
		}
		if (button.equals("box")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
			}
			if (action.equals("released")) {
				UI.setColor(newColor);
				Box box = new Box(fX, fY, Math.abs(fX - x), Math.abs(fY - y), newColor);
				shapes.add(box);
				box.draw();
			}
		}

		if (button.equals("image")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
			}
			if (action.equals("released")) {
				Image image = new Image(imageFile, fX, fY, Math.abs(x - fX), Math.abs(y - fY));
				shapes.add(image);
				image.draw();
			}

		}

		if (button.equals("erase")) {
			if (action.equals("clicked")) {
				Shape shape = shapeSearch(x, y);
				if (shape != null) {
					shapes.remove(shape);
					UI.clearGraphics();
					for (Shape s : shapes) {
						s.draw();
					}
				}
			}
		}

		if (button.equals("move")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
				for (Shape s : shapes) {
					if (s.checkPoint(x, y) == true) {
						selectedShape = s;
					}
				}
			}
			if (action.equals("released")) {
				if (selectedShape != null) {
					if (!(selectedShape instanceof Line)) {
						selectedShape.setLeft(x);
						selectedShape.setTop(y);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					} else {
						Line line = (Line) selectedShape;
						// dif is the distance of the movement of the mouse
						double difX = x - fX;
						double difY = y - fY;
						line.setLeft(line.getLeft() + difX);
						line.setTop(line.getTop() + difY);
						line.setX2(line.getX2() + difX);
						line.setY2(line.getY2() + difY);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					}
				}
			}
		}
		if (button.equals("zoom")) {
			if (action.equals("pressed")) {
				fX = x;
				fY = y;
				for (Shape s : shapes) {
					if (s.checkPoint(x, y) == true) {
						selectedShape = s;
					}
				}
			}
			if (action.equals("released")) {
				if (selectedShape != null) {
					if (selectedShape instanceof Box) {
						Box box = (Box)selectedShape;
						double difX = x - box.getLeft()-box.getWidth();
						double difY = y -box.getHeight()-box.getHeight();
						box.setWidth(box.getWidth()+difX);
						box.setHeight(box.getHeight()+difY);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					}
					if (selectedShape instanceof Rectangle) {
						Rectangle rec = (Rectangle)selectedShape;
						double difX = x - rec.getLeft()-rec.getWidth();
						double difY = y -rec.getHeight()-rec.getHeight();
						rec.setWidth(rec.getWidth()+difX);
						rec.setHeight(rec.getHeight()+difY);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					}
					if(selectedShape instanceof Circle) {
						Circle circle = (Circle) selectedShape;
						double dif = x - circle.getCenterX()-circle.getSize();
						circle.setSize(circle.getSize()+dif);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					}
					if(selectedShape instanceof Line) {
						Line line = (Line) selectedShape;
						line.setX2(x);
						line.setY2(y);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					}
					if(selectedShape instanceof Image) {
						Image image = (Image) selectedShape;
						double difX = x - image.getLeft()-image.getWidth();
						double difY = y -image.getHeight()-image.getHeight();
						image.setWidth(image.getWidth()+difX);
						image.setHeight(image.getHeight()+difY);
						UI.clearGraphics();
						for (Shape s : shapes) {
							s.draw();
						}
						selectedShape = null;
					}
				}
			}
		}
	}

	public Shape shapeSearch(double x, double y) {
		for (Shape s : shapes) {
			if (s.checkPoint(x, y) == true) {
				return s;
			}
		}
		return null;
	}

	private void saveToFile() {
		try {
			PrintStream stream = new PrintStream(new File(UIFileChooser.save("File to save data in")));
			for (Shape s : shapes) {
				stream.println(s);
			}
			stream.close();
		} catch (IOException e) {
			UI.println("A file-writing error: " + e);
		}
	}

	public void loadFile() {
		try {
			Scanner scan = new Scanner(new File(UIFileChooser.open("Select a file to open")));
			while (scan.hasNext()) {
				// the next scanned string is store in an object
				String object = scan.next();
				if (object.equals("Box")) {
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					double w = scan.nextDouble();
					double h = scan.nextDouble();
					Color c = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
					Box box = new Box(x, y, w, h, c);
					shapes.add(box);
					scan.nextLine();
				} else if (object.equals("Circle")) {
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					double s = scan.nextDouble();
					Color c = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
					Circle circle = new Circle(x, y, s, c);
					shapes.add(circle);
					scan.nextLine();
				} else if (object.equals("Line")) {
					double x1 = scan.nextDouble();
					double y1 = scan.nextDouble();
					double x2 = scan.nextDouble();
					double y2 = scan.nextDouble();
					Color c = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
					Line line = new Line(x1, y1, x2, y2, c);
					shapes.add(line);
					scan.nextLine();
				} else if (object.equals("Text")) {
					String s = scan.next();
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					Color c = new Color(scan.nextInt(), scan.nextInt(), scan.nextInt());
					double f = scan.nextDouble();
					Text text = new Text(s, x, y, c, f);
					shapes.add(text);
					scan.nextLine();
				}else if(object.equals("Image")) {
					String s = scan.next();
					double x = scan.nextDouble();
					double y = scan.nextDouble();
					double w = scan.nextDouble();
					double h = scan.nextDouble();
					Image image = new Image(s, x, y, w, h);
					shapes.add(image);
					scan.nextLine();
				}
			}

		} catch (Exception e) {
			UI.println("A file-reading error: " + e);
		}
		for (Shape s : shapes) {
			s.draw();
		}
	}

	public void clearGraphics() {
		UI.clearGraphics();
	}
	

	public UserInterface() {
		UI.initialise();
		UI.addButton("Set color", this::setColor);
		UI.addButton("Draw rectangle", this::drawRec);
		UI.addButton("Draw circles", this::drawCircle);
		UI.addButton("Draw lines", this::drawLine);
		UI.addButton("Draw Strings", this::drawString);
		UI.addButton("Draw images", this::draImage);
		UI.addButton("Fill with color", this::fillWithColor);
		UI.addButton("Add box", this::addBox);
		UI.addButton("Erase", this::erase);
		UI.addButton("Move", this::move);
		UI.addButton("Zoom", this::zoom);
		UI.addButton("Display shapes", this::displayShapes);
		UI.addButton("Clear", this::clearGraphics);
		UI.addButton("Save to file", this::saveToFile);
		UI.addButton("Load saved diagram", this::loadFile);
		UI.addButton("Unboxing Game", this::game);
		UI.setMouseListener(this::doMouse);
	}

	public static void main(String[] args) {
		new UserInterface();

	}
}
