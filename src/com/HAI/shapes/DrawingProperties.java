package src.com.HAI.shapes;

import java.awt.BasicStroke;
import java.awt.Color;

public class DrawingProperties implements Cloneable{

	private Color outline = Color.black;
	private Color fill = java.awt.Color.white;
	private BasicStroke stroke = new BasicStroke(3);
	
	public DrawingProperties(){
		
	}
	
	public DrawingProperties(Color outline, Color fill, BasicStroke st){
		this(outline, fill);
		this.stroke = st;
	}
	
	public DrawingProperties(Color outline, Color fill){
		this.outline = outline;
		this.fill = fill;
	}

	public Color getOutline() {
		return outline;
	}

	public void setOutline(Color outline) {
		this.outline = outline;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public BasicStroke getStroke() {
		return stroke;
	}

	public void setStroke(BasicStroke stroke) {
		this.stroke = stroke;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
