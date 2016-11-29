package br.imd.zmplayer.controller;

import java.util.Arrays;
import java.util.List;

import br.imd.zmplayer.model.Musica;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


public class ListaControler extends FXMLPlayerController {

	public void criarListaVazia(){
		List musicas = Arrays.asList(new Musica(1, "Teste", "C:\\"));
		
		
		getTableMusics().setEditable(true);
		this.columnNumber = new TableColumn<>("Nº");
        this.columnMusic = new TableColumn<>("Musica");
        this.columnPath = new TableColumn<>("Path");
                

		
        getColumnNumber().setCellValueFactory(new PropertyValueFactory<>("Nº"));
        getColumnMusic().setCellValueFactory(new PropertyValueFactory<>("Musica"));
        getColumnPath().setCellValueFactory(new PropertyValueFactory<>("Path"));
        
		getTableMusics().setItems(FXCollections.observableArrayList(musicas));
		getTableMusics().getColumns().addAll(columnNumber, columnMusic, columnPath);
		getTableMusics().refresh();
		
	}
}
