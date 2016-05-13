package src.com.HAI.shapes;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public interface myShape {
	public void draw(Graphics g);
	public void fill(Graphics g) ;
	public void outline (Graphics g ) ;
	public void resize (int height , int width) ;
	public void delete(Graphics g);
	public Graphics2D rotate (Graphics2D g  , int degree);
	public void move (int x , int y);
	public void copy();
}
