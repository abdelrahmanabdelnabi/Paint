package src.com.HAI.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {

	private PaintingPanel panel;
	private JPanel holderPanel; // a panel to hold all the components of the frame because its better to place components on a JPanel than directly on the mainFrame

	private JButton btnUndo;
	private JButton btnOutlineColor;
	private JButton btnFillColor;

	private JSlider thickness;

	ButtonGroup bg = new ButtonGroup();

	public JRadioButton rdbtnSquare;
	public JRadioButton rdbtnCircle;
	public JRadioButton rdbtnTriangle;
	private JTextField XField;
	private JTextField YField;
	private JLabel lblY;
	private JLabel lblThickness;
	
	public static final int GAP = 0; // the gap between the panel and the horizontal/vertical ruler in pixels 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 720);
		getContentPane().setLayout(null);
		
		
		panel = new PaintingPanel();
		panel.setSize(813, 585);
		panel.setLocation(197, 96);
		panel.setBorder(new EtchedBorder());
		panel.setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(panel);
		panel.setMainFrame(this);

		initButtons();
		
//		holderPanel = new HolderPanel();
//		getContentPane().add(holderPanel);
//		holderPanel.setSize(this.getSize());
//		holderPanel.setBackground(Color.yellow);
	}

	private void initButtons() {
		
		rdbtnCircle = new JRadioButton("circle");
		rdbtnCircle.setBounds(67, 175, 71, 23);
		getContentPane().add(rdbtnCircle);

		rdbtnSquare = new JRadioButton("square");
		rdbtnSquare.setBounds(67, 203, 89, 23);
		getContentPane().add(rdbtnSquare);

		rdbtnTriangle = new JRadioButton("triangle");
		rdbtnTriangle.setBounds(67, 230, 89, 23);
		getContentPane().add(rdbtnTriangle);

		bg.add(rdbtnSquare);
		bg.add(rdbtnCircle);
		bg.add(rdbtnTriangle);

		
		rdbtnCircle.setSelected(true);

		btnUndo = new JButton("undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnUndo.setBounds(676, 10, 89, 23);
		getContentPane().add(btnUndo);

		thickness = new JSlider();
		thickness.setValue(3);
		thickness.setMaximum(8);
		thickness.setMinimum(1);
		thickness.setToolTipText("Drawing Pen Thickness");
		thickness.setBounds(306, 45, 200, 16);
		getContentPane().add(thickness);
		thickness.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				panel.thicknessChanged(thickness.getValue());

			}
		});

		btnOutlineColor = new JButton("change outline color");
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r, g, b;
				r = (int) (Math.random() * 255);
				g = (int) (Math.random() * 255);
				b = (int) (Math.random() * 255);
				Color newColor = new Color(r, g, b);

				btnOutlineColor.setBackground(newColor);

				panel.outlineColorChanged(newColor);
			}
		});
		btnOutlineColor.setBounds(544, 45, 192, 25);
		getContentPane().add(btnOutlineColor);

		btnFillColor = new JButton("change fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r, g, b;
				r = (int) (Math.random() * 255);
				g = (int) (Math.random() * 255);
				b = (int) (Math.random() * 255);
				Color newColor = new Color(r, g, b);

				btnFillColor.setBackground(newColor);

				panel.fillColorChanged(newColor);
			}
		});
		btnFillColor.setBounds(762, 45, 146, 25);
		getContentPane().add(btnFillColor);
		
		XField = new JTextField();
		XField.setEditable(false);
		XField.setBounds(98, 96, 81, 19);
		getContentPane().add(XField);
		XField.setColumns(10);
		
		YField = new JTextField();
		YField.setEditable(false);
		YField.setColumns(10);
		YField.setBounds(98, 127, 81, 19);
		getContentPane().add(YField);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(67, 96, 29, 15);
		getContentPane().add(lblX);
		
		lblY = new JLabel("Y:");
		lblY.setBounds(67, 127, 29, 15);
		getContentPane().add(lblY);
		
		lblThickness = new JLabel("Thickness");
		lblThickness.setBounds(209, 38, 89, 23);
		getContentPane().add(lblThickness);
	}
	
	public void updateCoordinates(int x, int y){
		XField.setText(x + "");
		YField.setText(y + "");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//drawHorizontalRuler(g);
		//drawVerticalRuler(g);
		
	}
	
	private void drawHorizontalRuler(Graphics g){
		// TODO: implement this method
		// u might find drawline() and getBounds()/getSize()/getLocation() for the Panel helpful
		// u should also use a for loop to draw equidistant markers on the line
		
		Rectangle r = panel.getBounds();
		
		g.drawLine(r.x, r.y - GAP, r.x + r.width, r.y - GAP);
		
		for(int i = 0; i < r.width; i += 20){
			// draw a marker every 20 pixels on the line
			g.drawLine(r.x + i, r.y - GAP - 2, r.x + i, r.y - GAP + 2);
		}
	}
	
	private void drawVerticalRuler(Graphics g){
		// TODO: implement this method
	}
}


class HolderPanel extends JPanel{
	
	public HolderPanel(){
		super();
	}
	
	// TODO: override the paintComponent() method
	
}
