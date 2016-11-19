package br.imd.zmplayer.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerController extends FXMLPlayerController {

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

}
