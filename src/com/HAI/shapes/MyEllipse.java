package src.com.HAI.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import src.com.HAI.frame.ShapeDetails;

public class MyEllipse extends Ellipse2D.Float{

	private DrawingProperties prop = new DrawingProperties();
	
	public MyEllipse(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public MyEllipse() {
		super();
	}

	public void draw(Graphics g){
		
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
	
	public void updateDetailsPanel(ShapeDetails panel){
		
		panel.setShape(this);
		
		panel.TypeLabel.setText("Oval");
		
		panel.XField.setText((x + width/2) + "");
		panel.YField.setText(y + height/2 + "");
		
		panel.HeightField.setText(height + "");
		panel.WidthField.setText(width + "");
		
	}

	public DrawingProperties getProp() {
		return prop;
	}

	public void setProp(DrawingProperties prop) {
		this.prop = prop;
	}
	
}

