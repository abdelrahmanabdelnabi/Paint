package src.com.HAI.frame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import src.com.HAI.shapes.DrawingProperties;
import src.com.HAI.shapes.MyEllipse;
import src.com.HAI.shapes.MyRectangle;
import src.com.HAI.shapes.MyTriangle;

public class PaintingPanel extends JPanel {

	private MainFrame mainFrame; // a referenece to the parent frame of this
									// panel

	private int x, y; // x & y: the x & y coordinates of the mouse "press"
	private int fx, fy; // fx & fy: the x & y coordinates of the mouse
						// "released"
	private int CurrX, CurrY; // CurrX and CurrY: the x & y coordinates of the
								// current mouse location

	private DrawingProperties currProp = new DrawingProperties(); // the current
																	// drawing
																	// properties
																	// for
																	// drawing
																	// shapes

	boolean dragging = false; // a boolean flag indicating of the user is
								// drawing a shape by dragging the mouse

	boolean drawingTriangle = false; // a boolean flag indicating if the user it
										// drawing a triangle by clicking on 3
										// separate location

	public boolean moving = false;
	public boolean copying = false;

	private MyRectangle Rdrag = new MyRectangle(); // an instance of a rectangle
													// that is drawn while
													// dragging the mouse

	private MyEllipse Edrag = new MyEllipse(); // an instance of the ellipse
												// that is drawn while draggin
												// the ellipse
	public Shape selectedShape;

	private int[] xpoints = new int[3]; // an array of x and y coordinates of
										// the 3 mouse clicks for drawing the
										// triangle
	private int[] ypoints = new int[3];

	private int clicks = 0; // the number of clicks clicked when drawing a
							// triangle

	Stack<Shape> shapes = new Stack<Shape>(); // a stack holding the shapes that
	Stack<Shape> shapes2 = new Stack<Shape>();											// a drawn

	public PaintingPanel() {
		setLayout(null);

		setBackground(Color.white);

		this.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				fx = e.getX();
				fy = e.getY();

				// clone the current drawing properties and place it in the
				// shape that is being drawn
				DrawingProperties cloned = null;
				try {
					cloned = (DrawingProperties) currProp.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}

				if (mainFrame.rdbtnRectangle.isSelected()) {
					MyRectangle r = new MyRectangle(Math.min(x, fx), Math.min(y, fy), Math.abs(fx - x),
							Math.abs(fy - y));

					r.setProp(cloned);
					shapes.add(r);

				} else if (mainFrame.rdbtnEllipse.isSelected()) {
					MyEllipse ellipse = new MyEllipse(Math.min(x, fx), Math.min(y, fy), Math.abs(fx - x),
							Math.abs(fy - y));

					ellipse.setProp(cloned);
					shapes.add(ellipse);
				} else if (mainFrame.rdbtnSquare.isSelected()) {
					MyRectangle r = (MyRectangle) Rdrag.clone();

					r.setProp(cloned);
					shapes.add(r);
				} else if (mainFrame.rdbtnCircle.isSelected()) {
					MyEllipse ellipse = (MyEllipse) Edrag.clone();

					ellipse.setProp(cloned);
					shapes.add(ellipse);
				}

				repaint();
			}

