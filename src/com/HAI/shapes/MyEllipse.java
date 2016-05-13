package src.com.HAI.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import src.com.HAI.frame.ShapeDetails;

public class MyEllipse extends Ellipse2D.Float implements myShape {

	private DrawingProperties prop = new DrawingProperties();

	public MyEllipse(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public MyEllipse() {
		super();
	}
	
	
	public void updateDetailsPanel(ShapeDetails panel) {

		panel.setShape(this);

		panel.TypeLabel.setText("Oval");

		panel.XField.setText((x + width / 2) + "");
		panel.YField.setText(y + height / 2 + "");

		panel.HeightField.setText(height + "");
		panel.WidthField.setText(width + "");
		panel.lblX.setText("X");
        panel.lblY.setText("Y");
        panel.lblWidth.setText("Width");
		panel.lblHeight.setVisible(true);
		panel.HeightField.setVisible(true);


	}

	public DrawingProperties getProp() {
		return prop;
	}

	public void setProp(DrawingProperties prop) {
		this.prop = prop;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		final Graphics2D g2d = (Graphics2D) g.create();

		try {

			fill(g2d);
			outline(g2d);
			g2d.draw(this);

		} finally {
			g2d.dispose();
		}
	}

	@Override
	public void fill(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(prop.getFill());
		g2d.fill(this);
	}

	@Override
	public void outline(Graphics2D g2d) {
		// TODO Auto-generated methdd stub
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

	public Shape makeRotatedShape(int degree) {
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(degree), this.x + this.width / 2, this.y + this.height / 2);
		
		

		// create a new transformed shape from this ellipse
		Shape rotatedShape = at.createTransformedShape(this);
		
		return rotatedShape;
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
