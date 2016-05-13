package src.com.HAI.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import src.com.HAI.frame.ShapeDetails;

public class MyTriangle extends Polygon implements myShape {

	DrawingProperties prop = new DrawingProperties();
	
	int rotationAngle = 0;
	
	public void modifyRotationAngle(int rotationAngle) {
		this.rotationAngle += rotationAngle;
		this.rotationAngle %= 360;
	}
		
	public int calculateCenterX(){
		return (xpoints[0]+ xpoints[1]+ xpoints[2])/3;
	}
	
	public int calculateCenterY(){
		return (ypoints[0] + ypoints[1] + ypoints[2])/3;
	}
	
	public MyTriangle() {
		super();
	}

	public MyTriangle(int[] xpoints, int[] ypoints, int npoints) {
		super(xpoints, ypoints, npoints);
	}

	public void draw(Graphics g) {

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
	
	
	public void updateDetailsPanel(ShapeDetails panel) {
      
		panel.setShape(this);
		panel.TypeLabel.setText("Triangle");
        panel.lblX.setText("First point ");
        panel.lblY.setText("Second point");
        panel.lblWidth.setText("Third point");
		panel.XField.setText((xpoints[0]) +" , "+(ypoints[0])+ "");
		panel.YField.setText(xpoints[1]+" , "+ypoints[1] + "");
		panel.WidthField.setText(xpoints[2] + " , "+ ypoints[2]+"");
		panel.HeightField.setVisible(false);
		panel.lblHeight.setVisible(false);
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
		g.setColor(prop.getFill() );
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
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(degree),calculateCenterX(), calculateCenterY());

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
	public void delete(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		MyTriangle cloned = new MyTriangle(xpoints, ypoints, 3);
		
		cloned.setProp((DrawingProperties) prop.clone());
		
		cloned.rotationAngle = this.rotationAngle;
		
		return cloned;
	}
}
