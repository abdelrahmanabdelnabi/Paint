package src.com.HAI.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class MyRectangle extends Rectangle implements myShape{

	private DrawingProperties prop = new DrawingProperties();
	
	int rotationAngle = 0;

	public void modifyRotationAngle(int angle){
		this.rotationAngle += angle;
	}
	public MyRectangle() {
		super();
	}

	public MyRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
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
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(prop.getFill());
		((Graphics2D) g).fill(this);
	}

	@Override
	public Graphics2D rotate(Graphics2D g, int degree) {
		
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(degree), this.x + this.width / 2, this.y + this.height / 2);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.transform(at);
		
		return g2d;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void outline(Graphics g) {
		// TODO Auto-generated method stub
		((Graphics2D) g).setStroke(prop.getStroke());
		g.setColor(prop.getOutline());
	}

	@Override
	public void delete(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	

}
