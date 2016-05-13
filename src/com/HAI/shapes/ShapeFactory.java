package src.com.HAI.shapes;

import java.awt.Polygon;
import java.awt.Shape;

public class ShapeFactory {
	static int x, y , width, height;
	
	static int[] xpoints, ypoints;
	
	static DrawingProperties prop;
	
	static int rotationAngle;
	
	public static void setXpoints(int[] xpoints) {
		ShapeFactory.xpoints = xpoints;
	}

	public static void setYpoints(int[] ypoints) {
		ShapeFactory.ypoints = ypoints;
	}

	public static void setProp(DrawingProperties prop) {
		ShapeFactory.prop = prop;
	}

	public static void setStartLocation(int xpoint, int ypoint){
		x = xpoint;
		y = ypoint;
	}
	
	public static void setRotationAngle(int rotationAngle) {
		ShapeFactory.rotationAngle = rotationAngle;
	}

	public static void setSize(int w, int h){
		width = w;
		height = h;
	}

	public ShapeFactory(DrawingProperties properties){
		prop = properties;
		
	}
	
	public Shape getShape(String request){
		Shape shape = null;
		
		if(request.equalsIgnoreCase("circle") || request.equalsIgnoreCase("ellipse")){
			shape = new MyEllipse(x, y, width, height);
			((MyEllipse)shape).setProp(prop);
		} else if(request.equalsIgnoreCase("rectangle") || request.equalsIgnoreCase("square") ){
			shape = new MyRectangle(x, y, width, height);
			((MyRectangle) shape).setProp(prop);
		} else if(request.equalsIgnoreCase("triangle")){
			shape = new MyTriangle(xpoints, ypoints, 3);
			((MyTriangle) shape).setProp(prop);
		}
		
		return shape;
	}

}
