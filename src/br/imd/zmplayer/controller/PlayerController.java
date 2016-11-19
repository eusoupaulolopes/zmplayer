package br.imd.zmplayer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerController extends FXMLPlayerController {
	
	
	public void tocar(File file){
		//TODO Arrumar o caso de tocar duas musicas ao mesmo tempo
		Media media = new Media(file.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		MediaView mediaView = new MediaView(mediaPlayer);
	}

	/*
	 public void tocar(File file) throws FileNotFoundException {
	 
		FileInputStream music = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(music);
		
		
		try {
			Player player = new Player(bis);
			System.out.println("Seduzindo com " + file.getAbsolutePath());
			new Thread(){
				run(){
					player.play();
				}
			}.start();
			
		} catch (JavaLayerException e) {
			System.out.println("Não pude abrir o arquivo");
		}
	}
	*/

}
