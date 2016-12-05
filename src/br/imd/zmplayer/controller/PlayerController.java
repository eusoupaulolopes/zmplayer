package br.imd.zmplayer.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * Classe responsável por controlar o player de música.
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 */
public class PlayerController extends FXMLPlayerController{

	private static PlayerController pl;

	private ChangeListener<Duration> progressChangeListener = new ChangeListener<Duration>() {
		String saida = "Parado";
		@Override
		public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
				Duration newValue) {
			String source = getMediaPlayer().getMedia().getSource();
			source = source.substring(0, source.length() - ".mp3".length());
			source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
			saida = "Tocando: "+source;
			
			System.out.println(getMediaPlayer().getCurrentTime().toSeconds());
		}
		
	};
	
	

	public ChangeListener<Duration> getProgressChangeListener() {
		return progressChangeListener;
	}

	private PlayerController() {

	}

	public static PlayerController getInstance() {
		if (pl == null) {
			synchronized (PlayerController.class) {
				if (pl == null) {
					pl = new PlayerController();
				}
			}
		}
		return pl;
	}

	public Media media;
	public MediaPlayer mediaPlayer;
	public Status status;

	public void tocar(File file) {
		media = new Media(file.toURI().toString());
		System.out.println(file.toURI());
		MediaPlayer mPlayer = new MediaPlayer(media);
		mediaControl(mPlayer);
	}

	public void tocar(List<File> files) {
		List<MediaPlayer> mp = new ArrayList<MediaPlayer>();
		for (File fl : files) {
			Media media = new Media(fl.toURI().toString());
			mp.add(new MediaPlayer(media));
		}
		mediaControl(mp);

	}

	public void parar() {
		if (this.mediaPlayer != null) {
			if (this.mediaPlayer.getStatus() == Status.PLAYING || this.mediaPlayer.getStatus() == Status.PAUSED) {
				this.mediaPlayer.stop();
			}
		}
	}

	public void pause(){
		if (this.mediaPlayer != null) {
			if (this.mediaPlayer.getStatus() == Status.PLAYING){
				this.mediaPlayer.pause();
			}else{
				this.mediaPlayer.play();
			}
		}
	}
	
	public void mediaControl(final MediaPlayer mediaPlayer) {
		Status status = null;
		if (this.mediaPlayer != null) {
			status = this.mediaPlayer.getStatus();
		}

		if (status == Status.PLAYING) {
			this.mediaPlayer.stop();
			this.mediaPlayer = mediaPlayer;
		} else {
			this.mediaPlayer = mediaPlayer;
		}
		getMediaPlayer().currentTimeProperty().removeListener(progressChangeListener);
		this.mediaPlayer.play();
		setCurrentlyPlaying(this.mediaPlayer);

	}

	private void setCurrentlyPlaying(MediaPlayer mediaPlayer) {
		mediaPlayer.currentTimeProperty().addListener(progressChangeListener);
	}

	/**
	 * Método que recebe a sublista de MediaPlayers para serem tocadas
	 * 
	 * @param mpList
	 *            List<MediaPlayer>
	 */
	public void mediaControl(List<MediaPlayer> mpList) {
		// Varrer toda a lista e a cada nó atribui o que fazer ao final de cada
		// Media
		for (int i = 0; i < mpList.size(); i++) {
			MediaPlayer atualMP = mpList.get(i);
			MediaPlayer nextMP = mpList.get((i + 1) % mpList.size());
			mpList.get(i).setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					getMediaPlayer().stop();
					getMediaPlayer().currentTimeProperty().removeListener(progressChangeListener);
					setMediaPlayer(nextMP);
					mediaControl(getMediaPlayer());
					

				}
			});
		}
		mediaControl(mpList.get(0)); // executa o Primeiro MediaPlayer

	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public Status getMediaControlStatus() {
		return getMediaPlayer().getStatus();
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

}
