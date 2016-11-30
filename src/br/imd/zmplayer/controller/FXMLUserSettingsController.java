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



public class FXMLUserSettingsController implements Initializable{	
	
	
	@FXML
	private TableView<UsuarioTabela> tableUsuario;
	private TableColumn<UsuarioTabela, String> columnNome = new TableColumn<UsuarioTabela, String>("Nome");
	private TableColumn<UsuarioTabela, String> columnId = new TableColumn<UsuarioTabela, String>("Id");
	private TableColumn<UsuarioTabela, String> columnSenha = new TableColumn<UsuarioTabela, String>("Senha");
	private TableColumn<UsuarioTabela, String> columnVip = new TableColumn<UsuarioTabela, String>("VIP");
	
	//Cria uma lista com todos usuários cadastrados
	private List<Usuario> usuarios =  RepositorioUsuario.listUsuarios();
	
	private ObservableList<UsuarioTabela> listaUsuarioTabela = FXCollections.observableArrayList();
	
	
	@FXML
	private Button btnCadastrar;
	private Button btnRemover;
	private Button btnAlterar;
	@FXML Button btnSalvar;
	@FXML Button btnCancelar;
	
	@FXML
	private Label resultadoLabel;
	
	@FXML
	public TextField nomeTextField;
	public TextField idTextField;
	public TextField senhaTextField;
	public CheckBox vipCheckBox;
	
	

	

	
	private void inicializarTabela(){
		
		//tableUsuario.setEditable(true);
		
		/*List<Usuario> usuarios = Arrays.asList(
			new Usuario("clahz", "Maria" , "5588", true),
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

        tableUsuario.setItems(FXCollections.observableArrayList(this.usuarios));
        tableUsuario.getColumns().addAll(columnNome, columnId, columnSenha,columnVip);
  
        tableUsuario.refresh();*/
        
        
	}
	
	
	@FXML
	private void handleCadastrarBtn(ActionEvent event) throws IOException {
		String nome = nomeTextField.getText();
		String id = idTextField.getText();
		String senha = senhaTextField.getText();
		boolean vip = vipCheckBox.isSelected();
				
		if(UserController.cadastrarUsuario(new Usuario(id,nome,senha,vip))){
			System.out.println("ok");
			
			listaUsuarioTabela.add(new UsuarioTabela(id, nome, senha, vip));			
			tableUsuario.refresh();
			resultadoLabel.setText("Usuário cadastrado com sucesso!");
			
		}else{
			System.out.println("no");
			resultadoLabel.setText("Id já cadastrada!");
		}
		
	}
	private int index;
	@FXML
	private void handleRemoverBtn(ActionEvent event) throws IOException {
		//Pega o item selecionado na tabela
		UsuarioTabela temp = tableUsuario.getSelectionModel().getSelectedItem();
		
		if(temp != null){
			index = listaUsuarioTabela.indexOf(temp);
			Usuario user = temp.toUsuario();
			listaUsuarioTabela.remove(temp);
			
			resultadoLabel.setText(UserController.removerUsuario(user));
			
			usuarios =  RepositorioUsuario.listUsuarios();
			
			tableUsuario.refresh();
		}else{
			resultadoLabel.setText("Selecione o usuário a ser removido.");
		}
		
		
		
	}
	
	@FXML
	public void handleAlterarBtn(ActionEvent event) throws IOException {
		//Pega o item selecionado na tabela
		UsuarioTabela temp = tableUsuario.getSelectionModel().getSelectedItem();
		
		if(temp != null){
			btnCancelar.setVisible(true);
			btnSalvar.setVisible(true);
			
			nomeTextField.setText(temp.getNome());
			idTextField.setText(temp.getId());
			idTextField.setEditable(false);			
			senhaTextField.setText(temp.getSenha());
			vipCheckBox.setSelected(temp.getVipBoolean());		
			
		}else{
			resultadoLabel.setText("Selecione o usuário a ser alterado.");
		}		
	}
	
	@FXML
	public void handleCancelarBtn(ActionEvent event) throws IOException {
		btnCancelar.setVisible(false);
		btnSalvar.setVisible(false);
	}
	
	@FXML
	public void handleSalvarBtn(ActionEvent event) throws IOException {
		
		Usuario user = new Usuario(idTextField.getText(), nomeTextField.getText(),
				senhaTextField.getText(), vipCheckBox.isSelected());
		
		UsuarioTabela temp = new UsuarioTabela(user.getId(), user.getNome(), user.getSenha(), user.isVIP());
		
		if(UserController.alterarUsuario(user)){
			
			listaUsuarioTabela.set(index, temp);
			usuarios =  RepositorioUsuario.listUsuarios();
			
			resultadoLabel.setText("Usuário "+user.getId()+" alterado com sucesso!");
			tableUsuario.refresh();

		}
		else{
			resultadoLabel.setText("Usuário "+user.getId()+" não pode ser alterado!");
		}
		btnCancelar.setVisible(false);
		btnSalvar.setVisible(false);
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.listarUsuarios();
		
		
		
		
	}
	
	public void listarUsuarios(){
		if(!listaUsuarioTabela.isEmpty()){
			listaUsuarioTabela.clear();
			System.out.println("limpou observale table");
		}
		
		for(Usuario usuario: usuarios){
			UsuarioTabela u = new UsuarioTabela(usuario.getId(),usuario.getNome(), usuario.getSenha(), usuario.isVIP());
			listaUsuarioTabela.add(u);
		}
		
		columnNome.setPrefWidth(100.0);
		columnId.setPrefWidth(100.0);
		columnSenha.setPrefWidth(100.0);
		columnVip.setPrefWidth(100.0);
		
		columnNome.setCellValueFactory(new PropertyValueFactory<UsuarioTabela,String>("nome"));
		columnId.setCellValueFactory(new PropertyValueFactory<UsuarioTabela,String>("id"));
		columnSenha.setCellValueFactory(new PropertyValueFactory<UsuarioTabela,String>("senha"));
		columnVip.setCellValueFactory(new PropertyValueFactory<UsuarioTabela,String>("vip"));
		
		tableUsuario.setItems(listaUsuarioTabela);
		tableUsuario.getColumns().addAll(columnNome, columnId, columnSenha,columnVip);
	}
	
	

}
