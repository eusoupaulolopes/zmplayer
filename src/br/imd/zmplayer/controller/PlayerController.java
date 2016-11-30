package br.imd.zmplayer.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.print.PageLayout;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerController {

	private static PlayerController pl;

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
		MediaControl(mPlayer);
	}

	public void tocar(List<File> files) {
		for (File file : files) {
			media = new Media(file.toURI().toString());
			System.out.println(file.toURI());
			MediaPlayer mPlayer = new MediaPlayer(media);
			MediaControl(mPlayer);
		}

	}

	public void parar() {
		if (this.mediaPlayer != null) {
			if (this.mediaPlayer.getStatus() == Status.PLAYING || this.mediaPlayer.getStatus() == Status.PAUSED) {
				this.mediaPlayer.stop();
			}
		}
	}

	public void MediaControl(final MediaPlayer mediaPlayer) {
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
		this.mediaPlayer.play();
	}

	public Boolean MediaControl() {
		Status status = null;
		if (this.mediaPlayer != null) {
			status = this.mediaPlayer.getStatus();
		}

		if (status == Status.PLAYING) {
			this.mediaPlayer.pause();
			return false;
		} else {
			this.mediaPlayer.play();
			return true;
		}
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

}
