package br.imd.zmplayer.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Classe responsável por controlar a Tela New Playlist
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 */
public class FXMLNamePlaylistController extends FXMLPlayerController implements Initializable {

	
	
	@FXML TextField txtFieldPlaylistName;
	@FXML Button btnSave;
	@FXML Button btnCancel;
	
	@FXML
	private void saveButtonAction(ActionEvent event) throws IOException {
		String nomePlaylist = txtFieldPlaylistName.getText();
	
		if(!nomePlaylist.equals("")){
			PlaylistController.getInstance().addPlaylist(super.getTableMyPlaylists(),nomePlaylist);
		}else{
			System.out.println("Nenhum nome inserido!");
		}
		cancelButtonAction(null);
	}
	
	@FXML
	private void cancelButtonAction(ActionEvent event) throws IOException {		
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}	
	
	
	
}