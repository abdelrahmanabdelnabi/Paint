package src.com.HAI.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import src.com.HAI.frame.ShapeDetails;

public class MyEllipse extends Ellipse2D.Float implements myShape {

	private DrawingProperties prop = new DrawingProperties();

	int rotationAngle = 0;
	static float scaleFactor = 1;

	public void modifyRotationAngle(int rotationAngle) {
		this.rotationAngle += rotationAngle;
		this.rotationAngle %= 360;
	}
	
	public void modifyscaleFactor(float scaleFactor) {
		this.scaleFactor = scaleFactor;
		this.width *= scaleFactor;
		this.height *= scaleFactor;
	}

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
		Graphics2D g2d = (Graphics2D) g.create();

		try {

			if (rotationAngle != 0) {
				g2d = rotate(g2d, rotationAngle);
			}
			
			fill(g2d);
			outline(g2d);
			g2d.draw(this);

		} finally {
			g2d.dispose();
		}
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(prop.getFill());
		((Graphics2D) g).fill(this);
	}

	@Override
	public void outline(Graphics g) {
		// TODO Auto-generated method stub
		((Graphics2D) g).setStroke(prop.getStroke());
		g.setColor(prop.getOutline());
	}

	@Override
	public void resize(int height, int width) {
		// TODO Auto-generated method stub

	}

	@Override
	public Graphics2D rotate(Graphics2D g, int degree) {
		// TODO Auto-generated method stub
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(degree), this.x + this.width / 2, this.y + this.height / 2);

		Graphics2D g2d = (Graphics2D) g;
		g2d.transform(at);

		return g2d;

	}

	public MyEllipse makeRotatedShape(int degree) {
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(degree), this.x + this.width / 2, this.y + this.height / 2);

		// create a new transformed shape from this ellipse
		Shape rotatedShape = at.createTransformedShape(this);

		Rectangle r = rotatedShape.getBounds();
		MyEllipse rotatedEllipse = new MyEllipse(r.x, r.y, r.width, r.height);
		return rotatedEllipse;
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
	public void delete(Graphics g) {
		// TODO Auto-generated method stub

	}

}
