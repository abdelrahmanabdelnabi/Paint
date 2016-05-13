package src.com.HAI.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MyRectangle extends Rectangle implements myShape{

	private DrawingProperties prop = new DrawingProperties();

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
	public void fill(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(prop.getFill());
		g2d.fill(this);
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

	@Override
	public void outline(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setStroke(prop.getStroke());
		g2d.setColor(prop.getOutline());
	}
	
	

}
