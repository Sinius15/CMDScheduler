package com.sinius15.sceduler;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class GitFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextArea txtArea;
	private JTextPane console;
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
		appendToPane(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":  " + msg, Color.black);
	}
	
	public static void errLog(String msg) {
		appendToPane(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":  " + msg, Color.red);
	}
	
}
