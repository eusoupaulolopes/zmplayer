package br.imd.zmplayer.controller;
import br.imd.zmplayer.*;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.tabela.UsuarioTabela;
import br.imd.zmplayer.model.tad.NoBinaria;

import br.imd.zmplayer.controller.utils.OperationalController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class FXMLNamePlaylistController extends FXMLPlayerController implements Initializable {

	
	
	@FXML TextField txtFieldPlaylistName;
	@FXML Button btnSave;
	@FXML Button btnCancel;
	
	@FXML
	private void saveButtonAction(ActionEvent event) throws IOException {
		String nomePlaylist = txtFieldPlaylistName.getText();
	
		if(!nomePlaylist.equals("")){
			new PlaylistController().addPlaylist(super.getTableMyPlaylists(),nomePlaylist);
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