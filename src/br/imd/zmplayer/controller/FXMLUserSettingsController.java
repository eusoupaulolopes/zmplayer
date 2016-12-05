package br.imd.zmplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.ManipuladorArquivo;
import br.imd.zmplayer.model.RepositorioUsuario;
import br.imd.zmplayer.model.Usuario;
import br.imd.zmplayer.model.tabela.UsuarioTabela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * Classe responsável por controlar a Tela de user settings
 * @author Clarissa Soares / Paulo Henrique
 * @version 1.0
 *
 */
public class FXMLUserSettingsController implements Initializable {

	@FXML
	private TableView<UsuarioTabela> tableUsuario;
	private TableColumn<UsuarioTabela, String> columnNome = new TableColumn<UsuarioTabela, String>("Nome");
	private TableColumn<UsuarioTabela, String> columnId = new TableColumn<UsuarioTabela, String>("Id");
	private TableColumn<UsuarioTabela, String> columnSenha = new TableColumn<UsuarioTabela, String>("Senha");
	private TableColumn<UsuarioTabela, String> columnVip = new TableColumn<UsuarioTabela, String>("VIP");

	// Cria uma lista com todos usuários cadastrados
	private List<Usuario> usuarios;
	private ObservableList<UsuarioTabela> listaUsuarioTabela = FXCollections.observableArrayList();

	@FXML
	private Button btnCadastrar;
	@FXML
	Button btnCancelar;
	@FXML
	Button btnSalvar;
	@FXML
	Button btnAlterar;
	@FXML
	Button btnRemover;

	@FXML
	private Label resultadoLabel;

	@FXML
	public TextField nomeTextField;
	public TextField idTextField;
	public TextField senhaTextField;
	public CheckBox vipCheckBox;
	
	private int index;
	@FXML
	Pane edicaoPane;

	@FXML
	private void handleCadastrarBtn(ActionEvent event) throws IOException {

		boolean vip;
		if (!OperationalController.getSessao().getUser().getId().equals("admin")) {
			vip = false;
		} else {
			vip = vipCheckBox.isSelected();
		}

		String nome = nomeTextField.getText();
		String id = idTextField.getText();
		String senha = senhaTextField.getText();

		if (!nome.equals("") && !id.equals("") && !senha.equals("")) {
			if (UserController.cadastrarUsuario(new Usuario(id, nome, senha, vip))) {
				System.out.println(id + " cadastrado");

				listaUsuarioTabela.add(new UsuarioTabela(id, nome, senha, vip));
				tableUsuario.refresh();

				if (vip) {
					//Se for VIP, cria o seu arquivo de usuário que contém os caminhos para as playlists
					ManipuladorArquivo.criarArquivoUserVip(id);
				}

				resultadoLabel.setText("Usuário cadastrado com sucesso!");
				this.limparTextField();

			} else {
				System.out.println("no");
				resultadoLabel.setText("Id já cadastrada!");
			}
		} else {
			resultadoLabel.setText("Porfavor, preencha todos os campos!");
		}

	}

	

	@FXML
	private void handleRemoverBtn(ActionEvent event) throws IOException {

		// Pega o item selecionado na tabela
		UsuarioTabela temp = tableUsuario.getSelectionModel().getSelectedItem();

		if (temp != null) {

			Usuario user = temp.toUsuario();
			resultadoLabel.setText(UserController.removerUsuario(user));
			listaUsuarioTabela.remove(temp);
			usuarios = RepositorioUsuario.listUsuarios();

			tableUsuario.refresh();

		} else {
			resultadoLabel.setText("Selecione o usuário a ser removido.");
		}

	}

	@FXML
	public void handleAlterarBtn(ActionEvent event) throws IOException {

		habilitarModoEdicao();

		// Pega o item selecionado na tabela
		UsuarioTabela temp = tableUsuario.getSelectionModel().getSelectedItem();

		index = listaUsuarioTabela.indexOf(temp);
		if (temp != null) {

			nomeTextField.setText(temp.getNome());
			idTextField.setText(temp.getId());
			senhaTextField.setText(temp.getSenha());
			vipCheckBox.setSelected(temp.getVipBoolean());

		} else {
			resultadoLabel.setText("Selecione o usuário a ser alterado.");
		}
	}

	@FXML
	public void handleSalvarBtn(ActionEvent event) throws IOException {

		Usuario user = new Usuario(idTextField.getText(), nomeTextField.getText(), senhaTextField.getText(),
				vipCheckBox.isSelected());

		UsuarioTabela temp = new UsuarioTabela(user.getId(), user.getNome(), user.getSenha(), user.isVIP());

		if (UserController.alterarUsuario(user)) {

			usuarios = RepositorioUsuario.listUsuarios();
			listaUsuarioTabela.set(index, temp);

			tableUsuario.refresh();

			resultadoLabel.setText("Usuário " + user.getId() + " alterado com sucesso!");

		} else {
			resultadoLabel.setText("Usuário " + user.getId() + " não pode ser alterado!");

		}
		this.desabilitarModoEdicao();
		this.limparTextField();

	}

	@FXML
	public void handleCancelarBtn(ActionEvent event) throws IOException {
		this.desabilitarModoEdicao();
		this.limparTextField();
	}
	/**
	 * Método que habilita a tela para o modo de edição, alterando a visibilidade de botoes e bloqueando o
	 * text field do id do usuário.
	 */
	private void habilitarModoEdicao() {
		btnCancelar.setVisible(true);
		btnSalvar.setVisible(true);
		idTextField.setEditable(false);
		btnRemover.setDisable(true);
		btnCadastrar.setDisable(true);

	}
	
	/**
	 * Método que desabilita a tela do modo de edição, alterando a visibilidade de botoes e desbloqueando o
	 * text field do id do usuário.
	 */
	private void desabilitarModoEdicao() {
		btnCancelar.setVisible(false);
		btnSalvar.setVisible(false);
		idTextField.setEditable(true);
		btnRemover.setDisable(false);
		btnCadastrar.setDisable(false);
	}

	/**
	 * Método que limpa os TextFields da janela User Settings.
	 */
	private void limparTextField() {
		nomeTextField.setText("");
		idTextField.setText("");
		senhaTextField.setText("");
		vipCheckBox.setSelected(false);
	}
	/**
	 * Método que inicializa a tabela de usuários.
	 */
	public void listarUsuarios() {
		if (!listaUsuarioTabela.isEmpty()) {
			listaUsuarioTabela.clear();
			System.out.println("limpou observale table");
		}

		usuarios = RepositorioUsuario.listUsuarios();

		for (Usuario usuario : usuarios) {
			UsuarioTabela u = new UsuarioTabela(usuario.getId(), usuario.getNome(), usuario.getSenha(),
					usuario.isVIP());
			listaUsuarioTabela.add(u);
		}

		columnNome.setPrefWidth(120.0);
		columnId.setPrefWidth(120.0);
		columnSenha.setPrefWidth(120.0);
		columnVip.setPrefWidth(100.0);

		columnNome.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("nome"));
		columnId.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("id"));
		columnSenha.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("senha"));
		columnVip.setCellValueFactory(new PropertyValueFactory<UsuarioTabela, String>("vip"));

		tableUsuario.setItems(listaUsuarioTabela);
		tableUsuario.getColumns().addAll(columnNome, columnId, columnSenha, columnVip);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.listarUsuarios();

		// Verifica se o usuário da sessão é o admin, se não for, não pode
		// cadastrar usuário VIP
		if (!OperationalController.getSessao().getUser().getId().equals("admin")) {
			vipCheckBox.setVisible(false);

		}

		this.desabilitarModoEdicao();

	}

}