			public void mouseClicked(MouseEvent e) {
				int xClick = e.getX();
				int yClick = e.getY();

				if (moving == true) {
					if (selectedShape instanceof MyEllipse) {
						((MyEllipse) selectedShape).x = xClick - ((MyEllipse) selectedShape).width / 2;
						((MyEllipse) selectedShape).y = yClick - ((MyEllipse) selectedShape).height / 2;

						moving = false;
					}
				} else if (mainFrame.rdbtnSelect.isSelected()) {
					selectedShape = getSelectedShape(xClick, yClick);

					ShapeDetails sd = mainFrame.detailsPanel;

					if (selectedShape instanceof MyEllipse) {
						((MyEllipse) selectedShape).updateDetailsPanel(sd);
					} // else if rectangle...

				} else if (mainFrame.rdbtnTriangle.isSelected()) {
					// triangle is selected so the user is trying to draw one
					drawingTriangle = true;
					clicks++;
					xpoints[clicks - 1] = e.getX();
					ypoints[clicks - 1] = e.getY();

					// if the user has chosen the 3 points of the triangle
					if (clicks == 3) {

						MyTriangle t = new MyTriangle(xpoints, ypoints, 3);

						DrawingProperties cloned;
						try {
							cloned = (DrawingProperties) currProp.clone();
							t.setProp(cloned);
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}

						shapes.add(t);

						// reset the number of clicks to 0
						clicks = 0;

						// the user finished drawing the triangle
						drawingTriangle = false;

					}
				}

				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				CurrX = 0;
				CurrY = 0;
				mainFrame.updateCoordinates(0, 0);
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {

			public void mouseDragged(MouseEvent e) {

				if (mainFrame.rdbtnRectangle.isSelected()) {

					Rdrag.setFrame(Math.min(x, e.getX()), Math.min(y, e.getY()), Math.abs(x - e.getX()),
							Math.abs(y - e.getY()));

				} else if (mainFrame.rdbtnEllipse.isSelected()) {

					Edrag.setFrame(Math.min(x, e.getX()), Math.min(y, e.getY()), Math.abs(x - e.getX()),
							Math.abs(y - e.getY()));

				} else if (mainFrame.rdbtnSquare.isSelected()) {

					int sideLength = Math.max(Math.abs(x - e.getX()), Math.abs(y - e.getY()));

					Rdrag.setFrame(Math.min(x, e.getX()), Math.min(y, e.getY()), sideLength, sideLength);

					if (e.getX() < x)
						Rdrag.x = x - sideLength;
					if (e.getY() < y)
						Rdrag.y = y - sideLength;
				} else if (mainFrame.rdbtnCircle.isSelected()) {

					int radius = Math.max(Math.abs(x - e.getX()), Math.abs(y - e.getY()));

					Edrag.setFrame(Math.min(x, e.getX()), Math.min(y, e.getY()), radius, radius);

					if (e.getX() < x && e.getY() > y)
						Edrag.setFrame(x - radius, Math.min(y, e.getY()), radius, radius);
					else if (e.getY() < y && e.getX() > x)
						Edrag.setFrame(Math.min(x, e.getX()), y - radius, radius, radius);
					else if (e.getX() < x && e.getY() < y)
						Edrag.setFrame(x - radius, y - radius, radius, radius);

				}

				dragging = true;
				
				mainFrame.updateCoordinates(e.getX(), e.getY());
				
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				CurrX = e.getX();
				CurrY = e.getY();

				if (moving || copying) {
					Edrag.x = CurrX - Edrag.width / 2;
					Edrag.y = CurrY - Edrag.height / 2;
				}

				mainFrame.updateCoordinates(CurrX, CurrY);

				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Shape r : shapes) {
			if (r instanceof MyRectangle) {
				((MyRectangle) r).draw(g);
			} else if (r instanceof MyEllipse) {
				((MyEllipse) r).draw(g);
				
			} else if (r instanceof MyTriangle) {
				((MyTriangle) r).draw(g);
			}
		}

		if (dragging) {
			if (mainFrame.rdbtnRectangle.isSelected() || mainFrame.rdbtnSquare.isSelected()) {
				g.setColor(Color.black);
				((Graphics2D) g).draw((Shape) Rdrag);
			} else if (mainFrame.rdbtnEllipse.isSelected() || mainFrame.rdbtnCircle.isSelected()) {
				g.setColor(Color.black);
				((Graphics2D) g).draw((Shape) Edrag);
			}

			dragging = false;
		}

		if (moving || copying) {
			g.setColor(Color.black);
			((Graphics2D) g).draw((Shape) Edrag);

		}

		if (clicks == 1) {
			g.drawLine(xpoints[0], ypoints[0], CurrX, CurrY);

		} else if (clicks == 2) {
			xpoints[2] = CurrX;
			ypoints[2] = CurrY;
			MyTriangle t = new MyTriangle(xpoints, ypoints, 3);
			t.draw(g);
		}

	}

	public void undoActionPerformed() {
		if (!shapes.isEmpty()) {
			System.out.print("size before = " + shapes.size());
			shapes2.push(shapes.pop());
			System.out.println(" after = " + shapes.size());
		}
		repaint();
	}
	
	public void redoActionPerformed() {
		if (!shapes2.isEmpty()) {
			System.out.print("size before = " + shapes2.size());
			shapes.push(shapes2.pop());
			System.out.println(" after = " + shapes2.size());
		}
		repaint();
	}

	public void clearActionPerformed (){
		while(!shapes.isEmpty()){
			shapes.pop();
		}
	}
	public void moveActionPerformed() {
		if (selectedShape instanceof MyEllipse) {
			Edrag = (MyEllipse) ((MyEllipse) selectedShape).clone();
			moving = true;
		}
	}
	
	public void thicknessChanged(int newThickness) {
		currProp.setStroke(new BasicStroke(newThickness));
	}

	public void fillColorChanged(Color c) {

		currProp.setFill(c);
	}

	public void outlineColorChanged(Color c) {

		currProp.setOutline(c);
	}

	private Shape getSelectedShape(int x, int y) {
		Shape selected = null;

		for (Shape s : shapes) {
			if (s.contains(x, y)) {
				System.out.println("Selected " + s);
				selected = s;
			}
		}

		return selected;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
