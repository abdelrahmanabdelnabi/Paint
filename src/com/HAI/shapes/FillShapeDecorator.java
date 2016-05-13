package src.com.HAI.shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class FillShapeDecorator extends ShapeDecorator {
	private DrawingProperties prop = new DrawingProperties();
	
	public FillShapeDecorator(myShape decoratedShape) {
		super(decoratedShape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g2d) {
		// TODO Auto-generated method stub
		decoratedShape.draw(g2d);
		fill((Graphics2D)decoratedShape);
	}

	@Override
	public void fill(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(prop.getFill() );
		//g2d.fill(this);
		
	}
	
	@Override
	public void outline(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
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
