package src.com.HAI.frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame implements ShapeDetailsListener {

	private PaintingPanel panel;
	private JPanel holderPanel; // a panel to hold all the components of the
								// frame because its better to place components
								// on a JPanel than directly on the mainFrame
	public ShapeDetails detailsPanel;

	private JButton btnUndo;
	private JButton btnOutlineColor;
	private JButton btnFillColor;
	private JButton btnRedo;

	private JSlider thickness;

	BasicStroke bs = new BasicStroke(3); // the thickness of the position marker
											// on the ruler

	ButtonGroup bg = new ButtonGroup();

	public JRadioButton rdbtnSquare;
	public JRadioButton rdbtnCircle;
	public JRadioButton rdbtnTriangle;
	public JRadioButton rdbtnEllipse;
	public JRadioButton rdbtnRectangle;
	public JRadioButton rdbtnSelect;
	public JRadioButton rdbtnLine;

	private JTextField XField;
	private JTextField YField;
	private JLabel lblY;
	private JLabel lblThickness;

	private JCheckBox HLChbx;
	Rectangle r;

	private int currX, currY; // the current x and y coordinates of the mouse on
								// the panel

	public static final int GAP = 3; // the gap between the panel and the
										// horizontal/vertical ruler in pixels
	public static final int TBTHICKNESS = 25; // the thickness of the title bar
												// of the main frame
	public static final int RULERTHICKNESS = 10;

	public static final int ACCURACY = 25; // the number of pixels between each
											// marker on the ruler

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
		super("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1150, 720);
		getContentPane().setLayout(null);

		panel = new PaintingPanel();
		panel.setSize(800, 575);
		panel.setLocation(325, 100);
		panel.setBorder(new EtchedBorder());
		panel.setLayout(new BorderLayout(0, 0));

		r = panel.getBounds();

		getContentPane().add(panel);
		panel.setMainFrame(this);

		detailsPanel = new ShapeDetails();
		detailsPanel.lblY.setBounds(12, 96, 101, 15);
		detailsPanel.lblX.setBounds(12, 62, 101, 15);
		detailsPanel.HeightField.setBounds(120, 155, 80, 19);
		detailsPanel.WidthField.setBounds(120, 128, 80, 19);
		detailsPanel.YField.setBounds(120, 96, 80, 19);
		detailsPanel.XField.setBounds(120, 62, 80, 19);
		detailsPanel.TypeLabel.setBounds(58, 12, 117, 15);

		getContentPane().add(detailsPanel);
		detailsPanel.setVisible(false);

		detailsPanel.setBounds(38, 275, detailsPanel.getPreferredSize().width, detailsPanel.getPreferredSize().height);
		detailsPanel.setLayout(null);

		detailsPanel.RegisterListener(this);

		initButtons();

		// holderPanel = new HolderPanel();
		// getContentPane().add(holderPanel);
		// holderPanel.setSize(this.getSize());
		// holderPanel.setBackground(Color.yellow);
	}

	private void initButtons() {

		ImageIcon circle = new ImageIcon(getClass().getResource("circle-outline.png"));
		ImageIcon Ellipse = new ImageIcon(getClass().getResource("circle-outline.png"));
		ImageIcon Rectangle = new ImageIcon(getClass().getResource("circle-outline.png"));
		ImageIcon Square = new ImageIcon(getClass().getResource("circle-outline.png"));
		ImageIcon Undo = new ImageIcon(getClass().getResource("back_undo.png"));
		ImageIcon Redo = new ImageIcon(getClass().getResource("redo_forward.png"));
		ImageIcon Erase = new ImageIcon(getClass().getResource("circle-outline.png"));
		ImageIcon Fill = new ImageIcon(getClass().getResource("color_fill.png"));
		ImageIcon outLine = new ImageIcon(getClass().getResource("paint-brush.png"));

		rdbtnCircle = new JRadioButton("circle");
		rdbtnCircle.setBounds(29, 84, 67, 23);
		getContentPane().add(rdbtnCircle);

		rdbtnCircle.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {

				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					rdbtnCircle.setBackground(null);
				}

			}
		});

		rdbtnSquare = new JRadioButton("square");
		rdbtnSquare.setBounds(29, 111, 102, 23);
		getContentPane().add(rdbtnSquare);

		rdbtnTriangle = new JRadioButton("triangle");
		rdbtnTriangle.setBounds(29, 138, 102, 23);
		getContentPane().add(rdbtnTriangle);

		rdbtnEllipse = new JRadioButton("ellipse");
		rdbtnEllipse.setBounds(29, 165, 102, 23);
		getContentPane().add(rdbtnEllipse);

		rdbtnRectangle = new JRadioButton("rectangle");
		rdbtnRectangle.setBounds(29, 192, 102, 23);
		getContentPane().add(rdbtnRectangle);

		rdbtnSelect = new JRadioButton("Select");
		rdbtnSelect.setBounds(158, 181, 77, 23);
		rdbtnSelect.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(!rdbtnSelect.isSelected()){
					detailsPanel.setVisible(false);
				}

			}
		});
		getContentPane().add(rdbtnSelect);
		
		rdbtnLine = new JRadioButton("Line");
		rdbtnLine.setBounds(29, 219, 89, 23);
		getContentPane().add(rdbtnLine);

		bg.add(rdbtnSquare);
		bg.add(rdbtnCircle);
		bg.add(rdbtnTriangle);
		bg.add(rdbtnEllipse);
		bg.add(rdbtnRectangle);
		bg.add(rdbtnSelect);
		bg.add(rdbtnLine);
		
		rdbtnCircle.setSelected(true);

		btnUndo = new JButton(Undo);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.undoActionPerformed();

			}
		});
		btnUndo.setBounds(38, 12, 24, 23);
		getContentPane().add(btnUndo);

		btnRedo = new JButton(Redo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.redoActionPerformed();

			}
		});
		btnRedo.setBounds(72, 12, 24, 23);
		getContentPane().add(btnRedo);

		thickness = new JSlider();
		thickness.setValue(3);
		thickness.setMaximum(8);
		thickness.setMinimum(1);
		thickness.setToolTipText("Drawing Pen Thickness");
		thickness.setBounds(360, 27, 200, 16);
		getContentPane().add(thickness);
		thickness.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				panel.thicknessChanged(thickness.getValue());

			}
		});

		btnOutlineColor = new JButton(outLine);
		btnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = null;
				newColor = JColorChooser.showDialog(null, "Change Outline Color", newColor);

				btnOutlineColor.setBackground(newColor);

				panel.outlineColorChanged(newColor);
			}
		});
		btnOutlineColor.setBounds(580, 11, 192, 25);
		getContentPane().add(btnOutlineColor);

		btnFillColor = new JButton(Fill);
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Color newColor = null;
				newColor = JColorChooser.showDialog(null, "Change Fill Color", newColor);

				btnFillColor.setBackground(newColor);

				panel.fillColorChanged(newColor);
			}
		});
		btnFillColor.setBounds(783, 12, 192, 25);
		getContentPane().add(btnFillColor);

		XField = new JTextField();
		XField.setEditable(false);
		XField.setBounds(205, 100, 53, 19);
		getContentPane().add(XField);
		XField.setColumns(10);

		YField = new JTextField();
		YField.setEditable(false);
		YField.setColumns(10);
		YField.setBounds(205, 131, 53, 19);
		getContentPane().add(YField);

		JLabel lblX = new JLabel("X:");
		lblX.setBounds(174, 100, 29, 15);
		getContentPane().add(lblX);

		lblY = new JLabel("Y:");
		lblY.setBounds(174, 131, 29, 15);
		getContentPane().add(lblY);

		lblThickness = new JLabel("Thickness");
		lblThickness.setBounds(259, 12, 89, 23);
		getContentPane().add(lblThickness);

		HLChbx = new JCheckBox("Helping Lines");
		HLChbx.setBounds(159, 154, 129, 23);
		getContentPane().add(HLChbx);
		
		JButton btnNewButton = new JButton("Clear All");
		btnNewButton.setBounds(158, 208, 115, 23);

		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane optionPane = new JOptionPane("Are you sure to Clear all ?", JOptionPane.QUESTION_MESSAGE,
						JOptionPane.YES_NO_OPTION);
				int choice = JOptionPane.showConfirmDialog(optionPane, "Are you sure to Clear all ?");
				if (choice == JOptionPane.YES_OPTION) {
					panel.clearActionPerformed();
				}
			}
		});
		

	}

	public void updateCoordinates(int x, int y) {
		currX = x;
		currY = y;
		XField.setText(x + "");
		YField.setText(y + "");

		// repaint the rulers
		repaint(r.x, r.y + TBTHICKNESS - 2 * RULERTHICKNESS - GAP, r.width, 2 * RULERTHICKNESS);
		repaint(r.x - 2 * RULERTHICKNESS - GAP, r.y + TBTHICKNESS, 2 * RULERTHICKNESS, r.height);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawHorizontalRuler(g);
		drawVerticalRuler(g);

		if (currX != 0) {
			drawCurrentMarkers(g);
			if (HLChbx.isSelected())
				drawHelpingLines(g);
		}
	}

	private void drawHorizontalRuler(Graphics g) {
		// TODO: implement this method
		// u might find drawline() and getBounds()/getSize()/getLocation() for
		// the Panel helpful
		// u should also use a for loop to draw equidistant markers on the line

		final Graphics2D g2d = (Graphics2D) g.create();

		try {
			g2d.setColor(Color.white);
			g2d.fillRect(r.x, r.y + TBTHICKNESS - RULERTHICKNESS - GAP, r.width, RULERTHICKNESS);

			g2d.setColor(Color.black);
			g2d.drawRect(r.x, r.y + TBTHICKNESS - RULERTHICKNESS - GAP, r.width, RULERTHICKNESS);

			for (int i = 0; i <= r.width; i += ACCURACY) {
				// draw a marker every 20 pixels on the line
				g2d.drawLine(r.x + i, r.y + TBTHICKNESS - GAP - RULERTHICKNESS, r.x + i, r.y + TBTHICKNESS - GAP);
				g2d.drawString(i / ACCURACY + "", r.x + i - 5, r.y + TBTHICKNESS - GAP - RULERTHICKNESS - 5);
			}

		} finally {
			g2d.dispose();
		}

	}

	private void drawVerticalRuler(Graphics g) {

		final Graphics2D g2d = (Graphics2D) g.create();

		try {
			g2d.setColor(Color.white);
			g2d.fillRect(r.x - RULERTHICKNESS - GAP, r.y + TBTHICKNESS, RULERTHICKNESS, r.height);

			g2d.setColor(Color.black);
			g2d.drawRect(r.x - RULERTHICKNESS - GAP, r.y + TBTHICKNESS, RULERTHICKNESS, r.height);

			for (int i = 0; i <= r.height; i += ACCURACY) {
				// draw a marker every 20 pixels on the line
				g2d.drawLine(r.x - RULERTHICKNESS - GAP, r.y + i + TBTHICKNESS, r.x - GAP, r.y + i + TBTHICKNESS);
				g2d.drawString(i / ACCURACY + "", r.x - GAP - RULERTHICKNESS - 20, r.y + i + TBTHICKNESS + 3);
			}

		} finally {
			g2d.dispose();
		}
	}

	private void drawCurrentMarkers(Graphics g) {
		final Graphics2D g2d = (Graphics2D) g.create();

		try {
			g2d.setStroke(bs);
			g2d.drawLine(r.x + currX, r.y + TBTHICKNESS - GAP - RULERTHICKNESS, r.x + currX, r.y + TBTHICKNESS - GAP);
			g2d.drawLine(r.x - RULERTHICKNESS - GAP, r.y + currY + TBTHICKNESS, r.x - GAP, r.y + currY + TBTHICKNESS);

		} finally {
			g2d.dispose();
		}
	}

	private void drawHelpingLines(Graphics g) {
		final Graphics2D g2d = (Graphics2D) g.create();

		try {
			g2d.drawLine(r.x + currX, r.y + TBTHICKNESS, r.x + currX, r.y + r.height);
			g2d.drawLine(r.x, r.y + currY + TBTHICKNESS, r.x + r.width, r.y + currY + TBTHICKNESS);
		} finally {
			g2d.dispose();
		}
	}

	@Override
	public void moveBtnClicked() {
		panel.moveActionPerformed();

	}

	@Override
	public void rotateBtnClicked() {
		// TODO Auto-generated method stub
		String input = JOptionPane.showInputDialog("Please enter the degree");
		int degree = 0;
		try {
			degree = Integer.parseInt(input);
			
		} catch (NumberFormatException e){
			


			JOptionPane.showMessageDialog(this, "INPUT IS NOT AN INTEGER", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
		}

		panel.rotateActionPerformed(degree);

	}

	public void newShapeSelected() {
		detailsPanel.setVisible(true);
	}

	@Override
	public void deleteBtnClicked() {
		panel.deleteActionPerformed();
	}

	@Override
	public void copyBtnClicked() {
		panel.copyActionPerformed();
		
	}

	@Override
	public void fillBtnClicked(Color c) {
		// TODO Auto-generated method stub
		panel.shapeFillChanged(c);
		
	}

	@Override
	public void outlineBtnClicked(Color c) {
		// TODO Auto-generated method stub
		panel.shapeOutlineChanged(c);
	}

	@Override
	public void resizeBtnClicked() {
		String input = JOptionPane.showInputDialog("Enter Scale Factor");
		float scale = 1;
		try {
			scale = Float.parseFloat(input);
			
		} catch (NumberFormatException e){
			


			JOptionPane.showMessageDialog(this, "INPUT IS NOT A Float", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
		}
		panel.resizeActionPerformed(scale);
		
	}
}

class HolderPanel extends JPanel {

	public HolderPanel() {
		super();
	}

	// TODO: override the paintComponent() method

}
