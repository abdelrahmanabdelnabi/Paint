package src.com.HAI.frame;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.*;


public class ShapeHandler {
	

	LinkedList<Shape> shapesList = new LinkedList<Shape>();
	Stack<LinkedList> shapes = new Stack<LinkedList>(); // a stack holding the shapes that are drawn
	Stack<LinkedList> shapes2 = new Stack<LinkedList>();	
	
	public void Undo() {
		if (!shapes.isEmpty()) {
			System.out.print("size before = " + shapes.size());
			shapes2.push(shapes.pop());
			System.out.println(" after = " + shapes.size());
		}
	}
	
	public void Redo() {
		if (!shapes2.isEmpty()) {
			System.out.print("size before = " + shapes2.size());
			shapes.push(shapes2.pop());
			System.out.println(" after = " + shapes2.size());
		}
	}
	public void addShape(Shape S){
		if(!shapes.isEmpty()){
		LinkedList<Shape> shapesList = shapes.peek();
		LinkedList<Shape> clonedList = (LinkedList<Shape>) shapesList.clone();
		clonedList.add(S);
		addList(clonedList);
		}else {
			shapesList.add(S);
			addList(shapesList);
		}
	}
	private void addList(LinkedList<Shape> list){
		shapes.push(list);
	}
	public LinkedList<Shape> getTop(){
		if(!shapes.isEmpty())
			return shapes.peek();
		else return new LinkedList<Shape>();
	}
	public void setTop(LinkedList list){
		shapes.push(list);
	}
	public void clear(){
		
		while(!shapes.isEmpty()){
			shapes.pop();
		}
	}
	
}
