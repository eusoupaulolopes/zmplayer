package br.imd.zmplayer.controller;
import br.imd.zmplayer.*;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class FXMLoginController implements Initializable {

    @FXML
    private Button btnLogar;
    public TextField userTextField;
    public PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction(ActionEvent event){
    	if (userTextField.getText().equals("admin") && passwordField.getText().equals("admin")){
        
            System.out.println("logou como admin");
        } else {
        	System.out.println("Login invalido");
        }

        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
