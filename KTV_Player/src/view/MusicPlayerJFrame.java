package view;

import javax.swing.*;
import java.awt.*;

//“Ù¿÷≤•∑≈∆˜Frame
public class MusicPlayerJFrame extends JFrame {
	private static MusicPlayerJFrame musicPlayerJFrame;
	public static MusicPlayerJFrame getMusicPlayerJFrame(){
		if (musicPlayerJFrame == null){
			musicPlayerJFrame = new MusicPlayerJFrame();
		}
		return musicPlayerJFrame;
	}

	private MusicPlayerJPanel musicPlayerJPanel = new MusicPlayerJPanel();
	public MusicPlayerJFrame() {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("“Ù¿÷≤•∑≈∆˜");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.add(musicPlayerJPanel);
	}

	public MusicPlayerJPanel getMusicPlayerJPanel() {
		return musicPlayerJPanel;
	}
}
