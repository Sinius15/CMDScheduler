package com.sinius15.sceduler;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class GitFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextArea txtArea;
	private JToggleButton startStopBtn;
	
	public GitFrame(){
		setTitle("Git Scheduler");
		setVisible(true);
		setBounds(0, 0, 574, 555);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startStopBtn = new JToggleButton("Start Excecuting");
		startStopBtn.addActionListener(this);
		getContentPane().add(startStopBtn, BorderLayout.SOUTH);
		
		txtArea = new JTextArea();
		txtArea.setText("cmd git add -A \ncmd git commit -m \" auto commit on %date - %time\" \nsleep 1000");
		getContentPane().add(txtArea, BorderLayout.NORTH);
		
		console = new JTextPane();
		console.setEditable(true);
		JScrollPane scroller = new JScrollPane(console);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		getContentPane().add(scroller, BorderLayout.CENTER);
	}
	
}
