package src.com.HAI.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import src.com.HAI.frame.ShapeDetails;

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
		return g;
		
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
