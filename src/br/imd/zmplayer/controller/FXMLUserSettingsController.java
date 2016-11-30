package br.imd.zmplayer.controller;
import br.imd.zmplayer.*;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;

import br.imd.zmplayer.controller.utils.OperationalController;

import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	
	@FXML
	private Button btnCadastrar;
	private Button btnRemover;
	private Button btnAlterar;
	
	@FXML
	private Label resultadoLabel;
	
	@FXML
	public TextField nomeTextField;
	public TextField idTextField;
	public TextField senhaTextField;
	public CheckBox vipCheckBox;
	

	@FXML
	private void inicializarTabela(){
		
		//tableUsuario.setEditable(true);
		
		List<Usuario> usuarios = Arrays.asList(
			new Usuario("maria", "Maria" , "5588", true),
			new Usuario("felipe", "Felipe" , "1234", false)
			);
	
        this.columnNome = new TableColumn<>("Nome");
        this.columnId = new TableColumn<>("Id");
        this.columnSenha = new TableColumn<>("Senha");
        this.columnVip = new TableColumn<>("VIP");
        
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        columnVip.setCellValueFactory(new PropertyValueFactory<>("vip"));

        tableUsuario.setItems(FXCollections.observableArrayList(usuarios));
        tableUsuario.getColumns().addAll(columnNome, columnId, columnSenha,columnVip);
  
        tableUsuario.refresh();
        
        
	}
	
	
	@FXML
	private void handleCadastrarBtn(ActionEvent event) throws IOException {
		String nome = nomeTextField.getText();
		String id = idTextField.getText();
		String senha = senhaTextField.getText();
		boolean vip = vipCheckBox.isSelected();
				
		if(UserController.cadastrarUsuario(new Usuario(id,nome,senha,vip))){
			System.out.println("ok");
			resultadoLabel.setText("Usuário cadastrado com sucesso!");
		}else{
			System.out.println("no");
			//resultadoLabel.setText("Problema no cadastro. Tente Novamente!");
		}
		
	}
	
	@FXML
	private void handleRemoverBtn(ActionEvent event) throws IOException {
		
		Usuario user = tableUsuario.getSelectionModel().getSelectedItem();
		
		nomeTextField.setText(user.getNome());
		idTextField.setText(user.getId());
		senhaTextField.setText(user.getSenha());
		vipCheckBox.setSelected(user.isVIP());
				
		if( UserController.removerUsuario(user) ){
			System.out.println("ok");
			resultadoLabel.setText("Usuário removido com sucesso!");
		}else{
			System.out.println("no");
			//resultadoLabel.setText("Problema no cadastro. Tente Novamente!");
		}
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.inicializarTabela();
		
		
		
	}
	
	

}
