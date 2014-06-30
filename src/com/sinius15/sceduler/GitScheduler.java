package com.sinius15.sceduler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GitScheduler  implements ActionListener, Runnable {
	
	private static final long serialVersionUID = 7168564315189023406L;
	private JTextArea txtArea;
	private JToggleButton startStopBtn;
	
	private Thread runner;
	private boolean isRunning;
	private JTextPane console;
	
	public GitScheduler() {

		
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
	
	public void log(String msg) {
		appendToPane(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":  " + msg, Color.black);
	}
	
	public void errLog(String msg) {
		appendToPane(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":  " + msg, Color.red);
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
	
	@Override
	public void run() {
		log("initializing...");
		String[] lines = txtArea.getText().split("\\n");
		if (lines.length < 1) {
			return;
		}
		
		int i = 0;
		while (isRunning) {
			String curLine = lines[i];
			log("now handeling '" + curLine + "'"); 
			if (curLine.toLowerCase().startsWith("sleep")) {   // sleep!!!
				curLine = curLine.replace("sleep", "").trim();
				int sleepTime;
				try {
					sleepTime = Integer.parseInt(curLine);
				} catch (NumberFormatException e) {
					errLog("Fatal Error!!");
					JOptionPane.showMessageDialog(this,
							"Invalid argument." + System.lineSeparator()
									+ "After 'sleep ' there must be a number!", "Fatal Error",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				try {
					log("Sleeping for " + sleepTime + "ms");
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {}
			} else if (curLine.toLowerCase().startsWith("cmd")) { // cmd!!!
				curLine = curLine.replace("cmd", "").trim();
				curLine = curLine.replace("%time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
				curLine = curLine.replace("%date", new SimpleDateFormat("dd.MM.yyy").format(new Date()));
				
				
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", curLine);
				builder.directory(new File(System.getProperty("user.dir")));
				builder.redirectErrorStream(true);
				try {
					Process p = builder.start();
					BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
					String line;
					while ((line = r.readLine()) != null) {
						errLog(line);
					}
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			i++;
			if (i == lines.length)
				i = 0;
		}
		
	}
	
	public static void main(String[] args) {
		new GitScheduler();
	}
	
}
