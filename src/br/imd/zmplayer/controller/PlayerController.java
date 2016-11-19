package br.imd.zmplayer.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	private static PlayerController pl = new PlayerController();
	
	private PlayerController(){
		
	}
	
	public static PlayerController getInstance(){
		return pl;
	}
	
	public Media media;
	public MediaPlayer mediaPlayer;
	public Status status;
	
	public void tocar(File file) {
		media = new Media(file.toURI().toString());
		MediaPlayer mPlayer = new MediaPlayer(media);
		MediaControl(mPlayer);
	}
	
	public void MediaControl(final MediaPlayer mediaPlayer){
		Status status = null;
		if(this.mediaPlayer != null){
			status = this.mediaPlayer.getStatus();
		}
		
		if(status == Status.PLAYING){
			this.mediaPlayer.stop();
			this.mediaPlayer = mediaPlayer;
		}else{
			this.mediaPlayer = mediaPlayer;		
		}
		this.mediaPlayer.play();
		
	}
	
	
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}	

	

	/*
	 * public void tocar(File file) throws FileNotFoundException {
	 * 
	 * FileInputStream music = new FileInputStream(file); BufferedInputStream
	 * bis = new BufferedInputStream(music);
	 * 
	 * 
	 * try { Player player = new Player(bis);
	 * System.out.println("Seduzindo com " + file.getAbsolutePath()); new
	 * Thread(){ run(){ player.play(); } }.start();
	 * 
	 * } catch (JavaLayerException e) {
	 * System.out.println("Não pude abrir o arquivo"); } }
	 */

}
