package br.imd.zmplayer.controller;
import br.imd.zmplayer.*;

import br.imd.zmplayer.model.Usuario;

import br.imd.zmplayer.controller.utils.OperationalController;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FXMLUserSettingsController implements Initializable{	
	
	@FXML
	private TableView<Usuario> tableUsuario;
	private TableColumn<Usuario, String> columnNome;
	private TableColumn<Usuario, String> columnId;
	private TableColumn<Usuario, String> columnSenha;
	private TableColumn<Usuario, String> columnVip;

	private Button btnCadastrar;
	private Button btnRemover;
	private Button btnAlterar;
	
	public TextField userTextField;
	public Label lbLoginInfo;
	public MenuItem closeButton;

	@FXML
	private void inicializarTabela(){
		tableUsuario.setEditable(true);
		
		List usuarios = Arrays.asList(
			new Usuario("maria", "Maria" , "5588", true)
			);
		
		tableUsuario = new TableView<>();
        columnNome = new TableColumn<>("Nome");
        columnId = new TableColumn<>("Id");
        columnSenha = new TableColumn<>("Senha");
        columnVip = new TableColumn<>("VIP");

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        columnVip.setCellValueFactory(new PropertyValueFactory<>("vip"));

        tableUsuario.setItems(FXCollections.observableArrayList(usuarios));
        tableUsuario.getColumns().addAll(columnNome, columnId, columnSenha,columnVip);
        
        /*primaryStage.setScene(new Scene(tabela));
        primaryStage.setWidth(250);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Tabelas no JavaFX");
        primaryStage.show();*/
        
	}
	
	@FXML
	private void handleLoginButtonAction(ActionEvent event) throws IOException {}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
	
	

}
