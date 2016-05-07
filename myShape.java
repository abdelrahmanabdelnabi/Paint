

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public interface myShape {
	public void draw(Graphics2D g);
	public void fill(Graphics2D g , Color c) ;
	public void resize (int height , int width) ;
	public void delete(Graphics2D g);
	public void rotate (Graphics2D g  , double degree);
	public void move (int x , int y , int height , int width);
	public Graphics2D copy (Graphics2D g) ;
	public void outline (Graphics2D g , Color c) ;
	
}
