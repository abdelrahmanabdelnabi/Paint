package src.com.HAI.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

public class MyTriangle extends Polygon implements myShape {

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
			
            fill(g2d);
		    outline(g2d);
			
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

	@Override
	public void fill(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(prop.getFill() );
		g2d.fill(this);
	}

	@Override
	public void outline(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setStroke(prop.getStroke());
		g2d.setColor(prop.getOutline());
	}

	@Override
	public void resize(int height, int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(Graphics2D g, double degree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int x, int y, int height, int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Graphics2D copy(Graphics2D g) {
		// TODO Auto-generated method stub
		return null;
	}
}
