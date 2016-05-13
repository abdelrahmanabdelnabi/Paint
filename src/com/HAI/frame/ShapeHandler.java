package src.com.HAI.frame;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.*;


public class ShapeHandler {
	
	private static ShapeHandler ShapeHandlerInstance = new ShapeHandler();
	
	private ShapeHandler(){}
	
	Stack<LinkedList> shapes = new Stack<LinkedList>(); // a stack holding the shapes that are drawn
	Stack<LinkedList> shapes2 = new Stack<LinkedList>();	
	
	public static ShapeHandler getInstance(){
	      return ShapeHandlerInstance;
	}
	
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
		emptyStack();
		}else {
			LinkedList<Shape> shapesList = new LinkedList<Shape>();
			shapesList.add(S);
			addList(shapesList);
			emptyStack();
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
	private void emptyStack(){
		while(!shapes2.isEmpty())
			shapes2.pop();
	}
	public void cloneShape(Shape added, Shape Selected){
		LinkedList<Shape> list = getTop();
		LinkedList<Shape> clonedlist = (LinkedList<Shape>) list.clone();
		
		for(Shape X : clonedlist){
			if(Selected == X){
				clonedlist.remove(X);
				clonedlist.add(added);
				setTop(clonedlist);
				System.out.println("found match");
				break;
				
			}
		}
	}
	
}
