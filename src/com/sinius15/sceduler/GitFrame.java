package com.sinius15.sceduler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GitFrame extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static GitFrame thisFrame;
	public static boolean isRunning;
	
	private JTextArea txtArea;
	private JTextPane console;
	private JToggleButton startStopBtn;
	
	private Thread runner = null;
	
	public GitFrame(){
		thisFrame = this;
		
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
	
	private void appendToPane(String msg, Color c) {
		msg += System.lineSeparator();
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		
		int len = console.getDocument().getLength();
		console.setCaretPosition(len);
		console.setCharacterAttributes(aset, false);
		console.replaceSelection(msg);
	}
	
	public static void log(String msg) {
		getFrame().appendToPane(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":  " + msg, Color.black);
	}
	
	public static void errLog(String msg) {
		getFrame().appendToPane(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":  " + msg, Color.red);
	}
	
	public static GitFrame getFrame(){
		return thisFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (startStopBtn.isSelected()) {
			// start running
			isRunning = true;
			txtArea.setEditable(false);
			runner = new Thread(this);
			runner.start();
			
			startStopBtn.setText("Stop Excecuting");
		} else {
			// stop running
			isRunning = false;
			if (!runner.isInterrupted())
				runner.interrupt();
			runner = null;
			
			txtArea.setEditable(true);
			startStopBtn.setText("Start Excecuting");
		}
	}
}
