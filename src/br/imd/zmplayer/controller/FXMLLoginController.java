package br.imd.zmplayer.controller;

import br.imd.zmplayer.*;
import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLLoginController implements Initializable {

	@FXML
	private Button btnLogar;
	public TextField userTextField;
	public PasswordField passwordField;
	public Label lbLoginInfo;
	public MenuItem closeButton;

	@FXML
	private void handleLoginButtonAction(ActionEvent event) throws IOException {
		
		//Ler arquivo dos usuarios cadastrados para add na arvore.
		String path = "usuarios.zmu";
		ManipuladorArquivo.lerZmu(path);
		
		String idDigitado = userTextField.getText();
		String senhaDigitada = passwordField.getText();
		
		if (idDigitado.equals(Usuario.getAdmin().getId()) && senhaDigitada.equals(Usuario.getAdmin().getSenha())) {
			System.out.println("logou como admin");
			
			this.abrirTelaPlayer(Usuario.getAdmin());
		
		} else{		

			//procurar o id e senha digitados no sistema
			Usuario user = UserController.verificarLogin(idDigitado, senhaDigitada);
			
			if(user != null){		
				System.out.println("Bem-vindo, "+user.getNome());
				this.abrirTelaPlayer(user);
			}else{
				lbLoginInfo.setText("Usuário/Senha Inválidos");
				System.out.println("Login invalido");
			}
		}
	}
	
	private void abrirTelaPlayer(Usuario tipoUsuario) throws IOException{
		
		OperationalController.iniciarSessao(tipoUsuario);
		
		Stage stage = (Stage) btnLogar.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLPlayerScene.fxml"));

		FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
