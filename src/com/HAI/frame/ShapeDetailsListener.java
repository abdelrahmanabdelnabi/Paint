package src.com.HAI.frame;

import java.awt.Color;

public interface ShapeDetailsListener {

	
	// subject to modification..
	// can be later changed to:
	// public void moveActionOccurred(), copyActionOccurred(), rotateActionOccurred(), ...
	public void moveBtnClicked();
	public void rotateBtnClicked();
	
	public void deleteBtnClicked();
	
	public void copyBtnClicked();
	
	public void fillBtnClicked(Color c);
	
	public void outlineBtnClicked(Color c);
}
