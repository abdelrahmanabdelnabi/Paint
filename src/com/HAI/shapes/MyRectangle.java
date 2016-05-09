package src.com.HAI.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MyRectangle extends Rectangle {

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
			g2d.setColor(prop.getFill());
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