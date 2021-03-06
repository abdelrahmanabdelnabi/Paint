package src.com.HAI.frame;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ShapeDetails extends JPanel {
	public JTextField XField;
	public JTextField YField;
	public JTextField WidthField;
	public JTextField HeightField;
	public JLabel TypeLabel;
	private JButton ResizeBtn;
	private JButton CopyBtn;
	private JButton MoveBtn;
	private JButton RotateBtn;
	private JButton FillBtn;
	private JButton OutlineBtn;
	private JButton DeleteBtn;
	public JLabel lblX;
	public JLabel lblY;
	public JLabel lblWidth;
	public JLabel lblHeight;
	public JLabel lblFillColor;
	public JLabel lblOutlilneColor;

	private Shape shape;

	private List<ShapeDetailsListener> listeners;

	/**
	 * Create the panel.
	 */
	public ShapeDetails() {
		listeners = new ArrayList<ShapeDetailsListener>();

		setLayout(null);

		setBorder(new EtchedBorder());

		TypeLabel = new JLabel("Shape Type");
		TypeLabel.setBounds(55, 12, 101, 15);
		add(TypeLabel);

		lblX = new JLabel("X");
		lblX.setBounds(12, 62, 30, 15);
		add(lblX);

		lblY = new JLabel("Y");
		lblY.setBounds(12, 96, 30, 15);
		add(lblY);

		lblWidth = new JLabel("Width");
		lblWidth.setBounds(12, 128, 70, 15);
		add(lblWidth);

		lblHeight = new JLabel("Height");
		lblHeight.setBounds(12, 155, 70, 15);
		add(lblHeight);

		lblFillColor = new JLabel("Fill Color");
		lblFillColor.setBounds(12, 193, 70, 15);
		add(lblFillColor);

		lblOutlilneColor = new JLabel("Outlilne Color");
		lblOutlilneColor.setBounds(12, 235, 101, 15);
		add(lblOutlilneColor);

		CopyBtn = new JButton("Copy");
		CopyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (ShapeDetailsListener listener : listeners) {
					listener.copyBtnClicked();
				}
			}
		});
		CopyBtn.setBounds(20, 281, 80, 25);
		add(CopyBtn);
		CopyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// notifyAllListeners();
			}
		});

		MoveBtn = new JButton("Move");
		MoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (ShapeDetailsListener listener : listeners) {
					listener.moveBtnClicked();
				}
			}
		});
		MoveBtn.setBounds(112, 281, 88, 25);
		add(MoveBtn);

		RotateBtn = new JButton("Rotate");
		RotateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (ShapeDetailsListener listener : listeners) {
					listener.rotateBtnClicked();
				}
			}
		});
		RotateBtn.setBounds(20, 318, 82, 25);
		add(RotateBtn);

		DeleteBtn = new JButton("Delete");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (ShapeDetailsListener listener : listeners) {
					listener.deleteBtnClicked();
				}
			}
		});
		DeleteBtn.setBounds(112, 318, 88, 25);
		add(DeleteBtn);
		
		ResizeBtn = new JButton("Resize");
		ResizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (ShapeDetailsListener listener : listeners) {
					listener.resizeBtnClicked();
				}
			}
		});
		ResizeBtn.setBounds(112, 355, 88, 25);
		add(ResizeBtn);

		XField = new JTextField();
		XField.setBounds(120, 62, 80, 19);
		add(XField);
		XField.setColumns(10);

		YField = new JTextField();
		YField.setColumns(10);
		YField.setBounds(120, 96, 80, 19);
		add(YField);

		WidthField = new JTextField();
		WidthField.setColumns(10);
		WidthField.setBounds(120, 128, 80, 19);
		add(WidthField);

		HeightField = new JTextField();
		HeightField.setColumns(10);
		HeightField.setBounds(120, 155, 80, 19);
		add(HeightField);

		FillBtn = new JButton("Change");
		FillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color chosenColor = JColorChooser.showDialog(getParent(), "Choose Fill Color", Color.white);

				for (ShapeDetailsListener listener : listeners) {
					listener.fillBtnClicked(chosenColor);
				}

			}
		});
		FillBtn.setBounds(112, 188, 88, 25);
		add(FillBtn);

		OutlineBtn = new JButton("Change");
		OutlineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color chosenColor = JColorChooser.showDialog(getParent(), "Choose Outline Color", Color.white);

				for (ShapeDetailsListener listener : listeners) {
					listener.outlineBtnClicked(chosenColor);
				}

			}
		});
		OutlineBtn.setBounds(112, 230, 88, 25);
		add(OutlineBtn);

	}

	public void RegisterListener(ShapeDetailsListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(220, 400);
	}

}
