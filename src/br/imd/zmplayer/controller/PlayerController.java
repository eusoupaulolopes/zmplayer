package br.imd.zmplayer.controller;

import java.io.File;

import javafx.stage.FileChooser;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class PlayerController implements Initializable{

	final FileChooser fileChooser = new FileChooser();

	public void selectMusic() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivo mp3", "*.mp3"));

		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			play(selectedFile.getAbsolutePath());
			
		}

	}

	public void play(String musica) {
		System.out.println(musica);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
