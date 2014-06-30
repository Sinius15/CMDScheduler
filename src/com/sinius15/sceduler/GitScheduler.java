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

public class GitScheduler implements ActionListener, Runnable {
	
	private JTextArea txtArea;
	private JToggleButton startStopBtn;
	
	private Thread runner;
	
	public GitScheduler() {

		
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
	
	
	
	public static void main(String[] args) {
		new GitScheduler();
	}
	
}
