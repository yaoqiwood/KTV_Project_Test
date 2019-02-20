package music;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//音乐播放类
public class MusicUtil {
	private static MusicUtil musicUtil;

	public static MusicUtil getMusicUtil() {
		if (musicUtil == null) {
			musicUtil = new MusicUtil();
		}
		return musicUtil;
	}

	private Player player;

	public MusicUtil() {

	}

	public void PlayMusic(String path) {
		File file = new File(path);
		String filePath = file.getAbsolutePath();
		System.out.println("歌曲名："+file.getName());
		//如果歌曲正在播放，需要先关闭正在播放的音乐
		if (player != null) {
			player.stop();
			player.close();
			player = null;
		}
		MediaLocator fileMusic = new MediaLocator("file:///" + filePath);
		try {
			player = Manager.createPlayer(fileMusic);
			player.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoPlayerException e) {
			e.printStackTrace();
		}
	}

	public static String getWavTime(File file) {
		String WavTime = null;
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
			int i = (int) (clip.getMicrosecondLength() / 1000000D);
			int minuteTen = (i / 60) / 10;
			int minuteOne = (i / 60) % 10;
			int secondTen = (i % 60) / 10;
			int secondOne = (i % 60) % 10;
			WavTime = minuteTen + minuteOne + ":" +secondTen+secondOne;
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return WavTime;
	}

	public Player getPlayer() {
		return player;
	}
}
