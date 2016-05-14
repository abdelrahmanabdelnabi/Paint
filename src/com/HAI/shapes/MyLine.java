package src.com.HAI.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import src.com.HAI.frame.ShapeDetails;

public class MyLine extends Line2D.Double implements myShape {
	private DrawingProperties prop = new DrawingProperties();
	
	public MyLine(int x1, int x2, int y1, int y2){
		super(x1, y1, x2, y2);
	}
	
	public void updateDetailsPanel(ShapeDetails panel) {

		panel.setShape(this);

		panel.TypeLabel.setText("Oval");

		panel.XField.setText(x1 + "");
		panel.YField.setText(y1  + "");

		panel.lblX.setText("X1");
		panel.WidthField.setText(x2 + "");
		
		panel.lblY.setText("Y1");
		panel.HeightField.setText(y2 + "");
		
		panel.lblWidth.setText("X2");
		panel.lblHeight.setVisible(true);
		
		panel.lblHeight.setText("Y2");
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
			outline(g2d);
			g2d.draw(this);

		} finally {
			g2d.dispose();
		}
	}

	@Override
	public void fill(Graphics g) {
		// empty
		
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
	public void delete(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Graphics2D rotate(Graphics2D g, int degree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy() {
		// TODO Auto-generated method stub
		
	}
}
