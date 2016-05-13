package src.com.HAI.frame;

public interface ShapeDetailsListener {

	
	// subject to modification..
	// can be later changed to:
	// public void moveActionOccurred(), copyActionOccurred(), rotateActionOccurred(), ...
	public void moveBtnClicked();
	public void rotateBtnClicked();
	public void deleteBtnClicked();
	public void copyBtnClicked();
}
