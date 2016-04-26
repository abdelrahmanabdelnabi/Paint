package com.HAI.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PaintingPanel extends JPanel {

	ButtonGroup bg = new ButtonGroup();
	private JRadioButton rdbtnSquare;
	private JRadioButton rdbtnCircle;

	private int x, y, fx, fy;
	boolean dragging = false;

	Rectangle Rdrag = new Rectangle();
	Ellipse2D Edrag = new Ellipse2D.Float();

	Stack<Shape> shapes = new Stack<Shape>();

	public PaintingPanel() {
		setLayout(null);

		rdbtnCircle = new JRadioButton("circle");
		rdbtnCircle.setBounds(10, 10, 71, 23);
		add(rdbtnCircle);

		rdbtnSquare = new JRadioButton("square");
		rdbtnSquare.setBounds(83, 10, 71, 23);
		add(rdbtnSquare);

		bg.add(rdbtnSquare);
		bg.add(rdbtnCircle);

		rdbtnCircle.setSelected(true);

		JButton btnUndo = new JButton("undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!shapes.isEmpty()) {
					shapes.pop();
				}
				repaint();
			}
		});
		btnUndo.setBounds(170, 10, 89, 23);
		add(btnUndo);

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
					Rectangle r = new Rectangle();
					shapes.add(r);
					r.setLocation(x, y);
					r.setSize(e.getX() - x, e.getY() - y);
				} else {
					Ellipse2D rp = new Ellipse2D.Float();
					shapes.add(rp);
					rp.setFrame(x, y, fx - x, fy - y);
				}
				repaint();

			}

			public void mouseClicked(MouseEvent e) {
				int xClick = e.getX();
				int yClick = e.getY();

				Shape delete = null;

				for (Shape s : shapes) {
					if (s.contains(xClick, yClick)) {
						System.out.println("Selected " + s);
						if (s instanceof Rectangle) {
							delete = s;
						}
					}
				}
				if (delete != null)
					shapes.remove(delete);
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (rdbtnSquare.isSelected()) {
					Rdrag.setLocation(x, y);
					Rdrag.setSize(e.getX() - x, e.getY() - y);
				} else {
					Edrag.setFrame(x, y, e.getX() - x, e.getY() - y);
				}
				dragging = true;
				repaint();

			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Shape r : shapes) {
			g.setColor(Color.blue);
			((Graphics2D) g).fill((Shape) r);

			g.setColor(Color.black);
			((Graphics2D) g).draw((Shape) r);
		}

		if (dragging) {
			if (rdbtnSquare.isSelected()) {
				g.setColor(Color.black);
				((Graphics2D) g).draw((Shape) Rdrag);
			} else {
				g.setColor(Color.black);
				((Graphics2D) g).draw((Shape) Edrag);
			}

			dragging = false;
		}

	}
}
