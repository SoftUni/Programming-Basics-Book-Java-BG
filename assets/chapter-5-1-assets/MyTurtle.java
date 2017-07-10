package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.aplu.turtle.Turtle;
import ch.aplu.turtle.TurtleFrame;

/**
 *
 * @author Cvetan Georgiev
 * Jul 7, 2017
 */

public class MyTurtle {

	private JPanel controlPanel;
	private JPanel panel;

	private TurtleFrame myTFrame;
	private Turtle turtle;
	private String shape = "";

	public enum Shape {
	    Triangles
	}

	private final Thread t;
	{
		t = new Thread(new Runnable() {
			public void run() {
				while (!Thread.interrupted()) {
					try {
						switch (shape) {
						case "Triangles":
							drawTriangle();
							break;

						default:
							break;
						}
							Thread.sleep(1000);
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
		});
		t.setDaemon(true);
		t.setName("Draw");
		t.start();
	}

	private MyTurtle(){
	}

	public static void main (String[] args) {
		MyTurtle myExample = new MyTurtle();
		myExample.prepareGUI();
	}

	private void resetTurtle() {
          // TODO: Update this method to Reset Turtle
	}

	private void showTurtle() {
          // TODO: Update this method to Hide / Show Turtle
	}

	private void drawTriangle() {
          // TODO: Update this method Turtle to Draw a Triangle
	}

	private void prepareGUI() {

		controlPanel = new JPanel();
		panel = new JPanel();

		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JButton btn1 = new JButton("Draw");
		btn1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	shape = Shape.Triangles.toString();
	        }
	    });

		JButton btn2 = new JButton("Reset");
		btn2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	resetTurtle();
	        }
	    });

		JButton btn3 = new JButton("Hide / Show");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTurtle();
			}
		});

		List<JButton> bList = new ArrayList<>();
		bList.add(btn1);
		bList.add(btn2);
		bList.add(btn3);

		Dimension d = new Dimension(150, 40);
		// to apply setting to All Buttons
		for (JButton jButton : bList) {
			jButton.setMinimumSize(d);
		}

		GroupLayout.SequentialGroup bSeqGroup = layout.createSequentialGroup();
		GroupLayout.ParallelGroup bParGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);

		for (JButton jButton : bList) {
			bParGroup.addComponent(jButton);
			bSeqGroup.addComponent(jButton);
		}

		layout.setHorizontalGroup(bParGroup);

		layout.setVerticalGroup(bSeqGroup);

		panel.setLayout(layout);
		controlPanel.add(panel);

		myTFrame = new TurtleFrame("MyTurtles", 800, 600);

		myTFrame.add(controlPanel, BorderLayout.EAST);

		turtle = new Turtle(myTFrame, Color.green);
		turtle.hideTurtle();
		turtle.setLineWidth(5);
		myTFrame.setVisible(true);
		myTFrame.repaint();

	}

}
