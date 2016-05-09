package src.com.HAI.frame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyImplementation {
	
	PaintingPanel pp = new PaintingPanel();

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		System.out.println("Created GUI on EDT? "
				+ SwingUtilities.isEventDispatchThread());
		JFrame f = new JFrame("HAI Paint Program");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new PaintingPanel());
		f.setSize(1024, 720);
		f.setVisible(true);
	}

}
