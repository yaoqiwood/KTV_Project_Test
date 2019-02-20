package view;

import javax.swing.*;
import java.awt.*;


//传输进度调窗口
public class ProgressBarFrame extends JFrame {
	private static ProgressBarFrame progressBarFrame;
	public static ProgressBarFrame getProgressBarFrame() {
		if (progressBarFrame == null) {
			progressBarFrame = new ProgressBarFrame();
		}
		return progressBarFrame;
	}

	private JPanel ProgressJPanel = new JPanel();
	private JProgressBar jProgressBar = new JProgressBar();

	public ProgressBarFrame() {
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("进度");
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(false);


		this.ProgressJPanel.setLayout(null);
		this.ProgressJPanel.setBounds(0,0,400,200);
		this.jProgressBar.setBounds(50,75,300,30);
		ProgressJPanel.add(jProgressBar);
		this.add(ProgressJPanel);
	}

	public JProgressBar getjProgressBar() {
		return jProgressBar;
	}
}
