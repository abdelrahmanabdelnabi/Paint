package com.HAI.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class MyTriangle extends Polygon {

	DrawingProperties prop = new DrawingProperties();
	
	
	public MyTriangle() {
		super();
	}

	public MyTriangle(int[] xpoints, int[] ypoints, int npoints) {
		super(xpoints, ypoints, npoints);
	}

	public void draw(Graphics g) {

		final Graphics2D g2d = (Graphics2D) g.create();
		try {
			g2d.setColor(prop.getFill() );
			g2d.fill(this);

			g2d.setStroke(prop.getStroke());
			g2d.setColor(prop.getOutline());
			g2d.draw(this);

		} finally {
			g2d.dispose();
		}
	}

	public DrawingProperties getProp() {
		return prop;
	}

	public void setProp(DrawingProperties prop) {
		this.prop = prop;
	}
	
	
}
