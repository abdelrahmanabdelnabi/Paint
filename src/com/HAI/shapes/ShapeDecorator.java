package src.com.HAI.shapes;

import java.awt.Graphics2D;

public abstract class ShapeDecorator implements myShape {
	 protected myShape decoratedShape;

	   public ShapeDecorator(myShape decoratedShape){
	      this.decoratedShape = decoratedShape;
	   }

	   public void draw(Graphics2D g2d){
	      decoratedShape.draw(g2d);
	   }
	/*   public void fill(Graphics2D g2d ) {
		   decoratedShape.fill(g2d);
	   }
	   public void outline (Graphics2D g2d ) {
		   decoratedShape.outline(g2d);
	   }*/

}
