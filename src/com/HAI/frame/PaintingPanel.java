package src.com.HAI.frame;
import src.com.HAI.shapes.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import src.com.HAI.shapes.DrawingProperties;
import src.com.HAI.shapes.MyEllipse;
import src.com.HAI.shapes.MyRectangle;
import src.com.HAI.shapes.MyTriangle;

public class PaintingPanel extends JPanel {

	ButtonGroup bg = new ButtonGroup();
	private JRadioButton rdbtnSquare;
	private JRadioButton rdbtnCircle;
	private JRadioButton rdbtnTriangle;

	private int x, y, fx, fy;
	private int CurrX, CurrY;

	private int thickness;

	private DrawingProperties currProp = new DrawingProperties();

	boolean dragging = false;
	boolean drawingTriangle = false;

	MyRectangle Rdrag = new MyRectangle();
	Ellipse2D Edrag = new MyEllipse();

	int[] xpoints = new int[3];
	int[] ypoints = new int[3];

	int clicks = 0;

	Stack<Shape> shapes = new Stack<Shape>();

	public PaintingPanel() {
		setLayout(null);

		rdbtnCircle = new JRadioButton("circle");
		rdbtnCircle.setBounds(279, 10, 71, 23);
		add(rdbtnCircle);

		rdbtnSquare = new JRadioButton("square");
		rdbtnSquare.setBounds(404, 10, 89, 23);
		add(rdbtnSquare);

		rdbtnTriangle = new JRadioButton("triangle");
		rdbtnTriangle.setBounds(537, 10, 89, 23);
		add(rdbtnTriangle);

		bg.add(rdbtnSquare);
		bg.add(rdbtnCircle);
		bg.add(rdbtnTriangle);

		
		rdbtnCircle.setSelected(true);

		setBackground(Color.white);

		JButton btnUndo = new JButton("undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!shapes.isEmpty()) {
					System.out.print("size before = " + shapes.size());
					shapes.pop();
					System.out.println(" after = " + shapes.size());
				}
				repaint();
			}
		});
		btnUndo.setBounds(676, 10, 89, 23);
		add(btnUndo);

		JSlider thickness = new JSlider();
		thickness.setValue(3);
		thickness.setMaximum(8);
		thickness.setMinimum(1);
		thickness.setToolTipText("Drawing Pen Thickness");
		thickness.setBounds(37, 10, 200, 16);
		add(thickness);
		thickness.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				currProp.setStroke(new BasicStroke(thickness.getValue()));
				
			}
		});
		
		JButton btnOutlineColor = new JButton("change outline color");
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r, g, b;
				r = (int) (Math.random()*255);
				g = (int) (Math.random()*255);
				b = (int) (Math.random()*255);
				Color newColor = new Color(r, g, b);
				currProp.setOutline(newColor);
				btnOutlineColor.setBackground(newColor);
			}
		});
		btnOutlineColor.setBounds(596, 45, 192, 25);
		add(btnOutlineColor);
		
		JButton btnFillColor = new JButton("change fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r, g, b;
				r = (int) (Math.random()*255);
				g = (int) (Math.random()*255);
				b = (int) (Math.random()*255);
				Color newColor = new Color(r, g, b);
				currProp.setFill(newColor);
				btnFillColor.setBackground(newColor);
				
			}
		});
		btnFillColor.setBounds(619, 82, 146, 25);
		add(btnFillColor);

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				fx = e.getX();
				fy = e.getY();
				if (rdbtnSquare.isSelected()) {
					MyRectangle r = new MyRectangle(x, y, fx - x, fy - y);
					try {
						r.setProp((DrawingProperties) currProp.clone());
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
					shapes.add(r);
				} else if (rdbtnCircle.isSelected()) {
					MyEllipse ellipse = new MyEllipse(x, y, fx - x, fy - y);
					try {
						ellipse.setProp((DrawingProperties) currProp.clone());
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
					shapes.add(ellipse);
				}
				repaint();

			}

			public void mouseClicked(MouseEvent e) {
				int xClick = e.getX();
				int yClick = e.getY();

				if (!rdbtnTriangle.isSelected()) {
					Shape delete = null;

					for (Shape s : shapes) {
						if (s.contains(xClick, yClick)) {
							System.out.println("Selected " + s);
								delete = s;
						}
					}
					
					if (delete != null)
						shapes.remove(delete);
				
				} else {
					// triangle is selected so the user is trying to draw one
					drawingTriangle = true;
					clicks++;
					xpoints[clicks - 1] = e.getX();
					ypoints[clicks - 1] = e.getY();

					// if the user has chosen the 3 points of the triangle
					if (clicks == 3) {
						MyTriangle t = new MyTriangle(xpoints, ypoints, 3);
						try {
							t.setProp((DrawingProperties) currProp.clone());
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}
						shapes.add(t);
						System.out.println("adding new trinagle to shapes array size = " + shapes.size());
						System.out.println(xpoints[0] + " " + xpoints[1] + " " + xpoints[2] + " " + ypoints[0] + " "
								+ ypoints[1] + " " + ypoints[2]);

						// reset the number of clicks to 0
						clicks = 0;

						// the user finished drawing the triangle
						drawingTriangle = false;

					}

				}

				repaint();

			}

		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (rdbtnSquare.isSelected()) {
					Rdrag.setLocation(x, y);
					Rdrag.setSize(e.getX() - x, e.getY() - y);
				} else if (rdbtnCircle.isSelected()) {
					Edrag.setFrame(x, y, e.getX() - x, e.getY() - y);
				}
				dragging = true;
				repaint();

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				CurrX = e.getX();
				CurrY = e.getY();

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
			if (rdbtnSquare.isSelected()) {
				g.setColor(Color.black);
				((Graphics2D) g).draw((Shape) Rdrag);
			} else if(rdbtnCircle.isSelected()){
				g.setColor(Color.black);
				((Graphics2D) g).draw((Shape) Edrag);
			}

			dragging = false;
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
}
